package game;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        String input;
        do {
            System.out.print("Введите 5-буквенное слово: ");
            input = scanner.nextLine().trim().toLowerCase();
        } while (input.length() != 5);
        return input;
    }
}
