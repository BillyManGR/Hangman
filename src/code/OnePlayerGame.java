package code;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OnePlayerGame extends BaseGame {

	private OpenFile openFile;

	// don't like the global fileName here; but anyway
	private String fileName;

	// construction
	public OnePlayerGame() {
		super();
		openFile = new OpenFile();
		fileName = openFile.chooseFile();
		String word = loadWord();
		setWordToGuess(word);
		playOnePlayerGame();

	}

	@Override
	protected String loadWord() {
		return openFile.chooseWord(fileName);

	}

	private void playOnePlayerGame() {
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
		System.out.print(""
				+ "1 to play again with same category"+ newline
				+ "2 to play again and change category"+ newline
				+ "3 switch playing mode"+ newline
				+ "4 Quit"+ newline
				+ "Select> "
				);
		
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
			case "1":
				System.out.println("Selected play again with same category");
				refreshWordStatus();
				setWordToGuess(loadWord());
				playOnePlayerGame();
				return;
			case "2":
				System.out.println("Selected play again and change category");
				refreshWordStatus();
				fileName = openFile.chooseFile();
				setWordToGuess(loadWord());
				playOnePlayerGame();
				return;
			case "3":
				System.out.println("Selected swith mode");
				BaseGame.play();
				return;
			case "4":
				System.out.println("Thank you for your interest in the game");
				System.exit(0);
			default:
				System.out.print("Invalid selection"+ newline
						+ "Select> ");
			}
		}

	}

}
