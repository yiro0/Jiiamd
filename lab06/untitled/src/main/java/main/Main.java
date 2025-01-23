package main;

import gui.FourSquareCipherGUI;
import logic.FourSquareCipher;

/**
 * The Main class is the entry point of the application.
 * It initializes the GUI and the Four-Square cipher with the provided keywords.
 *
 * @author Bartosz Pałucki
 * @version 4.0
 */
public class Main {
    /**
     * The main method initializes the GUI and the Four-Square cipher.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FourSquareCipherGUI gui = new FourSquareCipherGUI(null);
        String keyword1 = gui.getKeyword1(); // Get keyword1 from GUI
        String keyword2 = gui.getKeyword2(); // Get keyword2 from GUI
        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        gui.setCipher(cipher); // Update cipher in GUI
    }
}