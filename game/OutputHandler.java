package game;

import java.util.Arrays;

public class OutputHandler {
    public void displayWelcomeMessage(/*message*/) {
        System.out.println(/*message*/ "Добро пожаловать в Wordle! Попробуйте угадать слово за 6 попыток.");
    }

    public void displayWord(char[] displayedWord) {
        System.out.println("Текущее состояние: " + Arrays.toString(displayedWord));
    }

    public void displayAttemptResult(char[] displayedWord, int attempt, int maxAttempts) {
        System.out.println("Попытка " + attempt + " из " + maxAttempts);
    }

    public void displayWinMessage(String correctWord, int attempts) {
        System.out.println("Поздравляю! Вы угадали слово '" + correctWord + "' за " + attempts + " попыток!");
    }

    public void displayGameOver(String correctWord) {
        System.out.println("Игра окончена! Загаданное слово: " + correctWord);
    }
}
