/**
 * This Exception is made to be thrown when a password
 * contains more than three of the same character in a row
 * @author Nicholas A. Baker
 *
 */

public class InvalidSequenceException extends Exception {
	public InvalidSequenceException(String message) {
		super(message);
	}
}
