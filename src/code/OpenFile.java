package code;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class OpenFile {
	private final String FILE_PATH_DIR = "words";
	private int previousRandom;
	private File[] listOfFiles;

	public OpenFile() {
		previousRandom = -1;
		listFiles();

	}

	private int generateNewRandom(int range, int previousRandom) {
		int newRandom = (int) (Math.random() * range);
		System.out.println("Current random " + newRandom + "previousRandom " + previousRandom);
		if (newRandom != previousRandom)
			return newRandom;
		else {
			while (newRandom == previousRandom) {
				newRandom = (int) (Math.random() * range);
			}
			return newRandom;
		}
	}

	private void listFiles() {
		File folder = new File(FILE_PATH_DIR);
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
			}
		}
	}

	public String chooseFile() {
		int randomFileIndex = generateNewRandom(listOfFiles.length, previousRandom);
		previousRandom = randomFileIndex;
		System.out.println("returned new random" + randomFileIndex);
		return listOfFiles[randomFileIndex].getName();

	}

	public String chooseWord(String fileName) {
		System.out.println("Random file is " + fileName);
		String word = "NONE";
		File file = new File(FILE_PATH_DIR + "/" + fileName);
		try {
			int randomWordPick = (int) (Math.random() * countLines(fileName));
			System.out.println("RandomWordPick " + randomWordPick);
			int currentLine = 0;
			Scanner sc = new Scanner(file);

			while (currentLine < randomWordPick) {
				word = sc.nextLine();
				currentLine++;
			}
			// as random index can be 0, and does not go in the while loop
			if (word.equals("NONE"))
				word = sc.nextLine();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ChoosenWord " + word);
		return word.toLowerCase();
	}

	/**
	 * Function found in the Internet Relative performance: "s. On a 150MB log
	 * file this takes 0.35 seconds, versus 2.40 seconds when using readLines()"
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(FILE_PATH_DIR + "/" + filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}
}
