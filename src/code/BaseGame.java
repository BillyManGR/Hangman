package code;
import java.util.Scanner;


public class BaseGame {
	public enum LetterStates {
		LETTER_NOT_IN_WORD, 
		LETTER_NEW_CORRECT, 
		LETTER_ALREADY_ENTERED_CORRECT,
		LETTER_EMPTY_STRING, 
		LETTER_ALREADY_ENTERED_NOT_CORRECT,
	}

	public enum PlayMode {
		PLAY_ONE_PLAYER, 
		PLAY_TWO_PLAYERS,
	}
	public static String newline = System.getProperty("line.separator");
	public boolean wordIsGuessed;
	protected boolean showHiddenWord; //print the starts on first try
	private String wordToGuess;

	private char[] enteredLettersAll;
	private char[] enterLettersCorrect;

	public BaseGame() {
		wordIsGuessed = false;
		wordToGuess= null;
		showHiddenWord= true;
		//potential break point; imagine a word consists of 100 different chars :O
		enteredLettersAll = new char[100];
		//again this can be as much as  through all the unicode encoing table
		enterLettersCorrect= new char[100];
	}

	protected void refreshWordStatus() {
		wordIsGuessed = false;
		showHiddenWord= true;
		enterLettersCorrect= new char[100];
		enteredLettersAll = new char[100];
	}

	protected String loadWord() {
		return null;
	}

	public LetterStates enterLetter() {
		printWordFirstTime();
		System.out.print("Enter a new lettter> ");
		String keyboard = new Scanner(System.in).nextLine();

		//don't allow for empty input (just enter)
		if (keyboard.isEmpty()) return LetterStates.LETTER_EMPTY_STRING;
		//read the char
		char inputChar = Character.toUpperCase(keyboard.charAt(0));
		
		//if is already entered letter, but it is not part of the word
		if (inEnteredLetters(inputChar,enteredLettersAll) && !inEnteredLetters(inputChar,enterLettersCorrect)) {
			System.out.println(inputChar + " has been already entered");
			printWord(wordToGuess, enteredLettersAll);
			return LetterStates.LETTER_ALREADY_ENTERED_NOT_CORRECT;
		} 
		//add to the set of all entered letters
		if(!(inEnteredLetters(inputChar, enteredLettersAll)))
			enteredLettersAll[findEmptyPosition(enteredLettersAll)] = inputChar;
		//the letter is already entered and is correct
		if (inEnteredLetters(inputChar,enterLettersCorrect)) {
			System.out.println(inputChar + " is already in the word");
			printWord(wordToGuess, enteredLettersAll);
			return LetterStates.LETTER_ALREADY_ENTERED_CORRECT;
		} 
		//the letters is new and it is correct
		else if (wordToGuess.contains(String.valueOf(inputChar))) {
			enterLettersCorrect[findEmptyPosition(enterLettersCorrect)] = inputChar;
			printWord(wordToGuess, enteredLettersAll);
			return LetterStates.LETTER_NEW_CORRECT;
		}  
		//the letters is new and is not correct
		else {
			System.out.println(inputChar + " is not in the word");
			printWord(wordToGuess, enteredLettersAll);
			return LetterStates.LETTER_NOT_IN_WORD;
		}
	}
	

	private void printWordFirstTime() {
		if (showHiddenWord) {printWord(wordToGuess, enteredLettersAll);showHiddenWord=false;}	
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
		printAlreadyEnteredLetters(enteredLetters2);
		System.out.print(newline);

	}

	private void printAlreadyEnteredLetters( char[] letters) {
		System.out.print("  used letters:   "+ "["+new String(letters).replace("\0", "")+"]");
		
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
		this.wordToGuess = wordToGuess.toUpperCase();
		//this is not supposed to be here
	}

	public String getWordToGuess() {
		return wordToGuess;
	}


}
