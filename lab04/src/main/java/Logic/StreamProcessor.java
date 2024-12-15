package Logic;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The StreamProcessor class provides methods for processing streams of characters.
 * It includes methods to filter encoded texts, convert texts to uppercase, and sorts texts alphabetically.
 *
 * @author Bartosz Pałucki
 * @version 4.0
 */

public class StreamProcessor {

    /**
     * Filters the list to include only encoded texts.
     *
     * @param texts the list of texts
     * @return a list of encoded texts
     */
    public List<String> filterEncodedTexts(List<String> texts) {
        return texts.stream()
                .filter(text -> text.startsWith("ENCODED:"))
                .collect(Collectors.toList());
    }

    /**
     * Converts all texts to uppercase.
     *
     * @param texts the list of texts
     * @return a list of texts in uppercase
     */
    public List<String> convertToUpperCase(List<String> texts) {
        return texts.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /**
     * Sorts the list of texts alphabetically.
     *
     * @param texts the list of texts
     * @return a sorted list of texts
     */
    public List<String> sortTexts(List<String> texts) {
        return texts.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
