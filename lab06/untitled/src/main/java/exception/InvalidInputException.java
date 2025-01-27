package exception;

/**
 * Custom exception for invalid input.
 * @author Bartosz Pałucki
 * @version 6.1
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructor for InvalidInputException.
     * @param message Error message to be displayed.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
