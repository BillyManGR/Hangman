package tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import code.BaseGame;
import code.BaseGame.LetterStates;

public class LetterInputTest {

	BaseGame game = new BaseGame();

	@Test
	public void testInputAllNew() {
		String inputWord= "testword";
		String inputLetters= "tesword";
		game.setWordToGuess(inputWord);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in;
		for(char l :inputLetters.toCharArray()){
			in= new ByteArrayInputStream(String.valueOf(l).getBytes());
			System.setIn(in);
			System.setOut(new PrintStream(out)); //direct the output 
			
			LetterStates result = game.enterLetter();
			
			assertEquals(result, LetterStates.LETTER_NEW_CORRECT);
		}
	
		System.setIn(System.in);
		//System.setOut(System.out);

	}

}
