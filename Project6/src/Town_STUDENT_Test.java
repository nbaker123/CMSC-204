import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {
	Town first;
	Town second;
	Town third;
	Town fourth;
	Town fifth;
	@BeforeEach
	void setUp() throws Exception {
		first = new Town("first town");
		second = new Town("first town");
		third = new Town("third town");
		fourth = new Town("fourth town");
		fifth = new Town("fifth town");
	}

	@AfterEach
	void tearDown() throws Exception {
		first = null;
		second = null;
		third = null;
		fourth = null;
		fifth = null;
	}

	@Test
	void testHashCode() {
		assertEquals(first.hashCode(), second.hashCode(), 0.1);
		assertEquals(false, first.hashCode() == fourth.hashCode());
		assertEquals(false, second.hashCode() == fifth.hashCode());
	}

	@Test
	void testGetName() {
		assertEquals("first town", first.getName());
		assertEquals("first town", second.getName());
		assertEquals("third town", third.getName());
		assertEquals("fourth town", fourth.getName());
		assertEquals("fifth town", fifth.getName());
	}

	@Test
	void testSetName() {
		second.setName("second town");
		fifth.setName("tenth town");
		assertEquals("second town", second.getName());
		assertEquals("tenth town", fifth.getName());
	}

	@Test
	void testGetNeighbors() {
		first.addNeighbor(third);
		first.addNeighbor(fifth);
		first.addNeighbor(fourth);
		Set set = first.getNeighbors();
		first.addNeighbor(third);
		first.addNeighbor(fifth);
		first.addNeighbor(fourth);
		Iterator i = first.getNeighbors().iterator();
		Town neighbor1 = (Town) i.next();
		Town neighbor2 = (Town) i.next();
		Town neighbor3 = (Town) i.next();
		assertEquals("third town", neighbor1.getName());
		assertEquals("fifth town", neighbor2.getName());
		assertEquals("fourth town", neighbor3.getName());
	}

	@Test
	void testSetNeighbors() {
		LinkedHashSet<Town> newSet = new LinkedHashSet<Town>();
		newSet.add(new Town("test 1"));
		newSet.add(new Town("test 2"));
		newSet.add(new Town("test 3"));
		first.setNeighbors(newSet);
		Iterator i = first.getNeighbors().iterator();
		Town neighbor1 = (Town) i.next();
		Town neighbor2 = (Town) i.next();
		Town neighbor3 = (Town) i.next();
		assertEquals("test 1", neighbor1.getName());
		assertEquals("test 2", neighbor2.getName());
		assertEquals("test 3", neighbor3.getName());
	}

	@Test
	void testAddNeighbor() {
		first.addNeighbor(third);
		first.addNeighbor(fifth);
		first.addNeighbor(fourth);
		Iterator i = first.getNeighbors().iterator();
		Town neighbor1 = (Town) i.next();
		Town neighbor2 = (Town) i.next();
		Town neighbor3 = (Town) i.next();
		assertEquals("third town", neighbor1.getName());
		assertEquals("fifth town", neighbor2.getName());
		assertEquals("fourth town", neighbor3.getName());
	}

	@Test
	void testRemoveNeighbor() {
		first.addNeighbor(third);
		first.addNeighbor(fifth);
		first.addNeighbor(fourth);
		
		Iterator i = first.getNeighbors().iterator();
		Town neighbor1 = (Town) i.next();
		Town neighbor2 = (Town) i.next();
		Town neighbor3 = (Town) i.next();
		
		assertEquals("third town", neighbor1.getName());
		assertEquals("fifth town", neighbor2.getName());
		assertEquals("fourth town", neighbor3.getName());
		
		first.removeNeighbor(third);
		i = first.getNeighbors().iterator();
		neighbor1 = (Town) i.next();
		neighbor2 = (Town) i.next();
		assertEquals("fifth town", neighbor1.getName());
		assertEquals("fourth town", neighbor2.getName());
		
		first.removeNeighbor(fifth);
		i = first.getNeighbors().iterator();
		neighbor1 = (Town) i.next();
		assertEquals("fourth town", neighbor1.getName());
	}

	@Test
	void testCompareTo() {
		assertEquals(0, first.compareTo(second), 0.1);
		assertEquals(true, first.compareTo(third) < 1);
		assertEquals(true, first.compareTo(fourth) < 1);
		assertEquals(true, first.compareTo(fifth) > 1);
	}

	@Test
	void testEqualsTown() {
		assertEquals(true, first.equals(second));
		assertEquals(false, first.equals(third));
		assertEquals(false, first.equals(fourth));
		assertEquals(false, first.equals(fifth));
		
	}

	@Test
	public void testToString() {
		first.addNeighbor(third);
		first.addNeighbor(fifth);
		first.addNeighbor(fourth);
		assertEquals("first town with neighbors third town, fifth town, fourth town", first.toString());
	}
}