package logic;

import gridModel.GridRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The FourSquareCipher class implements the Four-Square cipher encryption and decryption algorithm.
 * It uses four 5x5 grids to encode and decode text based on two keywords.
 *
 * @author Bartosz Pałucki
 * @version 4.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FourSquareCipher {

    /**
     * The first grid used in the Four-Square cipher.
     */
    private GridRecord grid1;

    /**
     * The second grid used in the Four-Square cipher.
     */
    private GridRecord grid2;

    /**
     * The third grid used in the Four-Square cipher.
     */
    private GridRecord grid3;

    /**
     * The fourth grid used in the Four-Square cipher.
     */
    private GridRecord grid4;

    /**
     * Constructs a FourSquareCipher with the specified keywords and initializes the grids if required.
     *
     * @param keyword1 the first keyword for the cipher
     * @param keyword2 the second keyword for the cipher
     * @param initializeGrids whether to initialize the grids with the keywords
     */
    public FourSquareCipher(String keyword1, String keyword2, boolean initializeGrids) {
        if (initializeGrids) {
            generateGridsWithKeywords(keyword1, keyword2);
        }
    }

    /**
     * Generates the four grids used in the Four-Square cipher with the given keywords.
     *
     * @param keyword1 the first keyword for the cipher
     * @param keyword2 the second keyword for the cipher
     */
    private void generateGridsWithKeywords(String keyword1, String keyword2) {
        String normalizeKeyword1 = keyword1.toUpperCase().replaceAll("[^A-Z]", "");
        String normalizeKeyword2 = keyword2.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder keywordContent1 = new StringBuilder();
        StringBuilder keywordContent2 = new StringBuilder();

        for (char letter : normalizeKeyword1.toCharArray()) {
            if (letter != 'J' && keywordContent1.indexOf(String.valueOf(letter)) == -1) {
                keywordContent1.append(letter);
            }
        }

        for (char letter : normalizeKeyword2.toCharArray()) {
            if (letter != 'J' && keywordContent2.indexOf(String.valueOf(letter)) == -1) {
                keywordContent2.append(letter);
            }
        }

        for (char letter = 'A'; letter <= 'Z'; letter++) {
            if (letter == 'J') continue; // Skip J
            if (keywordContent1.indexOf(String.valueOf(letter)) == -1) {
                keywordContent1.append(letter);
            }
            if (keywordContent2.indexOf(String.valueOf(letter)) == -1) {
                keywordContent2.append(letter);
            }
        }

        //Standard alphabet without 'J' for grid1 and grid4
        StringBuilder standardAlphabet = new StringBuilder();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            if (letter != 'J') {
                standardAlphabet.append(letter);
            }
        }

        // Initialize the grids
        grid1 = new GridRecord(new ArrayList<>());
        grid2 = new GridRecord(new ArrayList<>());
        grid3 = new GridRecord(new ArrayList<>());
        grid4 = new GridRecord(new ArrayList<>());


        // Fill the grinds with characters
        fillGrid(grid1.grid(), standardAlphabet.toString());
        fillGrid(grid2.grid(), keywordContent1.toString());
        fillGrid(grid3.grid(), keywordContent2.toString());
        fillGrid(grid4.grid(), standardAlphabet.toString());
    }

    /**
     * Fills the specified grid with the given content.
     *
     * @param grid the grid to be filled
     * @param content the content to fill the grid with
     */
    private void fillGrid(List<List<Character>> grid, String content) {
        int index = 0;
        for (int row = 0; row < 5; row++) {
            List<Character> rowList = new ArrayList<>();
            for (int col = 0; col < 5; col++) {
                rowList.add(content.charAt(index++));
            }
            grid.add(rowList);
        }
    }

    /**
     * Encodes the given text using the Four-Square cipher.
     *
     * @param text the text to be encoded
     * @return the encoded text
     */
    public String encode(String text) {
        String formattedText = formatText(text);
        validateText(formattedText);
        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = (i + 1 < formattedText.length()) ? formattedText.charAt(i + 1) : 'X';

            // Find the positions in the grids
            int[] pos1 = findPosition(grid1.grid(), firstChar);
            int[] pos2 = findPosition(grid4.grid(), secondChar);

            // Apply the encoding rules
            char encodedFirstChar = grid3.grid().get(pos1[0]).get(pos2[1]);
            char encodedSecondChar = grid2.grid().get(pos2[0]).get(pos1[1]);

            encodedText.append(encodedFirstChar).append(encodedSecondChar);
        }
        return encodedText.toString();
    }

    /**
     * Finds the position of the specified character in the given grid.
     *
     * @param grid the grid to search in
     * @param ch the character to find
     * @return an array containing the row and column of the character
     */
    private int[] findPosition(List<List<Character>> grid, char ch) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (grid.get(row).get(col) == ch) {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }

    /**
     * Decodes the given text using the Four-Square cipher.
     *
     * @param text the text to be decoded
     * @return the decoded text
     */
    public String decode(String text) {
        String formattedText = formatText(text);
        validateText(formattedText);
        StringBuilder decodedText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = (i + 1 < formattedText.length()) ? formattedText.charAt(i + 1) : 'X'; // X used for padding

            // Find the positions in the grids
            int[] pos1 = findPosition(grid3.grid(), firstChar);
            int[] pos2 = findPosition(grid2.grid(), secondChar);

            // Apply the decoding rules
            char decodedFirstChar = grid1.grid().get(pos1[0]).get(pos2[1]);
            char decodedSecondChar = grid4.grid().get(pos2[0]).get(pos1[1]);

            decodedText.append(decodedFirstChar).append(decodedSecondChar);
        }
        return decodedText.toString();
    }

    /**
     * Formats the given text by converting it to uppercase and removing non-alphabetic characters.
     *
     * @param text the text to be formatted
     * @return the formatted text
     */
    private String formatText(String text) {
        return text.toUpperCase().replaceAll("[^A-Z]", "");
    }

    /**
     * Validates the given text by checking if all characters are present in the grids.
     *
     * @param text the text to be validated
     * @throws IllegalArgumentException if a character in the text is not found in the grids
     */
    private void validateText(String text) {
        for (char ch: text.toCharArray()) {
            if (findPosition(grid1.grid(), ch) == null && findPosition(grid4.grid(), ch) == null) {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }
    }
}