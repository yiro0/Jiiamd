package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor to handle error count cookies.
 * Increments the error count cookie whenever an exception is thrown.
 * <p>
 * Ensures that error count is tracked for each exception.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@Component
public class ErrorInterceptor implements HandlerInterceptor {

    private final CookieService cookieService;

    /**
     * Constructs an ErrorInterceptor with the specified CookieService.
     *
     * @param cookieService the CookieService to use for cookie operations
     */
    public ErrorInterceptor(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    /**
     * Intercepts the request to increment the error count cookie if an exception is thrown.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @param handler  the handler
     * @param ex       the exception thrown
     * @throws Exception if an error occurs
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String acceptHeader = request.getHeader("Accept");
        if (!requestURI.startsWith("/cookies/set") && acceptHeader != null && acceptHeader.contains("text/html")) {
            if (ex != null) {
                cookieService.incrementCookieValue(response, request, "errors");
            }
        }
    }
}