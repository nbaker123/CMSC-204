/**
 * The main purpose of this class is to serve as an implementation for a basic
 * doubly linked list, and as the subclass for SortedDoubleLinkedList.
 * 
 * I swear that I have not copied any code.
 * 
 * @author Nicholas A. Baker
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>{
	protected Node<T> head = null;
	protected Node<T> tail = null;
	protected int size = 0;

	/**
	 * This is the inner class for the nodes, which serve
	 * as the inner structure for the list. A Node consists of
	 * its data, a reference to the previous Node, and a reference to
	 * the next Node.
	 * 
	 * I swear I have not copied any code
	 * 
	 * @author Nicholas A. Baker
	 *
	 * @param <E>
	 */
	protected class Node<E>{
		E data;
		Node<E> next;
		Node<E> previous;

		/**
		 * The only constructor for Node,
		 * which takes the data and inserts
		 * it into the new Node
		 * @param data
		 */
		public Node(E data) {
			this.data = data;
		}

		/**
		 * returns the data within the Node
		 * @return the data within the Node
		 */
		public E getData() {
			return data;
		}

		/**
		 * Returns the reference to the next Node in the list after this Node
		 * @return the reference to the next Node in the list after this Node
		 */
		public Node<E> getNext() {
			return next;
		}

		/**
		 * Sets the next reference of this Node to the desired reference
		 * @param newNext
		 */
		public void setNext(Node<E> newNext) {
			next = newNext;
		}

		/**
		 * Returns the reference of the previous Node before this Node
		 * @return the reference of the previous Node before this Node
		 */
		public Node<E> getPrevious() {
			return previous;
		}

		/**
		 * Sets the reference to the previous Node before this Node
		 * to the desired reference
		 * @param newPrevious
		 */
		public void setPrevious(Node<E> newPrevious) {
			previous = newPrevious;
		}
	}

	/**
	 * This is the inner class for the iterator LinkedListIterator,
	 * which is used to iterate through BasicDoubleLinkedList and its
	 * subclasses.
	 * 
	 * I swear that I have not copied any code.
	 * 
	 * @author Nicholas A. Baker
	 *
	 * @param <E>
	 */
	private class LinkedListIterator<E> implements ListIterator<E>, Iterator<E>{
		Node<T> currentNode = head;
		boolean seenNext = false;

		/**
		 * This operation is unsupported for this iterator.
		 */
		@Override
		public void add(E arg0) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		/**
		 * Returns true if the iterator is at the 
		 * end of the list and cannot go on, false otherwise
		 * @return true if the iterator cannot go further, false otherwise
		 */
		@Override
		public boolean hasNext() {
			return !seenNext;
		}

		/**
		 * Returns true if the iterator is at the 
		 * beginning of the list and cannot go farther back, false otherwise
		 * @return true if the iterator cannot go further back, false otherwise
		 */
		@Override
		public boolean hasPrevious() {
			if(seenNext) return currentNode != null;
			return currentNode.getPrevious() != tail;
		}

		/**
		 * Returns the next element in the list and moves the
		 * cursor forward.
		 * @return the next element in the list
		 */
		@Override
		public E next() throws NoSuchElementException{
			if(!seenNext) {
				E result = (E) currentNode.getData();
				if(currentNode.getNext() == head) seenNext = true;
				else currentNode = currentNode.getNext();
				return result;
			}
			throw new NoSuchElementException("End of the list has been reached");
		}

		/**
		 * This operation is unsupported for this iterator.
		 */
		@Override
		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("Unsupported operation");
		}

		/**
		 * Returns the previous element in the list relative to the cursor
		 * and moves the cursor back.
		 * @return the previous element in the list relative to the cursor
		 */
		@Override
		public E previous() throws NoSuchElementException{
			if(seenNext) {
				E result = (E) currentNode.getData();
				seenNext = false;
				return result;
			}
			if(currentNode == head)
				throw new NoSuchElementException("The beginning of the list has "
						+ "no previous value");
			currentNode = currentNode.getPrevious();
			return (E) currentNode.getData();
		}

		/**
		 * This operation is unsupported for this iterator.
		 */
		@Override
		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("Unsupported operation");
		}

		/**
		 * This operation is unsupported for this iterator.
		 */
		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("Unsupported operation");
		}

		/**
		 * This operation is unsupported for this iterator.
		 */
		@Override
		public void set(E arg0) throws UnsupportedOperationException{
			throw new UnsupportedOperationException("Unsupported operation");
		}
	}

	/**
	 * Returns an iterator for this list that uses the inner class.
	 * @return an iterator for this list that uses the inner class.
	 */
	@Override
	public ListIterator<T> iterator() {
		return new LinkedListIterator<T>();
	}

	/**
	 * Adds a Node with the desired data to the end of this list.
	 * @param data
	 * @return this list with the Node with the desired data added to the end
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node<T> n = new Node<T>(data);

		/*
		 * When the list is empty, the size is 0
		 */
		if(size == 0) {
			head = n;
			tail = n;
			head.setNext(tail);
			head.setPrevious(tail);
			tail.setNext(head);
			tail.setPrevious(head);
		}
		else {
			tail.setNext(n);
			n.setPrevious(tail);
			tail = n;
			tail.setNext(head);
			head.setPrevious(tail);
		}
		size++;
		return this;
	}

	/**
	 * Adds a Node with the desired data to the front of this list.
	 * @param data
	 * @return this list with the Node with the desired data added to the front
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node<T> n = new Node<T>(data);

		/*
		 * When the list is empty, the size is 0
		 */
		if(size == 0) {
			head = n;
			tail = n;
			head.setNext(tail);
			head.setPrevious(tail);
			tail.setNext(head);
			tail.setPrevious(head);
		}
		else {
			head.setPrevious(n);
			n.setNext(head);
			head = n;
			head.setPrevious(tail);
			tail.setNext(head);
		}
		size++;
		return this;
	}

	/**
	 * Returns the first element of this list, but does not remove it.
	 * @return the first element of this list
	 */
	public T getFirst() {
		if(head == null) return null;
		return head.getData();
	}

	/**
	 * Returns the last element of this list, but does not remove it.
	 * @return the last element of this list
	 */
	public T getLast() {
		if(tail == null) return null;
		return tail.getData();
	}
	
	/**
	 * Returns the first element of this list and removes it.
	 * @return the first element of this list.
	 */
	public T retrieveFirstElement() {
		T result = head.getData();
		head = head.getNext();
		head.setPrevious(tail);
		tail.setNext(head);
		return result;
	}
	
	/**
	 * Returns the last element of this list and removes it.
	 * @return the last element of this list
	 */
	public T retrieveLastElement() {
		T result = tail.getData();
		tail = tail.getPrevious();
		tail.setNext(head);
		head.setPrevious(tail);
		return result;
	}
	
	/**
	 * Returns the number of elements in this list.
	 * @return the number of elements in this list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Removes the first instance of a Node with the desired data from this list
	 * using a comparator provided by the user.
	 * @param targetData
	 * @param comparator
	 * @return this list with the first instance of the target data removed
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		Node<T> n = head;
		boolean iterated = false;
		
		while(comparator.compare(targetData, n.getData()) != 0 && n.getNext() != head) {
			n = n.getNext();
			if (n == tail && tail.getData() != targetData) iterated = true;
		}

		/*
		 * If the first instance of targetData has been found, then
		 * the next and previous values of the nodes before and after
		 * the target node are tweaked in order to remove the target node from
		 * the list. If the data was not found (n == null) then the list is returned as is
		 */
		if(!iterated) {
			if(size == 1) {
				head = null;
				tail = null;
			}
			else if(n == head) {
				head.getNext().setPrevious(null);
				head = head.getNext();
			}
			else if(n == tail) {
				tail.getPrevious().setNext(head);
				tail = tail.getPrevious();
			}
			else {
				n.getPrevious().setNext(n.getNext());
				n.getNext().setPrevious(n.getPrevious());
			}
			size--;
		}
		return this;
	}
	
	/**
	 * Returns this list as an ArrayList
	 * @return this list as an ArrayList
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> result = new ArrayList<T>();
		ListIterator<T> iterator = iterator();
		
		while(iterator.hasNext())
			result.add(iterator.next());
		return result;
	}
}
