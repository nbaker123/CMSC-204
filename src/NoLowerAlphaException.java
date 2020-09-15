/**
 * This Exception is made to be thrown when a password
 * does not contain a lower case letter
 * @author Nicholas A. Baker
 */

public class NoLowerAlphaException extends Exception {
	public NoLowerAlphaException(String message) {
		super(message);
	}
}
