package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller to handle application errors and exceptions.
 * Sends appropriate error responses to the client.
 *
 * This approach centralizes error handling and separates concerns.
 *
 * Since there was an issue with the dependency injection of the HttpServletResponse, the ResponseEntity class is used instead.
 *
 * Ensures that the client is informed about incorrect application operations by using exceptions.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@ControllerAdvice
public class ErrorController {

    /**
     * Handles NullPointerException and returns a 400 Bad Request response.
     *
     * @param ex the NullPointerException
     * @return a ResponseEntity with the error message and HTTP status
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Null Pointer Exception: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles IllegalArgumentException and returns a 400 Bad Request response.
     *
     * @param ex the IllegalArgumentException
     * @return a ResponseEntity with the error message and HTTP status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Illegal Argument Exception: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex the Exception
     * @return a ResponseEntity with the error message and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}