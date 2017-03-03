package tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import code.BaseGame;
import code.BaseGame.PlayMode;


public class Tests {

	BaseGame game = new BaseGame();
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Test
	public void testCharacterInputValidation() {
		String inputLettersValud= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String inputLettersInvalid= "!@#$%^&&*()_+<>?}|~`=¡√—";
		
		//hide all print statements
		System.setOut(new PrintStream(out));
		
		//valid chrs
		for(char ch :inputLettersValud.toCharArray()){
			assertEquals(false,game.invalidCharacterCheck(ch));
		}
		//invalid chars
		for(char ch :inputLettersInvalid.toCharArray()){
			assertEquals(true,game.invalidCharacterCheck(ch));
		}
	

	}
	
	@Test
	public void testCharNovelty() {
		String enteredLetters= "ABCDEFGHIJ6789";
		String testNonEnteredLetters="KLMNOPQRSTUVWXYZ012345";
		String inputLettersInvalid= "!@#$%^&&*()_+<>?}|~`=¡√—";
		
		//hide all print statements
		System.setOut(new PrintStream(out));
		
		//populate the List;
		List<Character> test = new ArrayList<Character>();
		for(char ch :enteredLetters.toCharArray()) test.add(ch);
		
		//test for new letters
		for(char ch :testNonEnteredLetters.toCharArray()){
			assertEquals(false,game.inEnteredLetters(ch,test));
		}
		//test for non-new letters
		for(char ch :enteredLetters.toCharArray()){
			assertEquals(true,game.inEnteredLetters(ch,test));
		}
	
	}
	
	@Test
	public void testModeSelection() {
			
		//hide all print statements
		System.setOut(new PrintStream(out));
		
		//set value of '1' (one player) as input string to the system's input stream
		System.setIn(new ByteArrayInputStream("1".getBytes()));
		assertEquals(PlayMode.PLAY_ONE_PLAYER, game.mode_selection());
		
		//set value of '2' (multi player) as input string to the system's input stream
//		System.setIn(new ByteArrayInputStream("2".getBytes()));
//		assertEquals(PlayMode.PLAY_TWO_PLAYERS, game.mode_selection());
	}
	
	@Test
	public void testEmptyInput(){
		//hide all print statements
		System.setOut(new PrintStream(out));
		
		assertEquals(true,game.emptyCheck(""));
		assertEquals(false,game.emptyCheck("1"));
		assertEquals(false,game.emptyCheck("a"));
		assertEquals(false,game.emptyCheck(" "));
		assertEquals(false,game.emptyCheck("  "));
		assertEquals(false,game.emptyCheck("dadasfsdfsdg"));
		
	}
	
//	@Test
//	public void testNonASCIWord() {
//		String inputWord= "¡√—";
//		String inputLetters= "¡√—asdf";
//		game.setWordToGuess(inputWord);
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ByteArrayInputStream in;
//		for(char l :inputLetters.toCharArray()){
//			in= new ByteArrayInputStream(String.valueOf(l).getBytes());
//			System.setIn(in);
//			System.setOut(new PrintStream(out)); //direct the output 
//			LetterStates result = game.enterLetter();	
//		}
//		
//		assertEquals(true, game.wordIsGuessed);
//		System.setIn(System.in);
//		//System.setOut(System.out);
//
//	}

}
