import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

public class GameClass {

  private static final Scanner sc = new Scanner(System.in);

  static void start() {
    String correctWord = randomWord(); // загаданное слово
    // char[] correctArray = correctWord.toCharArray();
    char[] hiddenWord = new char[] {'*', '*', '*', '*', '*'}; // слово, которое отображается
    // System.out.println(correctWord); // вывод загаданного слова(для отладки)
    String word = ""; // введеное слово
    int attempt = 1; // попытки
    do {
      System.out.print(java.util.Arrays.toString(hiddenWord) + "\nEnter your word: ");
      word = sc.nextLine();
      System.out.println("Word written");
      if(checkWordInList(word)){
        System.out.println("Word exists in list");
        // char[] wordArray = word.toCharArray();
        if (checkWord(word, correctWord, hiddenWord)) {
          System.out.println("Congrats ! you succsessfully guessed the word");
          break;
        } 
      }
      else {
        System.out.println(
            "Incorrect ! here is what you have guessed: "
                + java.util.Arrays.toString(hiddenWord)
                + "\nAttempt: "
                + attempt
                + "\nAttempts left: "
                + (6 - attempt));
        attempt++;
      }
    } while (attempt < 7);
    System.out.println(
        "Game over !"
            + " Your stats:\n"
            + "The word was: "
            + correctWord
            + "\nAttempts taken: "
            + (attempt));   
  }

  static String randomWord() { // генерация случайного индекса из общего количества индексов в файле со словами
    Random rnd = new Random();
    long correctWordIndex = 1;
    try {
      correctWordIndex = rnd.nextLong(countWords());
    } catch (IOException e) {
      e.printStackTrace();
    }
    String correctWord = getWord(correctWordIndex);
    return correctWord;
  }

  public static long countWords() throws IOException { // подсчет общего числа слов
    long result;
    try (FileReader input = new FileReader("words.txt");
        LineNumberReader count = new LineNumberReader(input); ) {
      while (count.skip(Long.MAX_VALUE) > 0) {}

      result = count.getLineNumber() + 1;
    }
    System.out.println(result);
    return result;
  }

  public static String getWord(long correctWordIndex) { // получение  нужного слова из файла
    long currentWordIndex = 0;
    String line = "";
    try {
      File file = new File("words.txt");
      FileReader fr = new FileReader(file);
      try (BufferedReader reader = new BufferedReader(fr)) {
        line = reader.readLine();
        while (currentWordIndex != correctWordIndex) {
          line = reader.readLine();
          currentWordIndex++;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return line;
  }

  public static boolean checkWord(String userWord, String targetWord, char[] hiddenLetter) {  // проверка слова

    if (userWord.equals(targetWord)) {
      return true;
    } else {
      char[] userLetters = userWord.toCharArray();
      char[] targetLetters = targetWord.toCharArray(); 
      for (int i = 0; i < userLetters.length; i++){
        if (Character.compare(userLetters[i], targetLetters[i]) == 0){
            hiddenLetter[i] = Character.toUpperCase(userLetters[i]);
        }
        else if (new String(targetLetters).contains(String.valueOf(userLetters[i]))){
          hiddenLetter[i] = userLetters[i];
        }
      }
    return false;
    }
  }

  public static boolean checkWordInList(String word) {
    try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
      while (br.readLine() != null){
        if (br.readLine().equals(word)){
          return true;
        }
      }
    }
    catch (IOException e) {
      System.out.println("Ошибка при чтении файла"); 
    }
    return false;
  }
}
