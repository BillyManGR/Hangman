import java.io.*;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		OpenFile temp = new OpenFile();
		temp.print_file("data/hangman.txt"); // Hangman sign
		System.out.println("");
		temp.print_file("data/instructions.txt"); // Instructions of the game
		BaseGame.select_mode();
	}
}