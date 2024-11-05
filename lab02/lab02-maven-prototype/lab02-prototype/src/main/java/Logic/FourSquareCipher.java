package Logic;

import java.util.*;

/**
 * The FourSquareCipher class implements the Four-Square cipher encryption and decryption algorithm.
 * It uses four 5x5 grids to encode and decode text based on two keywords.
 */
public class FourSquareCipher {

    private char[][] grid1;
    private char[][] grid2;
    private char[][] grid3;
    private char[][] grid4;

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
        // Normalize the keywords
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

        // Add remaining letters to complete the keyword grids
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
        grid1 = new char[5][5];
        grid2 = new char[5][5];
        grid3 = new char[5][5];
        grid4 = new char[5][5];

        // Fill the grinds with characters
        fillGrid(grid1, standardAlphabet.toString());
        fillGrid(grid2, keywordContent1.toString());
        fillGrid(grid3, keywordContent2.toString());
        fillGrid(grid4, standardAlphabet.toString());
    }

    /**
     * Fills the specified grid with the given content.
     *
     * @param grid the grid to be filled
     * @param content the content to fill the grid with
     */
    private void fillGrid(char[][] grid, String content) {
        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                grid[row][col] = content.charAt(index++);
            }
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
        StringBuilder encodedText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = (i + 1 < formattedText.length()) ? formattedText.charAt(i + 1) : 'X'; // X used for padding

            // Find the positions in the grids
            int[] pos1 = findPosition(grid1, firstChar);
            int[] pos2 = findPosition(grid4, secondChar);

            // Apply the encoding rules
            char encodedFirstChar = grid3[pos1[0]][pos2[1]];
            char encodedSecondChar = grid2[pos2[0]][pos1[1]];

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
    private int[] findPosition(char[][] grid, char ch) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (grid[row][col] == ch) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // Character not found
    }

    /**
     * Decodes the given text using the Four-Square cipher.
     *
     * @param text the text to be decoded
     * @return the decoded text
     */
    public String decode(String text) {
        String formattedText = formatText(text);
        StringBuilder decodedText = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = (i + 1 < formattedText.length()) ? formattedText.charAt(i + 1) : 'X'; // X used for padding

            // Find the positions in the grids
            int[] pos1 = findPosition(grid3, firstChar);
            int[] pos2 = findPosition(grid2, secondChar);

            // Apply the decoding rules
            char decodedFirstChar = grid1[pos1[0]][pos2[1]];
            char decodedSecondChar = grid4[pos2[0]][pos1[1]];

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
}

