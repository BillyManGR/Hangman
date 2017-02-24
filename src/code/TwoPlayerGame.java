package code;
import java.util.Scanner;
public class TwoPlayerGame extends BaseGame {

	// constructor
	public TwoPlayerGame() {
		super();
		String word = loadWord();
		System.out.println("Entered word " + word);
		setWordToGuess(word);
		playTwoPlayerGame();
	

	}

	@Override
	protected String loadWord() {
		System.out.println("Make sure the other player is not looking at the screen");
		System.out.println("Please enter  a word to guess");
		System.out.println("Word to guess> ");
		// TODO: make some error handling for wrong input
		Scanner scanner = new Scanner(System.in); // <-- don't close standard
													// input! it breaks
		String word = scanner.nextLine();
		return word;
	}

	/**
	 * This method is exactly the same as playOnePlayerGame will keep them
	 * separate if you want to implement somewhat different logic
	 * 
	 * otherwise refactor by extracting to base class
	 * 
	 */
	private void playTwoPlayerGame() {
		int triesCount = 0;
		do {
			// iterate through cycle as long as enterLetter returns true
			switch (enterLetter()) {
			case LETTER_NOT_IN_WORD:
				triesCount++;
				break;
			case LETTER_NEW_CORRECT:
				break;
			case LETTER_ALREADY_ENTERED:
				triesCount++;
				break;
			case LETTER_EMPTY_STRING:
				System.out.println("You entered an empty string");
				triesCount++;
				break;

			}
		} while (!wordIsGuessed && !(triesCount == 9));

		printFinalMessage(wordIsGuessed, getWordToGuess(), triesCount);
		chooseWhatToDoNext();

	}

	@Override
	protected void chooseWhatToDoNext() {
		System.out.println("1 Play again and enter a new word to guess");
		System.out.println("2 Switch playing mode");
		System.out.println("3 Quit");
		System.out.println("Select >");
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
			case "1":
				System.out.println("Selected play again");
				refreshWordStatus();
				setWordToGuess(loadWord());
				playTwoPlayerGame();
				return;
			case "2":
				System.out.println("Selected swith mode");
				BaseGame.play();
				return;
			case "3":
				System.out.println("Thank you for your interest in the game");
				System.exit(0);
			default:
				System.out.println("Invalid selection");
			}
		}
	}
}
