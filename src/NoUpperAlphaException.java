/**
 * This Exception is made to be thrown when a password
 * does not contain a capital letter
 * @author Nicholas A. Baker
 *
 */
public class NoUpperAlphaException extends Exception {
	public NoUpperAlphaException(String message) {
		super(message);
	}
}
