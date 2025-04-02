package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordProvider {
    private static final String WORDS_FILE = "words.txt";

    public String getRandomWord() {
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(WORDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return "apple"; // Значение по умолчанию
        }

        if (words.isEmpty()) {
            return "apple"; // Если файл пуст
        }

        return words.get(new Random().nextInt(words.size()));
    }
}
