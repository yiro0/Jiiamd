package controller;

import exception.InvalidInputException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * Handles various exceptions that may occur during runtime and provides
 * meaningful error messages to users.
 * @author Bartosz Pałucki
 * @version 6.2
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles InvalidInputException globally.
     * @param ex The exception to handle.
     * @param model The model to send data to the view.
     * @return The view name.
     */
    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInputException(InvalidInputException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // Redirect to the error view with the error message
    }

    /**
     * Handles attempts to insert duplicate data or constraint violations globally.
     * @param ex The exception to handle.
     * @param model The model to send data to the view.
     * @return The view name.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("error", "Duplicate or invalid data entry: " + ex.getMostSpecificCause().getMessage());
        return "error"; // Redirect to the error view with the error message
    }
}