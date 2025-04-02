package game;

public class WordChecker {
    public char[] checkWord(String userWord, String correctWord, char[] displayedWord) {
        char[] userLetters = userWord.toCharArray();
        char[] correctLetters = correctWord.toCharArray();
        char[] result = new char[5];
        boolean[] used = new boolean[5];

        // 1. Проверка точного совпадения (ЗАГЛАВНАЯ буква)
        for (int i = 0; i < 5; i++) {
            if (userLetters[i] == correctLetters[i]) {
                result[i] = Character.toUpperCase(userLetters[i]);
                used[i] = true;
            } else {
                result[i] = '*'; // Пока звёздочка
            }
        }

        // 2. Проверка наличия буквы не на своём месте (прописная)
        for (int i = 0; i < 5; i++) {
            if (result[i] == '*') { 
                for (int j = 0; j < 5; j++) {
                    if (!used[j] && userLetters[i] == correctLetters[j]) {
                        result[i] = userLetters[i]; // Прописная буква
                        used[j] = true;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
