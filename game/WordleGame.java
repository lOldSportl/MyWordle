package game;

public class WordleGame {
    private static final int MAX_ATTEMPTS = 6;
    private final WordProvider wordProvider;
    private final WordChecker wordChecker;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private String correctWord;
    private char[] displayedWord;

    public WordleGame() {
        this.wordProvider = new WordProvider();
        this.wordChecker = new WordChecker();
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    public void start() {
        correctWord = wordProvider.getRandomWord();
        System.out.println(correctWord);
        displayedWord = new char[] {'*', '*', '*', '*', '*'};

        int attempt = 0;
        boolean guessed = false;

        outputHandler.displayWelcomeMessage();

        while (attempt < MAX_ATTEMPTS) {
            outputHandler.displayWord(displayedWord);
            String userWord = inputHandler.getUserInput();

            if (userWord.equals(correctWord)) {
                outputHandler.displayWinMessage(correctWord, attempt + 1);
                guessed = true;
                break;
            }

            displayedWord = wordChecker.checkWord(userWord, correctWord, displayedWord);
            attempt++;

            outputHandler.displayAttemptResult(displayedWord, attempt, MAX_ATTEMPTS);
        }

        if (!guessed) {
            outputHandler.displayGameOver(correctWord);
        }
    }
}
