package code;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author vikto
 *
 */
public class OpenFile {
	private final String FILE_PATH_DIR = "words";
	private int previousRandom;
	private File[] listOfFiles;

	public OpenFile() {
		previousRandom = -1;
		listFiles();

	}

	//	private int generateNewRandom(int range, int previousRandom) {
	//		int newRandom = (int) (Math.random() * range);
	//		if (newRandom != previousRandom)
	//			return newRandom;
	//		else {
	//			while (newRandom == previousRandom) {
	//				newRandom = (int) (Math.random() * range);
	//			}
	//			return newRandom;
	//		}
	//	}

	private void listFiles() {
		File folder = new File(FILE_PATH_DIR);
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
			}
		}
	}

	public String chooseFile() {
		//int randomFileIndex = generateNewRandom(listOfFiles.length, previousRandom);
		//previousRandom = randomFileIndex;
		System.out.println("Please select a categoty you want to play!");
		
		listOfFiles = moveGeneralCategoryAsFirstOption(listOfFiles);;

		//print all options
		int filesCount=listOfFiles.length;
		for(int i=0; i<filesCount;i++){
			System.out.println(String.valueOf(i+1)+" "+listOfFiles[i].getName());
		}

		System.out.println("Input a number between 1 and " + listOfFiles.length);

		while(true){
			Scanner scanner = new Scanner(System.in);
			try{
				int selection = Integer.valueOf(scanner.nextLine());
				
				if(selection>0 && selection <= filesCount){
					return listOfFiles[selection-1].getName();
				}else{
					System.out.println("invalid selection!");
					System.out.println("Input a number between 1 and " + listOfFiles.length);
				}

			}catch(NumberFormatException e){
				System.out.println("Please enter a number!!");
			}
		}
		//System.out.println("returned new random" + randomFileIndex);
		//		return listOfFiles[0].getName();

	}



	/**Move categoty 'general' to first position
	 * This will move all values from the index of the 'general'
	 * category backwards in the array by one position,
	 * and place the 'general' at the front
	 * should do noting if already at first position
	 * @param listOfFiles
	 * @return
	 */
	private File[] moveGeneralCategoryAsFirstOption(File[] listOfFiles) {

		int generalIndex=findPositionOfGeneralCategory();
		File general =listOfFiles[generalIndex];

		for(int i = generalIndex; i>0;i--)
			listOfFiles[i]=listOfFiles[i-1];

		listOfFiles[0]=general;
		return listOfFiles;
	}

	private int findPositionOfGeneralCategory() {

		for(int generalIndex=0;generalIndex<listOfFiles.length;generalIndex++){
			if(listOfFiles[generalIndex].getName().equals("general")) 
				return generalIndex;
		}
		return 0;

	}

	public String chooseWord(String fileName) {
		System.out.println("Random file is " + fileName);
		String word = null;
		File file = new File(FILE_PATH_DIR + "/" + fileName);
		try {
			int randomWordPick = (int) (Math.random() * countLines(fileName)) +1;
			int currentLine = 0;
			Scanner sc = new Scanner(file);

			while (currentLine < randomWordPick) {
				word = sc.nextLine();
				currentLine++;
			}
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
	 * UPDATE: please press enter on the last word as if not will not be caunter.
	 * Can't think of easy fix for this.
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
