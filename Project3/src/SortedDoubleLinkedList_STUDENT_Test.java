import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SortedDoubleLinkedList_STUDENT_Test {

	SortedDoubleLinkedList<Integer> intList;
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
		intList = new SortedDoubleLinkedList<Integer>(new IntegerComparator());
	}

	@AfterEach
	void tearDown() throws Exception {
		intList = null;
	}

	@Test
	void testIterator() {
		intList.add(a);
		intList.add(b);
		intList.add(c);
		intList.add(d);
		ListIterator<Integer> iterator = intList.iterator();
		
		//Testing next()
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next(), 0.01);
		assertEquals(d, iterator.next(), 0.01);
		assertEquals(a, iterator.next(), 0.01);
		assertEquals(c, iterator.next(), 0.01);
		assertEquals(false, iterator.hasNext());
		
		//Testing previous()
		assertEquals(true, iterator.hasPrevious());
		assertEquals(c, iterator.previous(), 0.01);
		assertEquals(a, iterator.previous(), 0.01);
		assertEquals(d, iterator.previous(), 0.01);
		assertEquals(b, iterator.previous(), 0.01);
		assertEquals(false, iterator.hasPrevious());
	}
	
	@Test
	void testIteratorExceptions() {
		intList.add(a);
		intList.add(b);
		intList.add(c);
		intList.add(d);
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
		try {
			intList.addToEnd(e);
			assertTrue(false, "Did not throw the correct exception");
		}catch(UnsupportedOperationException e) {
			assertTrue(true, "Threw the correct exception");
		}
	}

	@Test
	void testAddToFront() {
		try {
			intList.addToFront(e);
			assertTrue(false, "Did not throw the correct exception");
		}catch(UnsupportedOperationException e) {
			assertTrue(true, "Threw the correct exception");
		}
	}

	@Test
	void testAdd() {
		intList.add(a);
		intList.add(b);
		intList.add(c);
		intList.add(d);
	}

	@Test
	void testRemove() {
		intList.add(a);
		intList.add(b);
		assertEquals(b, intList.getFirst(), 0.01);
		intList.add(c);
		intList.add(d);
		intList.add(e);
		
		//real test
		ListIterator<Integer> iterator = intList.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next(), 0.01);
		assertEquals(d, iterator.next(), 0.01);
		assertEquals(a, iterator.next(), 0.01);
		assertEquals(e, iterator.next(), 0.01);
		assertEquals(c, iterator.next(), 0.01);
	}

}
