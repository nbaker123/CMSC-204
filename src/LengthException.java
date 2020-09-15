/**
 * This Exception is made to be thrown when a password
 * is less than six characters long
 * @author Nicholas A. Baker
 *
 */

public class LengthException extends Exception {
	public LengthException(String message) {
		super(message);
	}
}
