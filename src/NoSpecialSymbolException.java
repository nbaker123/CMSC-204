/**
 * This Exception is made to be thrown when a password
 * does not contain a special symbol 
 * (a character that is not a letter or a number)
 * @author Nicholas A. Baker
 *
 */

public class NoSpecialSymbolException extends Exception { 
	public NoSpecialSymbolException(String message) {
		super(message);
	}
}
