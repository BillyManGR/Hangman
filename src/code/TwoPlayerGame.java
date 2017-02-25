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


	protected String loadWord() {
		System.out.print("Make sure the other player is not looking at the screen"+ newline
				+ "Please enter  a word to guess"+ newline
				+ "Word to guess> ");

		while(true){
			Scanner scanner = new Scanner(System.in); // <-- don't close standard
		
			String word = scanner.nextLine();
			if(word.matches(pattern)){
				return word;
			}
			else{
				System.out.println(""
						+ "Invalid word ! Only alphanumeric characters"
						+ "allowed, space and the symbols  #& %");
				System.out.print("Word to guess> ");
			
			}
		}

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
			case LETTER_ALREADY_ENTERED_CORRECT:
				//triesCount++;
				break;
			case LETTER_ALREADY_ENTERED_NOT_CORRECT:
				//triesCount++;
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
		System.out.print(""
				+ "1 Play again and enter a new word to guess"+ newline
				+ "2 Switch playing mode"+ newline
				+ "3 Quit"+newline
				+ "Select> "
				);
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
