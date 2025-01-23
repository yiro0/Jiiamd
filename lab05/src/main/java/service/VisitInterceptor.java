package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor to handle visit count cookies.
 * Increments the visit count cookie for HTML page requests.
 * <p>
 * Ensures that visit count is tracked for each page request.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@Component
public class VisitInterceptor implements HandlerInterceptor {

    private final CookieService cookieService;

    /**
     * Constructs a VisitInterceptor with the specified CookieService.
     *
     * @param cookieService the CookieService to use for cookie operations
     */
    public VisitInterceptor(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    /**
     * Intercepts the request to increment the visit count cookie.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @param handler  the handler
     * @return true to continue the request, false to abort
     * @throws Exception if an error occurs
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String acceptHeader = request.getHeader("Accept");

        if (!requestURI.startsWith("/cookies/set") && acceptHeader != null && acceptHeader.contains("text/html")) {
            cookieService.incrementCookieValue(response, request, "visits");
        }
        return true;
    }
}