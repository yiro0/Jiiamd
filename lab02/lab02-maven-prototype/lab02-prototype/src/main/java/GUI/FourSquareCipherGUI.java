package GUI;

import Logic.FourSquareCipher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The FourSquareCipherGUI class represents the graphical user interface for the Four-Square Cipher application.
 * It provides functionality to encode and decode text using the Four-Square Cipher algorithm.
 */
public class FourSquareCipherGUI extends JFrame {

    private JTextField inputTextField, keyword1TextField, keyword2TextField;
    private JTextArea outputTextArea;
    private JButton encodeButton, decodeButton;
    private JTable historyTable;
    private JMenuBar menuBar;
    private FourSquareCipher cipher;

    /**
     * Constructs a new FourSquareCipherGUI with the specified cipher.
     *
     * @param cipher the Four-Square Cipher to be used for encoding and decoding
     */
    public FourSquareCipherGUI(FourSquareCipher cipher) {
        this.cipher = cipher;
        setTitle("Four-Square Cipher Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupMenuBar();
        setupTabbedPane();
        setupEnterKeyBinding();
        displayStartupMessage();
        setVisible(true);
    }

    /**
     * Sets the cipher to be used for encoding and decoding.
     *
     * @param cipher the Four-Square Cipher to be used
     */
    public void setCipher(FourSquareCipher cipher) {
        this.cipher = cipher;
    }

    /**
     * Sets up the menu bar with File and Help menus.
     */
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem usageItem = new JMenuItem("Usage");
        usageItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "How to use the Four-Square Cipher Application:\n" +
                        "1. Enter your text in the 'Input Text' field (Latin alphabet letters only).\n" +
                        "2. Enter two keywords (Latin alphabet letters only) for encoding/decoding.\n" +
                        "   - The keywords are required to initialize the cipher.\n" +
                        "   - After entering the keywords, the 'Encode' and 'Decode' buttons will be enabled.\n" +
                        "3. Click 'Encode' to encrypt or 'Decode' to decrypt.\n" +
                        "4. Results will appear below, and recent actions will display in the 'History' tab.\n" +
                        "\nNote: Ensure that only Latin alphabet letters are used in both the input text and keyword fields. Spaces and other characters are not allowed.",
                "Application Usage", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(usageItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Sets up the tabbed pane with Encode/Decode and History tabs.
     */
    private void setupTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel encodeDecodePanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel(new GridLayout(6, 1));
        inputTextField = new JTextField();
        keyword1TextField = new JTextField();
        keyword2TextField = new JTextField();
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        encodeButton = new JButton("Encode");
        encodeButton.addActionListener(new EncodeButtonListener());
        decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new DecodeButtonListener());

        controlsPanel.add(new JLabel("Input Text:"));
        controlsPanel.add(inputTextField);
        controlsPanel.add(new JLabel("Keyword 1:"));
        controlsPanel.add(keyword1TextField);
        controlsPanel.add(new JLabel("Keyword 2:"));
        controlsPanel.add(keyword2TextField);
        controlsPanel.add(encodeButton);
        controlsPanel.add(decodeButton);

        encodeDecodePanel.add(controlsPanel, BorderLayout.NORTH);
        encodeDecodePanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        JPanel historyPanel = new JPanel();
        String[] columns = {"Operation", "Input", "Output"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        historyTable = new JTable(model);
        historyPanel.add(new JScrollPane(historyTable));

        tabbedPane.add("Encode/Decode", encodeDecodePanel);
        tabbedPane.add("History", historyPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Sets up the key binding for the Enter key to confirm choices.
     */
    private void setupEnterKeyBinding() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "confirm");

        actionMap.put("confirm", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component focusedComponent = getFocusOwner();
                if (focusedComponent instanceof JButton) {
                    ((JButton) focusedComponent).doClick();
                } else if (focusedComponent instanceof JMenuItem) {
                    ((JMenuItem) focusedComponent).doClick();
                }
            }
        });
    }

    /**
     * Displays a startup message with instructions for keyboard navigation.
     */
    private void displayStartupMessage() {
        JOptionPane.showMessageDialog(this,
                "Welcome to the Four-Square Cipher Application!\n\n" +
                        "Keyboard Navigation Instructions:\n" +
                        "- Use TAB to move to the next component.\n" +
                        "- Use SHIFT+TAB to move to the previous component.\n" +
                        "- Press ENTER to confirm your choice.\n\n" +
                        "Press ENTER to start using the application.",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Returns the first keyword entered by the user.
     *
     * @return the first keyword
     */
    public String getKeyword1() {
        return keyword1TextField.getText();
    }

    /**
     * Returns the second keyword entered by the user.
     *
     * @return the second keyword
     */
    public String getKeyword2() {
        return keyword2TextField.getText();
    }

    /**
     * Listener for the Encode button.
     */
    private class EncodeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputTextField.getText();
            String keyword1 = keyword1TextField.getText();
            String keyword2 = keyword2TextField.getText();
            if (isValid(inputText) && isValid(keyword1) && isValid(keyword2)) {
                cipher = new FourSquareCipher(keyword1, keyword2, true); //Update constructor with new keyword
                String encodedText = cipher.encode(inputText);
                outputTextArea.setText(encodedText);
                updateHistory("Encode", inputText, encodedText); // Update history table
            } else {
                JOptionPane.showMessageDialog(FourSquareCipherGUI.this,
                        "Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Listener for the Decode button.
     */
    private class DecodeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputTextField.getText();
            String keyword1 = keyword1TextField.getText();
            String keyword2 = keyword2TextField.getText();
            if (isValid(inputText) && isValid(keyword1) && isValid(keyword2)) {
                cipher = new FourSquareCipher(keyword1, keyword2, true); //Update constructor with new keyword
                String decodedText = cipher.decode(inputText);
                outputTextArea.setText(decodedText);
                updateHistory("Decode", inputText, decodedText); // Update history table
            } else {
                JOptionPane.showMessageDialog(FourSquareCipherGUI.this,
                        "Invalid input! Only Latin alphabet letters are allowed in the input text and keyword fields.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Checks if the input text or keyword is valid (contains only Latin alphabet letters).
     *
     * @param input the input text or keyword
     * @return true if the input is valid, false otherwise
     */
    private boolean isValid(String input) {
        return input.matches("[A-Za-z]+");
    }

    /**
     * Updates the history table with the operation, input text, and output text.
     *
     * @param operation the operation performed (Encode or Decode)
     * @param input     the input text
     * @param output    the output text
     */
    private void updateHistory(String operation, String input, String output) {
        // Add new row to historyTable
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.addRow(new Object[]{operation, input, output});
    }
}

