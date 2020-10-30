/**
 * CourseDBManager's purpose is to serve as the main code that allows the GUI
 * to function the way it does, being able to read data from a file into a
 * hashtable as well as convert all of its data into an ArrayList of Strings.
 * 
 * This class can also add and retrieve elements just like in the structure class.
 * 
 * I swear I have not copied any code.
 * @author Nicholas A. Baker
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{

	protected CourseDBStructure database;

	public CourseDBManager() {
		database = new CourseDBStructure(10);
	}

	public CourseDBManager(int size) {
		database = new CourseDBStructure(size);
	}

	/**
	 * Adds a CourseDBElement containing the desired data into the hashtable.
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, 
				roomNum, instructor);
		database.add(element);
	}

	/**
	 * Retrieves the first instance of a CourseDBElement with the
	 * desired crn from the hashtable.
	 * 
	 * @return the first instance of a CourseDBElement with the
	 * desired crn from the hashtable
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return database.get(crn);
		}
		catch(IOException e) {
			System.err.println("ERROR: "+e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads the data from the desired file into the hashtable,
	 * assuming that it exists and that the data is written properly into
	 * the file.
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		if(!input.exists()) throw new FileNotFoundException();
		try {
			Scanner inFile = new Scanner(input);
			String tempString = "";
			String id = "";
			int crn = 0;
			int credits = 0;
			String location = "";
			String instructor = "";
			CourseDBElement element;
			
			/*
			 * Main loop that reads the data from the files
			 */
			while(inFile.hasNext()) {
				tempString = inFile.nextLine().trim();
				
				//Getting id and cutting that part off of the String
				id = tempString.substring(0, tempString.indexOf(" "));
				tempString = tempString.substring(tempString.indexOf(" ") + 1);
				
				//Getting crn and cutting that part off of the String
				crn = Integer.parseInt(tempString.substring(0, tempString.indexOf(" ")));
				tempString = tempString.substring(tempString.indexOf(" ") + 1);
				
				//Getting credits and cutting that part off of the String
				credits = Integer.parseInt
						(tempString.substring(0, tempString.indexOf(" ")));
				tempString = tempString.substring(tempString.indexOf(" ") + 1);
				
				//Getting location and cutting that part off of the String
				location = tempString.substring(0, tempString.indexOf(" "));
				tempString = tempString.substring(tempString.indexOf(" ") + 1);
				
				/*
				 * Checks the case where the location is "distance learning"
				 * and adjusts accordingly
				 */
				if(location.toLowerCase().equals("distance")) {
					location = location + " " 
				+tempString.substring(0, tempString.indexOf(" "));
					tempString = tempString.substring(tempString.indexOf(" ") + 1);
				}
				
				//The rest of the String is the instructor
				instructor = tempString;
				
				//Adds the data to the list
				this.add(id, crn, credits, location, instructor);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns an ArrayList of every element in the database, 
	 * converted to Strings, and sorted by crn.
	 * 
	 * @return an ArrayList of every element in the database converted to Strings and sorted by crn.
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> strs = new ArrayList<String>();
		
		for(int i = 0; i < database.getTableSize(); i++) {
			if(database.hashTable[i] == null) continue;
			for(int k = 0; k < database.hashTable[i].size(); k++) {
				strs.add(database.hashTable[i].get(k).toString());
			}
		}
		Collections.sort(strs, new ElementComparator());
		return strs;
	}

}
