package Logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FourSquareCipher class.
 */
public class FourSquareCipherTest {

    /**
     * Tests the encode method of the FourSquareCipher class.
     * Verifies that the input text is correctly encoded using the provided keywords.
     */
    @Test
    public void testEncode() {
        FourSquareCipher cipher = new FourSquareCipher("keyword1","keyword2" ,true);
        String input = "testinput";
        String expectedOutput = "SWQPAILSPX";
        String actualOutput = cipher.encode(input);
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests the decode method of the FourSquareCipher class.
     * Verifies that the encoded text is correctly decoded using the provided keywords.
     */
    @Test
    public void testDecode () {
        FourSquareCipher cipher = new FourSquareCipher("keyword1", "keyword2", true);
        String input = "encodedText";
        String expectedOutput = "BRKEGBFWDWYV";
        String actualOutput = cipher.decode(input);
        assertEquals(expectedOutput, actualOutput);
    }
}