import java.util.ArrayList;
import java.util.Scanner;

public class OnePlayerGame extends BaseGame {
	private ArrayList<String> words;
	private int triesCount;
	// array to store already entered letters
	private int randomWordNumber;

	//construction
	public OnePlayerGame(){
		super();  
		words  = new ArrayList<String>();
		loadWords();
		triesCount=0;
		randomWordNumber =(int) (Math.random() * words.size());
		playOnePlayerGame(words.get(randomWordNumber));

	}



	@Override
	public void  loadWords(){
		//temporary test load
		words.add("first");
		words.add("second");
		words.add("dog");
		words.add("cat");
	}

	private void playOnePlayerGame(String wordToGuess) {
		char[] enteredLetters = new char[words.get(randomWordNumber).length()];
		do {
			//iterate through cycle as long as enterLetter returns true
			switch (enterLetter(wordToGuess, enteredLetters)) {
			case LETTER_NOT_IN_WORD:
				triesCount++;
				break;
			case  LETTER_NEW_CORRECT:
				break;
			case LETTER_ALREADY_ENTERED:
				triesCount++;
				break;

			}
		} while (! wordIsGuessed && !(triesCount==9));

		printFinalMessage(wordIsGuessed,wordToGuess);
		chooseWhatToDoNext();

	}

	@Override
	protected void chooseWhatToDoNext() {
		System.out.println("1 to play again with same category");
		System.out.println("2 to play again and change category");
		System.out.println("3 switch playing mode");
		System.out.println("4 Quit");
		System.out.println("Select >");
		while(true){
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection){
			case "1":
				System.out.println("Selected play again with same category");
				return;
			case "2":
				System.out.println("Selected play again and change category");
				return;
			case "3":
				BaseGame.play();
				return;
			default:
				System.out.println("Invalid selection");
			}
		}
	
	}



	private void printFinalMessage(boolean win, String correctWord){
		if(win){
			System.out.println("Congraturations, you guessed the word with only "+ triesCount  +" wrong tries");
		}
		else{
			System.out.println("Sorry, you did NOT manage to guess the word");
			System.out.println("The word was "+ correctWord);
		}
	}












}
