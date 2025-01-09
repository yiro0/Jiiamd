package controller;

import model.FourSquareCipher;

import java.util.Scanner;

public class FourSquareCipherController {
    private FourSquareCipher cipher;

    public void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword 1: ");
        String keyword1 = scanner.nextLine();
        System.out.print("Enter keyword 2: ");
        String keyword2 = scanner.nextLine();
        cipher = new FourSquareCipher(keyword1, keyword2);

        System.out.print("Enter text to encode: ");
        String text = scanner.nextLine();
        String encodedText = cipher.encode(text);
        System.out.println("Encoded text: " + encodedText);
    }

    public void commandLineMode(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <keyword1> <keyword2> <text>");
            return;
        }
        String keyword1 = args[0];
        String keyword2 = args[1];
        String text = args[2];
        cipher = new FourSquareCipher(keyword1, keyword2);
        String encodedText = cipher.encode(text);
        System.out.println("Encoded text: " + encodedText);
    }
}