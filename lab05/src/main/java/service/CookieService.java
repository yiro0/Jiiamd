package service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to handle cookie operations.
 * Provides methods to set, get, increment, and delete cookies.
 *
 * Ensures that cookie operations are centralized and reusable.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@Service
public class CookieService {

    /**
     * Sets a cookie with the specified name, value, and max age.
     *
     * @param response the HttpServletResponse
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param maxAge   the maximum age of the cookie in seconds
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * Retrieves the value of the cookie with the specified name.
     *
     * @param request the HttpServletRequest
     * @param name    the name of the cookie
     * @return an Optional containing the cookie value, or an empty Optional if the cookie is not found
     */
    public Optional<String> getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Increments the value of the cookie with the specified name.
     *
     * @param response the HttpServletResponse
     * @param request  the HttpServletRequest
     * @param name     the name of the cookie
     */
    public void incrementCookieValue(HttpServletResponse response, HttpServletRequest request, String name) {
        int currentValue = getCookieValue(request, name).map(Integer::parseInt).orElse(0);
        int newValue = currentValue + 1;

        Cookie cookie = new Cookie(name, String.valueOf(newValue));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * Deletes the cookie with the specified name.
     *
     * @param response the HttpServletResponse
     * @param name     the name of the cookie
     */
    public void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}