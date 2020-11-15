/**
 * MorseCodeConverter serves as a class which converts
 * Morse code to English using a MorseCodeTree. This class
 * can do conversions of manual inputs or inputs from a file.
 * 
 * I swear I have not copied any code
 * @author Nicholas A. Baker
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	static MorseCodeTree tree = new MorseCodeTree();
	
	/**
	 * Prints the contents of the MorseCodeTree used in this class.
	 * This is used for testing purposes to make sure the tree was
	 * built correctly.
	 * @return a String containing the contents of the MorseCodeTree
	 */
	public static String printTree() {
		ArrayList<String> treeList = tree.toArrayList();
		String result = "";
		
		for(int i = 0; i < treeList.size(); i++) {
			result += treeList.get(i) + " ";
		}
		System.out.println("result: " + result);
		result = result.trim();
		return result;
	}
	
	/**
	 * Converts a Morse code String to English using the MorseCodeTree,
	 * and returns the result.
	 * 
	 * @param code: the input in Morse code to be converted
	 * @return the contents in code converted to English
	 */
	public static String convertToEnglish(String code) {
		String[] stringArray = code.split(" ");
		String result = "";
		
		for(int i = 0; i < stringArray.length; i++) {
			if(stringArray[i].equals("/")) result += " ";
			else result += tree.fetch(stringArray[i]);
		}
		return result;
	}
	
	/**
	 * Converts Morse code from a file to English using the MorseCodeTree,
	 * and returns the result.
	 * 
	 * @param code: the file to be converted (contains Morse code on the first line)
	 * @return the contents in the file converted to English
	 */
	public static String convertToEnglish(File file) {
		String result = "";
		
		try {
			Scanner inFile = new Scanner(file);
			
			/*
			 * Assuming that the file only has one line, judging
			 * from the sample input I was given.
			 */
			String[] stringArray = inFile.nextLine().split(" ");
			
			for(int i = 0; i < stringArray.length; i++) {
				if(stringArray[i].equals("/")) result += " ";
				else result += tree.fetch(stringArray[i]);
			}
		}
		catch(FileNotFoundException e) {
			System.err.println(file.getName() + " does not exist");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
