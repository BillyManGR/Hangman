import java.io.FileNotFoundException;
import java.util.Scanner;

public class OnePlayerGame extends BaseGame {
	public enum Category {
		GENERAL,
		ANIMALS,
		PLANTS, 
		COMPUTER, 
		HOUSE, 
		FOOD, 
		JOBS, 
		WEATHER,
	}
	private OpenFile openFile;
	private String category;

	// constructors
	//Player selects category
	public OnePlayerGame() throws FileNotFoundException {
		super();												//Call BaseGame constructor
		openFile = new OpenFile();
		Category cat = category_selection();					//First ask the player which category to choose from
		if (cat==Category.GENERAL) {
			category = openFile.chooseRandomCategory();            //Random category
			category = (category.endsWith(".txt")) ? category.substring(0, category.length() - 4) : category;
		}
		else if (cat==Category.ANIMALS)
			category = "animals";
		else if (cat==Category.COMPUTER)
			category = "computer";
		else if (cat==Category.FOOD)
			category = "food";
		else if (cat==Category.HOUSE)
			category = "house";
		else if (cat==Category.JOBS)
			category = "jobs";
		else if (cat==Category.WEATHER)
			category = "weather";
		
		else {category = "plants";}
		playOnePlayerGame(category);
	}
	//Player selects previous category
	public OnePlayerGame(String category) throws FileNotFoundException {
		super();												//Call BaseGame constructor
		openFile = new OpenFile();
		playOnePlayerGame(category);
	}

	private String loadWord(String category) {
		String filename = category+".txt";
//		System.out.println("Choosing word from "+filename);
		return openFile.chooseWord(filename);
	}

	private void playOnePlayerGame(String category) throws FileNotFoundException{
		String word = loadWord(category);
		setWordToGuess(word);
		play();
		chooseWhatToDoNext();
	}

	@Override
	protected void chooseWhatToDoNext() throws FileNotFoundException {
		super.chooseWhatToDoNext();
		System.out.print("Press:"+ newline
				+ "1 to Play again with same category (" + category + ")" + newline
				+ "2 to Play again and change category" + newline
				+ "3 to Switch playing mode"+ newline
				+ "4 to Quit"+ newline
				+ "Select> "
				);
		nextGameSelection();
	}

	private void nextGameSelection() throws FileNotFoundException {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String selection = scanner.nextLine();
			switch (selection) {
				case "1":
					System.out.println("Selected to play again with same category");
					new OnePlayerGame(category);
					return;
				case "2":
					System.out.println("Selected to play again and change category");
					new OnePlayerGame();
					return;
				case "3":
					System.out.println("Selected to switch mode");
					BaseGame.select_mode();
					return;
				case "4":
					System.out.println("Thank you for your interest in the game. Goodbye :)");
					System.exit(0);
				default:
					System.out.print("Invalid selection"+ newline
							+ "Select> ");
			}
		}
	}

	private Category category_selection() throws FileNotFoundException {
		while (true) {
			openFile.print_file("categories.txt");
			System.out.print("Selection> ");
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
				case "4":
					System.out.println("Selected category is Computer");
					return Category.COMPUTER;
				case "5":
					System.out.println("Selected category is Food");
					return Category.FOOD;
				case "6":
					System.out.println("Selected category is House");
					return Category.HOUSE;
				case "7":
					System.out.println("Selected category is Jobs");
					return Category.JOBS;
				case "8":
					System.out.println("Selected category is Weather");
					return Category.WEATHER;
				default:
					System.out.println("Invalid selection. Please select a valid option.");
			}
		}
	}

}