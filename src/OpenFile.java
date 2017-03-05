import java.io.*;
import java.util.Scanner;

public class OpenFile {
	private final String FILE_PATH_DIR = "data";
	private int previousRandom;
	private String[] listOfCategories;

	public OpenFile() {
		previousRandom = -1;
		listCategories();
	}

	//Returns a random int E [min, max] which is not the same as the previousRandom
	private int generateNewRandom(int min, int max, int previousRandom) {
		int range = (max - min) + 1;
		int newRandom = (int)(Math.random() * range) + min;
		if (newRandom != previousRandom)
			return newRandom;
		else {
			while (newRandom == previousRandom) {
				newRandom = (int) (Math.random() * range);
			}
			return newRandom;
		}
	}

	private void listCategories() {
		//File folder = new File(FILE_PATH_DIR+"/categories");
		//	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + filename)))

		//	listOfCategories = folder.listFiles();
		//		//System.out.println("Number of Categories = "+listOfCategories.length);
		//		System.out.println("How big"+listOfCategories.length);
		//		for (int i = 0; i < listOfCategories.length; i++) {
		//			if (listOfCategories[i].isFile()) {
		//			}
		//}
		//	    BufferedReader categories=	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("categories.txt")));
		//	    String s;
		//		try {
		//			while ((s=categories.readLine())!=null)
		//				System.out.println(s);
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		listOfCategories= new String[8];
		listOfCategories[0] = "General.txt";
		listOfCategories[1] = "Animals.txt";
		listOfCategories[2] = "Plants.txt";
		listOfCategories[3] = "Computer.txt";
		listOfCategories[4] = "Food.txt";
		listOfCategories[5] = "House.txt";
		listOfCategories[5] = "Jobs.txt";
		listOfCategories[6] = "Weather.txt";

	}

	public String chooseRandomCategory() {					//Returns the filename of a random category
		int randomFileIndex = generateNewRandom(0, listOfCategories.length-1, previousRandom);
		previousRandom = randomFileIndex;
		//System.out.println("returned new random" + randomFileIndex);
		return listOfCategories[randomFileIndex];

	}

	public String chooseWord(String fileName) {
		//System.out.println("Random file is " + fileName);
		String word = null;
		//File file = new File(FILE_PATH_DIR + "/categories/" + fileName);
		BufferedReader categories=	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/categories/"+fileName)));
		try {
			int randomLinePick = (int) (Math.random() * countLines(fileName)) +1;
			int currentLine = 0;
			Scanner sc = new Scanner(categories);
			while (currentLine < randomLinePick) {
				word = sc.nextLine();
				currentLine++;
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//		System.out.println("Chosen word is " + word);
		return word.toUpperCase();
	}

	/**
	 * Function found in the Internet Relative performance: "s. On a 150MB log
	 * file this takes 0.35 seconds, versus 2.40 seconds when using readLines()"
	 * 
	 * UPDATE: please press enter on the last word as if not will not be counted.
	 * Can't think of easy fix for this.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private int countLines(String filename) throws IOException {
		//InputStream is = new BufferedInputStream(new FileInputStream(FILE_PATH_DIR + "/categories/" + filename));
		
		BufferedReader file=	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/categories/"+filename)));
		Scanner sc = null ;
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			sc = new Scanner(file);
			while ( sc.hasNext()) {
				sc.nextLine();
				count++;
			}
		
		return (count == 0 && !empty) ? 1 : count;
	} finally {
		sc.close();
	}
}

public void print_file(String name) throws FileNotFoundException { // Prints a file
	BufferedReader bffile=	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(name)));
	String s = null;

	try {
		while ((s=bffile.readLine())!=null)
		{

			System.out.println(s);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void print_phase(int phase) throws FileNotFoundException { // Prints a file
	BufferedReader file=	new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("phases.txt")));
	int current_phase = 0;
	Scanner sc = new Scanner(file);
	while (sc.hasNextLine()) {
		String line = sc.nextLine();
		if (line.equals("%snextscene")) {
			current_phase++;
			continue;								//Read next line
		}
		if (current_phase==phase) {
			if(line.charAt(0)=='-') {System.out.print(line);}
			else {System.out.println(line);}
		}
	}
	sc.close();
}
}
