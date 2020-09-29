import java.util.ArrayList;

/**
 * The purpose of this class is to represent a stack of objects <T>.
 * This stack's main purpose is to store 
 * @author Nicholas A. Baker
 *
 * @param <T>
 */
public class NotationStack<T> implements StackInterface<T>{

	private T[] contents;
	private int topIndex = -1;

	/**
	 * Constructor that takes an int as the size limit of the Stack
	 * @param size
	 */
	public NotationStack(int size) {
		contents = (T[])new Object[size];
	}

	/**
	 * Default constructor. The size limit is set to 20.
	 */
	public NotationStack() {
		contents = (T[])new Object[20];
	}

	/**
	 * Constructor that takes an ArrayList as a parameter and adds
	 * its data to the stack
	 * @param list
	 */
	public NotationStack(ArrayList<T> list) {
		contents = (T[])new Object[list.size()];

		//Adding each individual value to the stack
		for(int i = 0; i < contents.length; i++) {
			contents[i] = list.get(i);
			topIndex++;
		}
	}

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(topIndex < 0) return true;
		return false;
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(topIndex == (contents.length)-1) return true;
		return false;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) 
			throw new StackUnderflowException("Cannot pop on an empty stack");
		T o = contents[topIndex];
		topIndex--;
		return o;
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T top() throws StackUnderflowException {
			if(isEmpty()) 
				throw new StackUnderflowException("Cannot find top because stack is empty");
			return contents[topIndex];
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return topIndex+1;
	}

	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) 
			throw new StackOverflowException("Cannot puch because stack is full");
		contents[++topIndex] = e;
		return true;
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String str = "";

		if(!isEmpty()) {
			for(int i = 0; i < topIndex+1; i++)
				str = str + contents[i];
		}
		
		return str;
	}

	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		if(isEmpty()) return "";
		String str = "";

		for(int i = 0; i < topIndex+1; i++)
			str = str + delimiter + contents[i];

		//We use subString to get rid of the delimiter at the beginning
		return str.substring(1, str.length());
	}

}
