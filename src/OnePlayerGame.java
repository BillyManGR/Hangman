import java.util.ArrayList;
import java.util.Scanner;

public class OnePlayerGame {
	private ArrayList<String> words;
	private int triesCount;
	private  boolean wordIsGuessed;
	public enum LetterStates {
		LETTER_NOT_IN_WORD,
		LETTER_NEW_CORRECT,
		LETTER_ALREADY_ENTERED,
	}
	// array to store already entered letters
	private int randomWordNumber;

	//construction
	public OnePlayerGame(){
		words  = new ArrayList<String>();
		loadWords();
		triesCount=0;
		wordIsGuessed= false;
		randomWordNumber =(int) (Math.random() * words.size());
		playOnePlayerGame(words.get(randomWordNumber));

	}



	/*public visibility to allow for easy testing */
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
		
		System.out.println("Sorry, you did NOT manage to guess the word");
		System.out.println("The word was "+ wordToGuess);

	}

	private LetterStates enterLetter(String wordToGuess, char[] alreadyEnteredWords) {
		System.out.print("Enter a new lettter ");
		System.out.print(" > ");
		char inputChar = new Scanner(System.in).nextLine().charAt(0);

		if (inEnteredLetters(inputChar, alreadyEnteredWords)) {
			System.out.println(inputChar + " is already in the word");
			printWord(wordToGuess, alreadyEnteredWords);
			return LetterStates.LETTER_ALREADY_ENTERED;
		}
		else if (wordToGuess.contains(String.valueOf(inputChar))) {
			alreadyEnteredWords[findEmptyPosition(alreadyEnteredWords)] = inputChar;
			printWord(wordToGuess, alreadyEnteredWords);
			return  LetterStates.LETTER_NEW_CORRECT;
		}
		else {
			System.out.println(inputChar + " is not in the word");
			return LetterStates.LETTER_NOT_IN_WORD;
		}
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
		if(wordGuessed) wordIsGuessed= true;
		System.out.print('\n');

	}




	/* Check if letter is in enteredLetters array */
	public static boolean inEnteredLetters(char letter, char[] enteredLetters) {
		return new String(enteredLetters).contains(String.valueOf(letter));
	}

	/* Find first empty position in array of entered letters (one with code \u0000) */
	public static int findEmptyPosition(char[] enteredLetters) {
		int i = 0;
		while (enteredLetters[i] != '\u0000') i++;
		return i;
	}


}
