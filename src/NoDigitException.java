/**
 * This Exception is made to be thrown when a password
 * does not contain a digit
 * @author Nicholas A. Baker
 *
 */

public class NoDigitException extends Exception {
	public NoDigitException(String message) {
		super(message);
	}
}
