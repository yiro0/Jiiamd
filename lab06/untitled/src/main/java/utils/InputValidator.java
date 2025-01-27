package utils;

import java.util.regex.Pattern;

/**
 * Utility class to validate user input.
 *
 * @author Bartosz Pałucki
 * @version 6.1
 */
public class InputValidator {

    private static final Pattern LATIN_PATTERN = Pattern.compile("^[\\x00-\\x7F]*$");

    /**
     * Validates if the input contains only Latin characters.
     *
     * @param input The input string to validate.
     * @return true if the input contains only Latin characters, false otherwise.
     */
    public static boolean isValidLatin(String input) {
        return LATIN_PATTERN.matcher(input).matches();
    }
}
