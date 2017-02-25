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
	//quick fix make it global; TODO: consider refactor;
	private String lastFile;

	// construction
	public OnePlayerGame() {
		super();
		openFile = new OpenFile();
		lastFile = openFile.chooseFile();
		String word = loadWord(lastFile);
		setWordToGuess(word);
		playOnePlayerGame();

	}

	protected String loadWord( String fileName) {
		long startTime = System.currentTimeMillis();
		while(true){
			String newword= openFile.chooseWord(fileName);
			
			//if the word matches the patters return it 
			if(newword.matches(pattern)) return newword;
			
			long estimatedTime = System.currentTimeMillis()-startTime;
			
			//if no suitable word is found after 5 sec ask for another category
			if(estimatedTime>5000){ 
				fileName= openFile.chooseFile();
				lastFile=fileName;
				startTime = System.currentTimeMillis();
			}
		}

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
				//last category,pick a random word, set it in the "base game"
				setWordToGuess(loadWord(lastFile));
				playOnePlayerGame();
				return;
			case "2":
				System.out.println("Selected play again and change category");
				refreshWordStatus();
				//ask to choose a category,pick a random word, set it in the "base game"
				lastFile=openFile.chooseFile();
				setWordToGuess(loadWord(lastFile));
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
