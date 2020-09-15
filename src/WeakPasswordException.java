/**
 * This Exception is made to be thrown when a password
 * is between six and eight characters long 
 * (which is considered a weak password if it is otherwise valid)
 * @author Nicholas A. Baker
 *
 */
public class WeakPasswordException extends Exception {

	public WeakPasswordException(String message) {
		super(message);
	}
	
}
