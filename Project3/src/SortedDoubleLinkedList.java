/**
 * The purpose of this class is to serve as a subclass of
 * BasicDoubleLinkedList which automatically sorts the elements as
 * they are added.
 * 
 * I swear I have not copied any code.
 * @author Nicholas A. Baker
 */

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> 
implements Iterable<T>{
	private Comparator<T> comparator2;
	
	/**
	 * The only constructor, which takes a comparator provided by the user.
	 * @param comparator2
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		this.comparator2 = comparator2;
	}
	
	/**
	 * Adds a Node with the desired data into the correct position on the list.
	 * @param data
	 * @return this list with the desired data added into the correct spot
	 */
	public SortedDoubleLinkedList<T> add(T data){
		Node<T> n = new Node<T> (data);
		Node<T> currentNode = head;
		boolean putAtEnd = false;
		
		if(size == 0) {
			head = n;
			tail = n;
			head.setNext(tail);
			head.setPrevious(tail);
			tail.setNext(head);
			tail.setPrevious(head);
		}
		else {
			while(!(comparator2.compare(data, currentNode.getData()) <= 0) && !putAtEnd) {
				if(currentNode == tail && !putAtEnd) putAtEnd = true;
				else currentNode = currentNode.getNext();
			}
			if(putAtEnd) {
				tail.setNext(n);
				n.setPrevious(tail);
				tail = n;
				tail.setNext(head);
				head.setPrevious(tail);
			}
			else if(currentNode == head) {
				if(size == 1) {
					head = n;
					tail.setPrevious(head);
					tail.setNext(head);
					head.setNext(tail);
					head.setPrevious(tail);
				}
				else {
					head.setPrevious(n);
					n.setNext(head);
					head = n;
					head.setPrevious(tail);
					tail.setNext(head);
				}
			}
			else {
				n.setPrevious(currentNode.getPrevious());
				currentNode.getPrevious().setNext(n);
				n.setNext(currentNode);
				currentNode.setPrevious(n);
			}
		}
		size++;
		return this;
	}
	
	/**
	 * This operation is unsupported for this list.
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is unsupported for this list.
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Calls the super class method and returns an iterator for this list 
	 * which uses the inner class in BasicDoubleLinkedList.
	 * @return an iterator for this list
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Calls the super class method to remove the Node with the target data.
	 * @return this list with the desired data removed
	 */
	public SortedDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		return (SortedDoubleLinkedList<T>)super.remove(targetData, comparator2);
	}
}
