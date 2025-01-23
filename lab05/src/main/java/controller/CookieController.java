package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CookieService;

import java.util.Optional;

/**
 * Controller to handle cookie-related requests.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@RestController
public class CookieController {

    private final CookieService cookieService;

    /**
     * Constructs a CookieController with the specified CookieService.
     *
     * @param cookieService the CookieService to use for cookie operations
     */
    @Autowired
    public CookieController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    /**
     * Sets initial cookies for visits and errors.
     *
     * @param response the HttpServletResponse
     * @return a message indicating that the cookies have been set
     */
    @GetMapping("/cookies/set")
    public String setCookies(HttpServletResponse response) {
        cookieService.setCookie(response, "visits", "1", 7 * 24 * 60 * 60); // 7 days
        cookieService.setCookie(response, "errors", "0", 7 * 24 * 60 * 60);
        return "Cookies have been set.";
    }

    /**
     * Reads the values of the visits and errors cookies.
     *
     * @param request the HttpServletRequest
     * @return a message with the values of the visits and errors cookies
     */
    @GetMapping("/cookies/read")
    public String readCookies(HttpServletRequest request) {
        Optional<String> visits = cookieService.getCookieValue(request, "visits");
        Optional<String> errors = cookieService.getCookieValue(request, "errors");

        return String.format(
                "Visits: %s, Errors: %s",
                visits.orElse("not set"),
                errors.orElse("not set")
        );
    }

    /**
     * Updates the visits cookie by incrementing its value.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @return a message indicating the new visit count
     */
    @GetMapping("/cookies/update")
    public String updateCookies(HttpServletRequest request, HttpServletResponse response) {
        try {
            Optional<String> visits = cookieService.getCookieValue(request, "visits");
            int visitCount = visits.map(Integer::parseInt).orElse(0) + 1;

            cookieService.setCookie(response, "visits", String.valueOf(visitCount), 7 * 24 * 60 * 60);
            return "Visit count updated to: " + visitCount;
        } catch (Exception e) {
            cookieService.incrementCookieValue(response, request, "errors");
            throw e;
        }
    }

    /**
     * Reads the value of the errors' cookie.
     *
     * @param request the HttpServletRequest
     * @return a message with the value of the errors cookie
     */
    @GetMapping("/cookies/errors")
    public String readErrorCookie(HttpServletRequest request) {
        Optional<String> errors = cookieService.getCookieValue(request, "errors");
        return String.format("Errors: %s", errors.orElse("not set"));
    }
}