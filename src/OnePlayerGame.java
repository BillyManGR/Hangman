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
		ChooseWhatToDoNext();
		
	}
	
	private void ChooseWhatToDoNext() {
		BaseGame.play();
	}



	private void printFinalMessage(boolean win, String correctWord){
		if(win){
			System.out.println("Congraturations, you guessed the word with only "+ triesCount  +"wrong tries");
		}
		else{
			System.out.println("Sorry, you did NOT manage to guess the word");
			System.out.println("The word was "+ correctWord);
		}
	}

	



	






}
