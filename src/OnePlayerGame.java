import java.io.FileNotFoundException;
import java.util.Scanner;

public class OnePlayerGame extends BaseGame {
	public enum Category {
		GENERAL,
		ANIMALS,
		PLANTS,
	}
	private OpenFile openFile;

	// constructors
	//Player selects category
	public OnePlayerGame() throws FileNotFoundException {
		super();												//Call BaseGame constructor
		Category cat = category_selection();					//First ask the player which category to choose from
		String category;
		openFile = new OpenFile();
		if (cat==Category.GENERAL) {
			category = openFile.chooseRandomCategory();            //Random category
			category = (category.endsWith(".txt")) ? category.substring(0, category.length() - 4) : category;
		}
		else if (cat==Category.ANIMALS)
			category = "animals";
		else {category = "plants";}
		playOnePlayerGame(category);
	}
	//Player selects previous category
	public OnePlayerGame(String category) throws FileNotFoundException {
		super();												//Call BaseGame constructor
		playOnePlayerGame(category);
	}

	protected String loadWord(String category) {
		String filename = category+".txt";
		System.out.println("Choosing word from "+filename);
		return openFile.chooseWord(filename);
	}

	private void playOnePlayerGame(String category) throws FileNotFoundException{
		String word = loadWord(category);
		setWordToGuess(word);
		play();
		chooseWhatToDoNext(category);
	}

	@Override
	protected void chooseWhatToDoNext(String previousCat) throws FileNotFoundException{
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
				new OnePlayerGame(previousCat);
				return;
			case "2":
				System.out.println("Selected play again and change category");
				new OnePlayerGame();
				return;
			case "3":
				System.out.println("Selected switch mode");
				BaseGame.select_mode();
				return;
			case "4":
				System.out.println("Thank you for your interest in the game :)");
				System.exit(0);
			default:
				System.out.print("Invalid selection"+ newline
						+ "Select> ");
			}
		}

	}

	private Category category_selection() throws FileNotFoundException {
		while (true) {
			openFile.print_file("data/categories.txt");
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
				case "1":
					System.out.println("Selected category is General");
					return Category.GENERAL;
				case "2":
					System.out.println("Selected category is Animals");
					return Category.ANIMALS;
				case "3":
					System.out.println("Selected category is Plants");
					return Category.PLANTS;
				default:
					System.out.println("Invalid selection.");
			}
		}
	}

}