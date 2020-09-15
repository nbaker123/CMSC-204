/**
 * This Exception is made to be thrown when the two passwords 
 * typed on the display do not match
 * @author Nicholas A. Baker
 *
 */
public class UnmatchedException extends Exception {
	
	public UnmatchedException(String message) {
		super(message);
	}
}
