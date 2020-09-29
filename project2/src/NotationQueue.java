import java.util.ArrayList;

/**
 * The purpose of this class is to represent a queue of objects.
 * This queue will primarily be used to store postfix expressions
 * @author Nicholas A. Baker
 *
 * @param <T>
 */
public class NotationQueue<T> implements QueueInterface<T>{

	/*
	 * In this implementation, the only purpose of the head value
	 * is to allow for dequeue-ing since it is impossible to dequeue
	 * a generic otherwise. The specific behavior of the head value is explained
	 * throughout the program.
	 */
	private T[] contents;
	private int head = 0;
	private int tail = -1;

	/**
	 * Constructor which takes an int as the size of the queue
	 * @param size
	 */
	public NotationQueue(int size) {
		contents = (T[])new Object[size];
	}

	/**
	 * Default constructor where the size of the queue is set to 20
	 */
	public NotationQueue() {
		contents = (T[])new Object[20];
	}

	/**
	 * Constructor where the contents of an ArrayList are used to fill a queue
	 * @param list
	 */
	public NotationQueue(ArrayList<T> list) {
		contents = (T[])new Object[list.size()];

		//Adding each individual value to the stack
		for(int i = 0; i < contents.length; i++) {
			contents[i] = list.get(i);
			tail++;
		}
	}

	@Override
	public boolean isEmpty() {

		/*
		 * This condition is created in the dequeue method if
		 * the queue ends up empty as a result
		 */
		if(tail < head) return true;
		return false;
	}

	@Override
	public boolean isFull() {

		/*
		 * If tail >= head, then there are values at the
		 * head and tail indexes and at the indexes in between
		 */
		if(tail == contents.length-1 && head == 0) return true;
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) 
			throw new QueueUnderflowException("Cannot dequeue on an empty queue");

		T result = contents[head];
		head++;

		/*
		 * If we dequeue the last value, the head and tail values
		 * are set so that isEmpty() returns true
		 */
		if(tail < head) {
			head = 0;
			tail = -1;
		}

		return result;
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;

		//This equation turns this from O(n) to O(1)
		return (tail - head) + 1;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull())
			throw new QueueOverflowException("Cannot enqueue with a full queue");
		contents[++tail] = e;
		return true;
	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue elements
	 */
	public String toString() {
		String str = "";
		
		if(!isEmpty()) {
			for(int i = head; i < tail+1; i++)
				str = str + contents[i];
		}
		
		return str;
	}

	/**
	 * Returns the string representation of the elements in the Queue separated by a delimiter, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements separated by delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String str = "";
		
		if(!isEmpty()) {
			for(int i = head; i < tail+1; i++)
				str = str + delimiter + contents[i];
		}
		
		return str.substring(1, str.length());
	}

}
