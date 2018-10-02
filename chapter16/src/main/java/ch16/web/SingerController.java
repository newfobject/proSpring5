package ch16.web;

import ch16.entities.Singer;
import ch16.entities.SingerGrid;
import ch16.services.SingerService;
import ch16.util.Message;
import ch16.util.UriUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

@RequestMapping("/singers")
@Controller
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    private SingerService singerService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Listing singers");
        List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);

        logger.info("No. of singers: " + singers.size());
        return "singers/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        logger.info("Showing singer");
        Singer singer = singerService.findById(id);
        model.addAttribute("singer", singer);
        return "singers/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Singer singer, BindingResult bindingResult,
                         Model model, HttpServletRequest request,
                         RedirectAttributes redirectAttributes, Locale locale,
                         @PathVariable("id") Long id,
                         @RequestParam(value = "file", required = false) Part file) {
        logger.info("Updating singer");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error", messageSource.getMessage("singer_save_fail",
                    new Object[]{}, locale)));
            model.addAttribute("singer", singer);
            return "singers/update";
        }
        model.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message(
                "success", messageSource.getMessage("singer_save_success", new Object[]{}, locale)));

        setImage(singer, file);

        singerService.save(singer);
        return "redirect:/singers/" + UriUtil.encodePathSegment(singer.getId().toString(), request);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("singer", singerService.findById(id));
        return "singers/update";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Singer singer, BindingResult bindingResult,
                         Model model, HttpServletRequest request,
                         RedirectAttributes redirectAttributes, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {
        logger.info("Creating singer");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            model.addAttribute("singer", singer);
            return "singers/create";
        }
        model.asMap().clear();

        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
        logger.info("Singer id: " + singer.getId());

        setImage(singer, file);

        singerService.save(singer);
        return "redirect:/singers/";
    }

    @GetMapping(value = "/photo/{id}")
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Singer singer = singerService.findById(id);

        if (singer.getPhoto() != null) {
            logger.info("Downloading photo for id: {} with size: {}", singer.getId(), singer.getPhoto().length);
        }

        return singer.getPhoto();
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "singers/create";
    }

    @ResponseBody
    @RequestMapping(value = "/listgrid", method = RequestMethod.GET,
            produces = "application/json")
    public SingerGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "rows", required = false) Integer rows,
                               @RequestParam(value = "sidx", required = false) String sortBy,
                               @RequestParam(value = "sord", required = false) String order) {
        logger.info("Listing singers for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing singers for grid with sort: {}, order: {}",
                sortBy, order);

        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("birthDateString"))
            orderBy = "birthDate";

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
        }

        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = PageRequest.of(page - 1, rows, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, rows);
        }

        Page<Singer> singerPage = singerService.findAllByPage(pageRequest);

        SingerGrid singerGrid = new SingerGrid();
        singerGrid.setCurrentPage(singerPage.getNumber() + 1);
        singerGrid.setTotalPages(singerPage.getTotalPages());
        singerGrid.setTotalRecords(singerPage.getTotalElements());
        singerGrid.setSingerData(Lists.newArrayList(singerPage.iterator()));

        return singerGrid;
    }

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private void setImage(Singer singer, Part file) {
        logger.info("File name: " + file.getName());
        logger.info("File size: " + file.getSize());
        logger.info("File content type: " + file.getContentType());
        byte[] fileContent = null;

        try {
            InputStream inputStream = file.getInputStream();
            if (inputStream == null)
                logger.info("File inputstream is null");

            fileContent = IOUtils.toByteArray(inputStream);
            singer.setPhoto(fileContent);
        } catch (IOException e) {
            logger.error("Error saving uploaded file");
        }
    }
}
