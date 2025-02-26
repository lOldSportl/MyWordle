import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

public class GameClass {
    
    static void start() {
        Scanner in = new Scanner(System.in);
        String correctWord = randomWord();
        System.out.println(correctWord);
        String  word = "";
        int attempt = 0;
        System.out.print("*****\n"
        + "\n"
        + "Enter your word: ");
        word = in.nextLine();
        if(checkWord(word,attempt,correctWord)) {
            System.out.println("Congrats ! you succsessfully guessed the word with " + attempt + " attempts.");
            start();
        }


    }

    static String randomWord() {
        Random rnd = new Random();
        long correctWordIndex = 1;
        try {
            correctWordIndex = rnd.nextLong(countWords());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String correctWord = getWord(correctWordIndex);
     return correctWord;   
    }

    public static long countWords() throws IOException {
        long result;
                try
        (  
           FileReader input = new FileReader("words.txt");
           LineNumberReader count = new LineNumberReader(input);
        )
                {
           while (count.skip(Long.MAX_VALUE) > 0)
           {

           }
        
            result = count.getLineNumber() + 1;                                    
    }
    return result;
    }

    public static String getWord(long correctWordIndex) {
        long currentWordIndex = 0;
        String line = "test123";
        try {
            File file = new File("words.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            line = reader.readLine();
            while (currentWordIndex == correctWordIndex) {
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

    public static boolean checkWord(String word, int attempt, String correctWord) {
        if(correctWord.equals(word)) {
            return true;
        }
        else {attempt++; return false;}
    }


}
