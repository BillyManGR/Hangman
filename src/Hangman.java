import java.io.*;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		OpenFile temp = new OpenFile();
		temp.print_file("hangman.txt"); // Hangman sign
		System.out.println("");
		temp.print_file("instructions.txt"); // Instructions of the game
		BaseGame.select_mode();
	}
}