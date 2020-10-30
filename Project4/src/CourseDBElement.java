/**
 * CourseDBElement serves as the structure of each element in the
 * course database.
 * 
 * I swear I have not copied any code.
 * 
 * @author Nicholas A. Baker
 *
 */

//IMPORTS GO HERE

public class CourseDBElement implements Comparable{
	private String id;
	private int crn;
	private int credits;
	private String roomNumber;
	private String instructor;
	
	public CourseDBElement(String id, int crn, int credits, 
			String roomNumber, String instructor) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructor = instructor;
	}
	
	/**
	 * Returns the id of this CourseDBElement
	 * @return the id of this CourseDBElement
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the crn of this CourseDBElement
	 * @return the crn of this CourseDBElement
	 */
	public int getCRN() {
		return crn;
	}

	/**
	 * Returns the number of credits of this CourseDBElement
	 * @return the number of credits of this CourseDBElement
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Returns the room number of this CourseDBElement
	 * @return the room number of this CourseDBElement
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Returns the name of the instructor of this CourseDBElement
	 * @return the name of the instructor of this CourseDBElement
	 */
	public String getInstructor() {
		return instructor;
	}
	
	/**
	 * Returns the hash code of the crn of this CourseDBelement converted
	 * into a String (it is converted into a String, then hashed).
	 * @return the hash code of the crn of this CourseDBelement converted into a String
	 */
	public int hashCode() {
		return (Integer.toString(getCRN())).hashCode();
	}
	
	/**
	 * Compares two CourseDBElements based on their crn. 
	 * Returns a positive number if the first is larger 
	 * than the second, a negative number of the second is 
	 * larger than the first, or 0 if they are equal.
	 * 
	 * @return an int
	 */
	@Override
	public int compareTo(Object arg0) {
		CourseDBElement second = (CourseDBElement) arg0;
		return this.getCRN()-second.getCRN();
	}
	
	/**
	 * Returns a String representation of this CourseDBElement
	 * 
	 * @return this CourseDBElement as a String
	 */
	public String toString() {
		System.out.println("instructor: " + instructor);
		return "\nCourse:" + id + " CRN:" + crn + " Credits:" + credits + 
				" Instructor:" + instructor + " Room:" + roomNumber;
	}

}
