package ch16.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class UriUtil {
    public static String encodePathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        return pathSegment;
    }
}
