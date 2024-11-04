package GUI;

import Logic.FourSquareCipher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourSquareCipherGUI extends JFrame {

    private JTextField inputTextField;
    private JTextField keywordTextField;
    private JTextArea outputTextArea;
    private JButton encodeButton;
    private JButton decodeButton;
    private FourSquareCipher cipher;

    public FourSquareCipherGUI() {
        // GUI setup code (as seen in the previous code block)
        setTitle("Four-Square Cipher");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));

        // Input text field
        inputTextField = new JTextField();
        inputTextField.setToolTipText("Enter the text to " +
                "encode or decode");
        mainPanel.add(new JLabel("Input Text:"));
        mainPanel.add(inputTextField);

        // Keyword field
        keywordTextField = new JTextField();
        keywordTextField.setToolTipText("Enter the keyword (optional)");
        mainPanel.add(new JLabel("Keyword:"));
        mainPanel.add(keywordTextField);

        // Output area
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setToolTipText("The result of the encoding" +
                "/decoding will appear here");
        mainPanel.add(new JLabel("Output:"));
        mainPanel.add(new JScrollPane(outputTextArea));

        // Buttons
        JPanel buttonPanel = new JPanel();
        encodeButton = new JButton("Encode");
        encodeButton.setToolTipText("Click to encode the input " +
                "text using the Four-Square cipher");
        encodeButton.setMnemonic('E'); // Keyboard shortcut: Alt + E
        encodeButton.addActionListener(new EncodeButtonListener());

        decodeButton = new JButton("Decode");
        decodeButton.setToolTipText("Click to decode the input " +
                "text using the Four-Square cipher");
        decodeButton.setMnemonic('D'); // Keyboard shortcut: Alt + D
        decodeButton.addActionListener(new DecodeButtonListener());

        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);

        // Adding components to the window
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Accessibility context description
        encodeButton.getAccessibleContext().setAccessibleDescription("Encode" +
                " the provided text");
        decodeButton.getAccessibleContext().setAccessibleDescription("Decode" +
                "the provided text");

        // Show the window
        setVisible(true);
    }

    // Placeholder for the Encode button listener
    private class EncodeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Call encoding logic from the cipher class
        }
    }

    // Placeholder for the Decode button listener
    private class DecodeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Call decoding logic from the cipher class
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FourSquareCipherGUI::new);
    }
}

