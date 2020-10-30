/**
 * This class serves as the comparator to be used in the showAll method of
 * CourseDBManager
 * 
 * I swear I have not copied any code.
 * @author Nicholas A. Baker
 */

import java.util.Comparator;

public class ElementComparator implements Comparator{

	/**
	 * Takes two Strings in CourseDBElement.toString() format
	 * and compares them based on CRN. Returns a positive number
	 * if the first is greater than the second, a negative number
	 * if the second is greater than the first, or zero if they are equal.
	 * 
	 * @return a positive number, negative number, or zero, depending on the situation
	 */
	@Override
	public int compare(Object arg0, Object arg1) {
		
		//Retrieving the CRN of each element, assuming that they are both Strings
		String zero = (String) arg0;
		zero = zero.substring(zero.indexOf(" ") + 1);
		int intZero = Integer.parseInt(zero.substring(4, zero.indexOf(" ")));
		String one = (String) arg1;
		one = one.substring(one.indexOf(" ") + 1);
		int intOne = Integer.parseInt(one.substring(4, one.indexOf(" ")));
		
		return intZero-intOne;
	}
	
}
