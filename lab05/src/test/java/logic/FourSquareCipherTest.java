package logic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FourSquareCipher class.
 *
 * @author Bartosz Pałucki
 * @version 4.0
 */
public class FourSquareCipherTest {

    // TODO: Btw what about testing the testDecode method with incorrect data?
    // We need to test the decode method with incorrect data to check for invalid input handling.
    // And so thus we need to add a new test method for that.

    /**
     * Tests the encode method of the FourSquareCipher class with correct data.
     * Verifies that the input text is correctly encoded using the provided keywords.
     */
    @ParameterizedTest
    @CsvSource({
            "testinput, QWPPTHISNX",
            "exampletext, YZECCIWSYZNX",
            "ciphertext, WNGBESQWXP"
    })
    public void testEncode(String input, String expectedOutput) {
        FourSquareCipher cipher = new FourSquareCipher("keywordone", "keywordtwo", true);
        String actualOutput = cipher.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests the decode method of the FourSquareCipher class with correct data.
     * Verifies that the encoded text is correctly decoded using the provided keywords.
     */
    @ParameterizedTest
    @CsvSource({
            "QWPPTHISNX, testinput",
            "YZECCIWSYZNX, exampletext",
            "WNGBESQWXP, ciphertext"
    })
    public void testDecode(String input, String expectedOutput) {
        FourSquareCipher cipher = new FourSquareCipher("keywordone", "keywordtwo", true);
        String actualOutput = cipher.decode(input).replaceAll("X+$", "").toLowerCase();
        assertEquals(expectedOutput.toLowerCase(), actualOutput);
    }

    // Doing this test to check if the encode method can handle invalid input.
    // Same should be done with the decode method.
    // It's not finished yet, but it's a good start.
    // It's not finished because we need to indirectly test the private method.
    // However, we basically need to test the UI that uses the encode/decode method. And not the Logic itself.
    // This needs to be discussed, as before w ith Exception handling, we 'could' just throw an exception.
//    /**
//     * Tests the encode method with incorrect data to check for invalid input handling.
//     */
    /*
    @ParameterizedTest
    @CsvSource({
            "'', Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields.",
            "1234, Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields.",
            "invalid!@#, Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields."
    })
    public void testEncodeWithInvalidData(String input, String expectedMessage) throws Exception {
        FourSquareCipherGUI gui = new FourSquareCipherGUI(new FourSquareCipher("keywordone", "keywordtwo", true));

        // Use reflection to access private methods
        Method getInputTextField = FourSquareCipherGUI.class.getDeclaredMethod("getInputTextField");
        getInputTextField.setAccessible(true);
        JTextField inputTextField = (JTextField) getInputTextField.invoke(gui);
        inputTextField.setText(input);

        Method getKeyword1TextField = FourSquareCipherGUI.class.getDeclaredMethod("getKeyword1TextField");
        getKeyword1TextField.setAccessible(true);
        JTextField keyword1TextField = (JTextField) getKeyword1TextField.invoke(gui);
        keyword1TextField.setText("keywordone");

        Method getKeyword2TextField = FourSquareCipherGUI.class.getDeclaredMethod("getKeyword2TextField");
        getKeyword2TextField.setAccessible(true);
        JTextField keyword2TextField = (JTextField) getKeyword2TextField.invoke(gui);
        keyword2TextField.setText("keywordtwo");

        Method getEncodeButton = FourSquareCipherGUI.class.getDeclaredMethod("getEncodeButton");
        getEncodeButton.setAccessible(true);
        JButton encodeButton = (JButton) getEncodeButton.invoke(gui);

        // Simulate button click
        ActionEvent event = new ActionEvent(encodeButton, ActionEvent.ACTION_PERFORMED, "Encode");
        encodeButton.getActionListeners()[0].actionPerformed(event);

        // Check the error message
        JOptionPane optionPane = new JOptionPane();
        String actualMessage = optionPane.showInputDialog(gui, "Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields.");
        assertEquals(expectedMessage, actualMessage);
    }
     */

    /**
     * Tests the encode method with boundary situations.
     */
    @ParameterizedTest
    @CsvSource({
            "a, YT",
            "ab, EK",
            "abc, EKYV",
            "abcd, EKWY",
            "abcde, EKWYYZ"
    })
    public void testEncodeWithBoundaryData(String input, String expectedOutput) {
        FourSquareCipher cipher = new FourSquareCipher("keywordone", "keywordtwo", true);
        String actualOutput = cipher.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests the decode method with boundary situations.
     */
    @ParameterizedTest
    @CsvSource({
            "YT, a",
            "EK, ab",
            "EKYV, abc",
            "EKWY, abcd",
            "EKWYYZ, abcde"
    })
    public void testDecodeWithBoundaryData(String input, String expectedOutput) {
        FourSquareCipher cipher = new FourSquareCipher("keywordone", "keywordtwo", true);
        String actualOutput = cipher.decode(input).replaceAll("X+$", "").toLowerCase();
        assertEquals(expectedOutput.toLowerCase(), actualOutput);
    }
}