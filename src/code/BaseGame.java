package code;
import java.util.Scanner;


public class BaseGame {
	public enum LetterStates {
		LETTER_NOT_IN_WORD, 
		LETTER_NEW_CORRECT, 
		LETTER_ALREADY_ENTERED,
		LETTER_EMPTY_STRING,
	}

	public enum PlayMode {
		PLAY_ONE_PLAYER, 
		PLAY_TWO_PLAYERS,
	}
	protected boolean wordIsGuessed;
	private String wordToGuess;

	private char[] alreadyEnteredLetters;
	
	public BaseGame() {
		wordIsGuessed = false;
		wordToGuess= null;
		alreadyEnteredLetters= null;
	}

	protected void refreshWordStatus() {
		wordIsGuessed = false;
	}

	protected String loadWord() {
		return null;
	}

	public LetterStates enterLetter() {
		System.out.print("Enter a new lettter ");
		System.out.print(" > ");
		String keyboard = new Scanner(System.in).nextLine();
		System.out.println("Keyboard scnner" + keyboard);

		if (keyboard.isEmpty())
			return LetterStates.LETTER_EMPTY_STRING;

		char inputChar = keyboard.charAt(0);

		if (inEnteredLetters(inputChar, alreadyEnteredLetters)) {
			System.out.println(inputChar + " is already in the word");
			printWord(wordToGuess, alreadyEnteredLetters);
			return LetterStates.LETTER_ALREADY_ENTERED;
		} 
		else if (wordToGuess.contains(String.valueOf(inputChar))) {
			alreadyEnteredLetters[findEmptyPosition(alreadyEnteredLetters)] = inputChar;
			printWord(wordToGuess, alreadyEnteredLetters);
			return LetterStates.LETTER_NEW_CORRECT;
		} 
		else {
			System.out.println(inputChar + " is not in the word");
			return LetterStates.LETTER_NOT_IN_WORD;
		}
	}

	/* Check if letter is in enteredLetters array */
	private static boolean inEnteredLetters(char letter, char[] enteredLetters) {
		return new String(enteredLetters).contains(String.valueOf(letter));
	}

	private void printWord(String wordToGuess, char[] enteredLetters2) {
		// Iterate through all letters in word
		boolean wordGuessed = true;
		for (int i = 0; i < wordToGuess.length(); i++) {
			char letter = wordToGuess.charAt(i);

			if (inEnteredLetters(letter, enteredLetters2))
				System.out.print(letter);
			else {
				System.out.print('*');
				wordGuessed = false;
			}
		}
		if (wordGuessed)
			wordIsGuessed = true;
		System.out.print('\n');

	}

	/*
	 * Find first empty position in array of entered letters (one with code
	 * \u0000)
	 */
	private static int findEmptyPosition(char[] enteredLetters) {
		int i = 0;
		while (enteredLetters[i] != '\u0000')
			i++;
		return i;
	}

	protected void chooseWhatToDoNext() {
		System.out.println("Please choose what to do next");
	}

	// Select mode
	public static void play() {
		if (mode_selection() == PlayMode.PLAY_ONE_PLAYER)
			new OnePlayerGame();
		else
			new TwoPlayerGame();
	}

	public static PlayMode mode_selection() {
		while (true) {
			System.out.println("Please enter '1' for 1-Player or '2' for 2-players mode.");
			System.out.println("Selection> ");
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
			case "1":
				System.out.println("Selected mode is 1 Player");
				return PlayMode.PLAY_ONE_PLAYER;
			case "2":
				System.out.println("Selected mode is 2 players");
				return PlayMode.PLAY_TWO_PLAYERS;
			default:
				System.out.println("Invalid selection. Please enter '1' for 1-Player or '2' for 2-Players mode.");
			}
		}
	}

	protected void printFinalMessage(boolean win, String correctWord, int triesCount) {
		if (win) {
			System.out.println("Congraturations, you guessed the word with only " + triesCount + " wrong tries");
		} else {
			System.out.println("Sorry, you did NOT manage to guess the word");
			System.out.println("The word was " + correctWord);
		}
	}
	
	public void setWordToGuess(String wordToGuess) {
		this.wordToGuess = wordToGuess;
		//this is not supposed to be here
		alreadyEnteredLetters = new char[wordToGuess.length()];
	}
	
	public String getWordToGuess() {
		return wordToGuess;
	}


}
