import java.io.FileNotFoundException;
import java.util.*;


public class BaseGame {

	//Fields
	private enum PlayMode {
		PLAY_ONE_PLAYER, 
		PLAY_TWO_PLAYERS,
	}
	//reg for char number validation,usage: char.matches(regNumber)
	public static final String regNumber= "[0-9]";
	private final static String ValidCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";	//Valid characters
	private final static int MaxNumberOfTries = 7;											//Max # of tries
	protected final static String newline = System.getProperty("line.separator");
	private boolean wordIsGuessed;														//Word has been guessed (or not)
	private boolean wrong_guess;														//Wrong guess
	private int triesCount;																//# of unsuccessful tries
	private String wordToGuess;															//The word to guess
	private String soFarGuessedWord;													//So far guessed word
	private List<Character> alreadyEnteredCharacters;									//Already entered characters

	//Mutator
	protected void setWordToGuess(String word) {
		wordToGuess = word.toUpperCase();												//BILLY
		for (int i=0; i<wordToGuess.length(); i++) {									//_ _ _ _ _
			//Space is not considered as a character to guess
			soFarGuessedWord += (wordToGuess.charAt(i)!=' ') ? "_ ": "  ";
		}
		soFarGuessedWord = soFarGuessedWord.substring(0, soFarGuessedWord.length()-1);	//Delete last excessive space
		System.out.println("wordToGuess = "+wordToGuess);
		System.out.println("soFarGuessedWord = "+soFarGuessedWord);
	}

	//Constructor
	protected BaseGame() {
		wordIsGuessed = false;
		wrong_guess = false;
		triesCount = 0;
		wordToGuess= null;
		soFarGuessedWord = "";
		alreadyEnteredCharacters= new ArrayList<>();
	}

	//Pre playing methods
	public static void select_mode() throws FileNotFoundException {		// Select mode
		if (mode_selection() == PlayMode.PLAY_ONE_PLAYER)				//Check for invalid selection @mode_selection
			new OnePlayerGame();
		else {}
			new TwoPlayerGame();
	}

	private static PlayMode mode_selection() {
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

	//Playing methods
	protected void play() throws FileNotFoundException {				//Playing the game method (invoked outside)
		System.out.println("Playing the game now!");
		boolean game_ended;												//Final condition to end the game or not
		String input;
		OpenFile phases = new OpenFile();
		do {
			phases.print_phase(triesCount);								//Print the phase of the game (data/phases.txt)
			System.out.println("      "+soFarGuessedWord);				//Print the so far guessed word
			printSoFarUsedCharacters();									//Print the so far used characters
			input = enterNewCharacter();								//Player enters new character (or the whole word)
			checkInput(input);											//Check the input
			game_ended = wordIsGuessed || triesCount >= MaxNumberOfTries || wrong_guess;
		} while (!game_ended);											//Has the game ended?
		if(!wordIsGuessed) {
			phases.print_phase(MaxNumberOfTries); System.out.println();	//Last losing phase
		}
		printFinalMessage(wordIsGuessed, wordToGuess, triesCount);		//Print final message (win or lose)
	}

	private void printSoFarUsedCharacters() {							//Prints the so far used characters
		System.out.print("So far used characters [");					//e.g. [A O 4 C X]
		int size = alreadyEnteredCharacters.size();
		for (int i=0; i<size; i++) {
			System.out.print(alreadyEnteredCharacters.get(i));
			if (i!=size-1) System.out.print(' ');
		}
		System.out.println("]");
	}
	
	private String enterNewCharacter() {								//Player gives an input
		System.out.print("Enter a new character> ");
		String input = new Scanner(System.in).nextLine();
		return input.toUpperCase();										//For convenience make it uppercase
	}

	private void checkInput(String input) {								//Performs various checks on the input
		//Input checks
		if (emptyCheck(input)) return;									//Empty check
		if(input.length()>1) {											//Attempt to guess the whole word
			wordGuessCheck(input);										//Whole word guess check
			return;
		}
		//Character checks
		char inputChar = input.charAt(0);								//Get first character of the input
		if (invalidCharacterCheck(inputChar)) return;					//Invalid Character Check
		if (characterExistsAlreadyCheck(inputChar)) return;				//Check if character has already been entered
		characterGuessCheck(inputChar);									//Character is valid - check the guess
	}

	private boolean emptyCheck(String input) {
		boolean check = input.isEmpty();
		if (check)	System.out.println("You didn't enter anything.");	//Empty message
		return check;
	}

	private void wordGuessCheck(String input) {							//Input is a word
		boolean correct_guess = input.equals(wordToGuess);
		if (correct_guess) wordIsGuessed = true;						//Correct guess
		else wrong_guess = true;										//Wrong guess
	}

	private boolean invalidCharacterCheck(char input) {					//Invalid character check
		boolean check = IsValidCharacter(input);
		if (!check) {													//Invalid character
			System.out.print("Your input contains invalid character. ");
			System.out.println("Valid characters are letters from A to B and natural numbers from 0 to 9.");
			System.out.println("Please enter a valid character.");
		}
		return !check;													//The check is for invalid character, not valid
	}

	protected static boolean IsValidCharacter(char character) {
		return ValidCharacters.contains(String.valueOf(character));
	}

	private boolean characterExistsAlreadyCheck(char input) {			//Check if character has already been entered
		boolean check = inEnteredLetters(input, alreadyEnteredCharacters);
		if (check) {													//Character has already been entered
			System.out.println(input + " has already been entered.");
		}
		return check;
	}

	/* Check if letter is in enteredLetters array */
	private static boolean inEnteredLetters(char character, List<Character> enteredLetters) {
		return (enteredLetters!= null) && enteredLetters.contains(character);
	}

	private void characterGuessCheck(char input) {
		if (wordToGuess.contains(String.valueOf(input))) {				//Correct guessed character
			updateSoFarGuessedWord(input);
		}
		else {
			System.out.println(input + " is not in the word.");
			triesCount++;
		}
		alreadyEnteredCharacters.add(input);
	}

	private void updateSoFarGuessedWord(char input) {
		for (int i=0; i<wordToGuess.length(); i++) {
			if (wordToGuess.charAt(i)==input) {
				char[] charArray = soFarGuessedWord.toCharArray();
				charArray[2*i] = input;
				soFarGuessedWord = new String(charArray);
			}
		}
		if (!soFarGuessedWord.contains(String.valueOf('_'))) wordIsGuessed=true;	//Word has been guessed
		System.out.println("wordToGuess = "+wordToGuess);
		System.out.println("soFarGuessedWord = "+soFarGuessedWord);
	}

	private void printFinalMessage(boolean win, String correctWord, int triesCount) {
		if (win) {
			System.out.print("Congratulations, you guessed the word after " + triesCount + " unsuccessful ");
			System.out.println((triesCount==1) ? "try.":"tries.");
		}
		else {
			System.out.println("Sorry, you did not manage to guess the word.");
			System.out.println("The word was " + correctWord);
		}
	}

	//After playing methods
	protected void chooseWhatToDoNext() throws FileNotFoundException{
		System.out.println("Please choose what to do next.");
	}
}