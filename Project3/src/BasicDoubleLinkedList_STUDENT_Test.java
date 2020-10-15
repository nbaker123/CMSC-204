import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDoubleLinkedList_STUDENT_Test {

	IntegerComparator intComp;
	BasicDoubleLinkedList<Integer> intList;
	Integer a = 35;
	Integer b = 6;
	Integer c = 100;
	Integer d = 10;
	Integer e = 74;
	
	private class IntegerComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0-arg1;
		}
	}
	
	@BeforeEach
	void setUp() throws Exception {
		intList = new BasicDoubleLinkedList<Integer>();
		intComp = new IntegerComparator();
	}

	@AfterEach
	void tearDown() throws Exception {
		intList = null;
	}

	@Test
	void testIterator() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		ListIterator<Integer> iterator = intList.iterator();
		
		//Testing next()
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next(), 0.01);
		assertEquals(b, iterator.next(), 0.01);
		assertEquals(c, iterator.next(), 0.01);
		assertEquals(d, iterator.next(), 0.01);
		assertEquals(false, iterator.hasNext());
		
		//Testing previous()
		assertEquals(true, iterator.hasPrevious());
		assertEquals(d, iterator.previous(), 0.01);
		assertEquals(c, iterator.previous(), 0.01);
		assertEquals(b, iterator.previous(), 0.01);
		assertEquals(a, iterator.previous(), 0.01);
		assertEquals(false, iterator.hasPrevious());
	}
	
	@Test
	void testIteratorExceptions() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		ListIterator<Integer> iterator = intList.iterator();
		
		//Testing NoSuchElementException
		try {
			while(iterator.hasNext())
				iterator.next();
			
			//throws an exception
			iterator.next();
			assertTrue(false, "Did not throw an exception");
		}catch(NoSuchElementException e) {
			assertTrue(true, "Threw the correct exception");
		}catch(Exception e) {
			assertTrue(false, "Threw a different exception");
		}
		
		try {
			while(iterator.hasPrevious())
				iterator.previous();
			
			//throws an exception
			iterator.previous();
			assertTrue(false, "Did not throw an exception");
		}catch(NoSuchElementException e) {
			assertTrue(true, "Threw the correct exception");
		}catch(Exception e) {
			assertTrue(false, "Threw a different exception");
		}
		
		//Testing UnsupportedOperationException
		try {
			iterator.nextIndex();
			assertTrue(false, "Did not throw the right exception");
		}catch(UnsupportedOperationException e) {
			assertTrue(true, "Threw the right exception");
		}
	}

	@Test
	void testAddToEnd() {
		intList.addToFront(a);
		intList.addToFront(b);
		intList.addToFront(c);
		intList.addToFront(d);
		
		//test
		intList.addToEnd(e);
		ListIterator<Integer> iterator = intList.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		
		assertEquals(e, iterator.next(), 0.01);
	}

	@Test
	void testAddToFront() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		
		//test
		intList.addToFront(e);
		ListIterator<Integer> iterator = intList.iterator();
		assertEquals(e, iterator.next(), 0.01);
	}

	@Test
	void testGetFirst() {
		
		//Tests involving removal of elements and checking results
		//are in other tests
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		assertEquals(a, intList.getFirst(), 0.01);
	}

	@Test
	void testGetLast() {
		//Tests involving removal of elements and checking results
		//are in other tests
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		assertEquals(d, intList.getLast(), 0.01);
	}

	@Test
	void testRetrieveFirstElement() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		assertEquals(a, intList.retrieveFirstElement(), 0.01);
		assertEquals(b, intList.getFirst(), 0.01);
	}

	@Test
	void testRetrieveLastElement() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		assertEquals(d, intList.retrieveLastElement(), 0.01);
		assertEquals(c, intList.getLast(), 0.01);
	}

	@Test
	void testGetSize() {
		assertEquals(0, intList.getSize(), 0.01);
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		assertEquals(4, intList.getSize(), 0.01);
		intList.addToEnd(e);
		assertEquals(5, intList.getSize(), 0.01);
	}

	@Test
	void testRemove() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		intList.remove(a, intComp);
		intList.remove(d, intComp);
		assertEquals(b, intList.getFirst(), 0.01);
		assertEquals(c, intList.getLast(), 0.01);
	}

	@Test
	void testToArrayList() {
		intList.addToEnd(a);
		intList.addToEnd(b);
		intList.addToEnd(c);
		intList.addToEnd(d);
		ArrayList<Integer> arList= intList.toArrayList();
		assertEquals(a, arList.get(0), 0.01);
		assertEquals(b, arList.get(1), 0.01);
		assertEquals(c, arList.get(2), 0.01);
		assertEquals(d, arList.get(3), 0.01);
	}

}
