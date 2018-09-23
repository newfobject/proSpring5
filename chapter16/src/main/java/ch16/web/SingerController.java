package ch16.web;

import ch16.entities.Singer;
import ch16.services.SingerService;
import ch16.util.Message;
import ch16.util.UriUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
                         @PathVariable("id") Long id) {
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
                         RedirectAttributes redirectAttributes, Locale locale) {
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
        singerService.save(singer);
        return "redirect:/singers/";
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "singers/create";
    }

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
