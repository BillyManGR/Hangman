import java.util.Scanner;



public class BaseGame {
	public enum LetterStates {
		LETTER_NOT_IN_WORD,
		LETTER_NEW_CORRECT,
		LETTER_ALREADY_ENTERED,
	}
	protected  boolean wordIsGuessed;
	
	public BaseGame(){
		wordIsGuessed= false;
	}
	
	
	public void  loadWords(){}
	
	public LetterStates enterLetter(String wordToGuess, char[] alreadyEnteredWords) {
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
		if(wordGuessed) wordIsGuessed= true;
		System.out.print('\n');

	}

	/* Find first empty position in array of entered letters (one with code \u0000) */
	private static int findEmptyPosition(char[] enteredLetters) {
		int i = 0;
		while (enteredLetters[i] != '\u0000') i++;
		return i;
	}
	
	
    public static void play(){
        int mode = mode_selection();                                                    //Select mode
        if (mode == 1) {
        	new OnePlayerGame();
        } else if (mode == 2) {
            System.out.println("Calling 2 Player mode");//two_players();
        }
    }

    public static int mode_selection() {
        while (true) {
        	System.out.print("Please enter '1' for 1-Player or '2' for 2-players mode.");
            System.out.print("Selection> ");
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.nextLine();
            //System.out.println("Selection = "+selection);
            switch (selection){
                case "1":
                    System.out.println("Selected mode is 1 Player");
                    return 1;
                case "2":
                    System.out.println("Selected mode is 2 players");
                    return 2;
                default:
                    System.out.println("Invalid selection. Please enter '1' for 1-Player or '2' for 2-Players mode.");
            }
        }
    }


	


}
