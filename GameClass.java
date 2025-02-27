import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

public class GameClass {

  static void start() {
    Scanner in = new Scanner(System.in);
    String correctWord = randomWord(); // 
    char[] correctArray = correctWord.toCharArray();
    char[] wrongArray = new char[26];
    char[] hiddenWord = new char[]{'*','*','*','*','*'};
    System.out.println(correctWord);
    String word = "";
    int attempt = 1;
    do {
    System.out.print(java.util.Arrays.toString(hiddenWord) + "\nEnter your word: ");
    word = in.nextLine();
    char[] wordArray = word.toCharArray();
    if (checkWord(wordArray, attempt, correctArray, hiddenWord, wrongArray)) {
      System.out.println(
          "Congrats ! you succsessfully guessed the word with " + attempt + " attempts.");
      break;
    }
    else 
    {
      attempt++;
      System.out.println("Incorrect ! here is what you have guessed: " + java.util.Arrays.toString(hiddenWord) + "\nAttempt: " + attempt + 
      "\nAttempts left: " + (6 - attempt) + "\nThese letters are wrong: " + java.util.Arrays.toString(wrongArray));
    }
  } while(attempt < 6);
    System.out.println("Game over !\n" + "Your stats:\n" + "Guessed: " + java.util.Arrays.toString(hiddenWord) + "\nAttempts taken: " + attempt);
  }

  static String randomWord() {
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

  public static long countWords() throws IOException {
    long result;
    try (FileReader input = new FileReader("words.txt");
        LineNumberReader count = new LineNumberReader(input); ) {
      while (count.skip(Long.MAX_VALUE) > 0) {}

      result = count.getLineNumber() + 1;
    }
    System.out.println(result);
    return result;
  }

  public static String getWord(long correctWordIndex) {
    long currentWordIndex = 0;
    String line = "";
    try {
      File file = new File("words.txt");
      FileReader fr = new FileReader(file);
      BufferedReader reader = new BufferedReader(fr);
      line = reader.readLine();
      while (currentWordIndex != correctWordIndex) {
        line = reader.readLine();
        currentWordIndex++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return line;
  }

  public static boolean checkWord(char[] wordArray, int attempt, char[] correctArray, char[] hiddenWord, char[] wrongArray) {
    if (wordArray.equals(correctArray)) {
      return true;
    } 
    else {
      for (int i = 0; i <= wordArray.length - 1; i++) {
        for (int j = 0; j <= wordArray.length - 1; j++) 
          if (wordArray[i] == correctArray[j]) {
            if(i == j) {
              hiddenWord[i] = Character.toUpperCase(wordArray[i]);
            }
            else {
              hiddenWord[i] = wordArray[i];
            }
          }
          else if (wordArray[i] != correctArray[j]) {
            wrongArray[i] = wordArray[j];
          }

      }
      return false;
    }

  }
}
