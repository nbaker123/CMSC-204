/**
 * CourseDBStructure is meant to serve as a hash table which
 * holds the course elements.
 * 
 * I swear I have not copied any code.
 * 
 * @author Nicholas A. Baker
 *
 */

import java.io.IOException;
import java.util.LinkedList;


public class CourseDBStructure implements CourseDBStructureInterface{
	protected LinkedList<CourseDBElement>[] hashTable;
	private String status;
	private int size;
	
	public CourseDBStructure(int estimatedSize) {
		status =  "Active database";
		this.size = estimatedSize;
		hashTable = new LinkedList[estimatedSize];
	}
	
	public CourseDBStructure(String status, int size) {
		status = "Testing";
		this.size = size;
		hashTable = new LinkedList[size];
	}

	/**
	 * Adds a CourseDBElement into the correct bucket in the hash table using
	 * the hash code based on its CRN.
	 * 
	 * @param element
	 */
	@Override
	public void add(CourseDBElement element) {
		int key = element.hashCode()%getTableSize();
		
		if(hashTable[key] == null) {
			LinkedList<CourseDBElement> newList = new LinkedList<CourseDBElement>();
			newList.add(element);
			hashTable[key] = newList;
		}
		else hashTable[key].add(element);
	}

	/**
	 * Finds and returns the CourseDBElement in the hash table that matches the
	 * CRN provided by the user. If the element does not exist in the array,
	 * an IOException is thrown.
	 * 
	 * @param crn
	 * @return The first CourseDBElement in the hash table 
	 * that matches the provided crn
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int key = Integer.toString(crn).hashCode()%getTableSize();
		 
		if(hashTable[key] == null)
			throw new IOException("Element is not in the table");
		else {
			for(int i = 0; i < hashTable[key].size(); i++) {
				if(hashTable[key].get(i).getCRN() == crn)
					return hashTable[key].get(i);
			}
			throw new IOException("Element is not in the table");
		}
	}

	/**
	 * Returns the number of indexes in the hash table array.
	 * @return the number of indexes in the hash table array
	 */
	@Override
	public int getTableSize() {
		return size;
	}
	
	
}
