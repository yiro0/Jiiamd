package Main;

import controller.FourSquareCipherController;

public class Main {
    public static void main(String[] args) {
        FourSquareCipherController controller = new FourSquareCipherController();
        if (args.length == 0) {
            controller.interactiveMode();
        } else {
            controller.commandLineMode(args);
        }
    }
}