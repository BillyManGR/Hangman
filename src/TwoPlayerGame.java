import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoPlayerGame extends BaseGame {

	// constructor
	public TwoPlayerGame() throws FileNotFoundException{
		super();
		playTwoPlayerGame();
	}

	private void playTwoPlayerGame() throws FileNotFoundException{
		String word = loadWord();
		//System.out.println("Entered word " + word);
		setWordToGuess(word);
		play();
		chooseWhatToDoNext();
	}

	private String loadWord() {
		System.out.println("Make sure the other player is not looking at the screen"+ newline
				+ "Please enter  a word to guess. The word should only contain letters from A to B or numbers from	0 to 9");
		return checkInputWord();														//Check the input
	}

	private String checkInputWord() {											//I suppose it can be enumerated
		boolean validWord = false;
		String input="";
		while (!validWord) {
			System.out.print("Word to guess> ");
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextLine().toUpperCase();
			if (inputLengthCheck(input))	continue;							//Length less than 2 characters
			if (notValidCharacterCheck(input))	continue;						//Not valid character - try again
			if (inputBeginsOrEndsWithSpace(input)) continue;					//Begins or ends with space - try again
			if (subwordIsAcharacterCheck(input)) continue;						//Subword is a character - try again
			validWord = true;													//All checks are OK - word is valid
		}
		return input;
	}

	private boolean inputLengthCheck(String input) {			//Check if input is less than 2 characters
		boolean check = input.length()<2;
		if (check) System.out.println("Your word has less than two characters. Try again");
		return check;
	}

	private boolean notValidCharacterCheck(String input) {		//Invalid character which is not space
		boolean result = false;									//Assume it is valid
		for (int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
			//System.out.println("Current char = "+c);
			if (!IsValidCharacter(c) && c!=' ') {
				System.out.println("Your word contains invalid character(s). Try again");
				result = true;	break;
			}
		}
		return result;
	}

	private boolean inputBeginsOrEndsWithSpace(String input) {		//Check if input begins or ends with space
		boolean check = (input.charAt(0)==' ' || input.charAt(input.length()-1)==' ');
		if (check) System.out.println("Your word begins or ends with space. Try again");
		return check;
	}

	private boolean subwordIsAcharacterCheck(String input) {        //Check if input begins or ends with space
		boolean check = false;										//Assume subword(s) are not one character
		String[] subwords = input.split(" "); 					//String array, each subword is text between spaces
		for (String subword: subwords) {							//If subword is character, stop
			if (subword.length()<2) {
				System.out.println("Your word subwords that are characters. Try again");
				check = true; break;
			}
		}
		return check;
	}

	@Override
	protected void chooseWhatToDoNext() throws FileNotFoundException{
		System.out.print("Press:"+ newline
				+ "1 to Play again and enter a new word to guess"+ newline
				+ "2 to Switch playing mode"+ newline
				+ "3 to Quit"+newline
				+ "Select> "
				);
		nextGameSelection();
	}

	private void nextGameSelection() throws FileNotFoundException {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
				case "1":
					System.out.println("Selected to play again and enter a new word to guess");
					new TwoPlayerGame();
					return;
				case "2":
					System.out.println("Selected to switch mode");
					BaseGame.select_mode();
					return;
				case "3":
					System.out.println("Thank you for your interest in the game. Goodbye :)");
					System.exit(0);
				default:
					System.out.print("Invalid selection"+ newline
							+ "Select> ");
			}
		}
	}
}
