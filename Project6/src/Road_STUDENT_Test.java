import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	Road road1;
	Road road2;
	Road road3;
	Town town1 = new Town("The Sacred District");
	Town town2 = new Town("Bakerstown");
	Town town3 = new Town("Hacksburg");
	Town town4 = new Town("Itopolis");
	
	@BeforeEach
	void setUp() throws Exception {
		road1 = new Road(town1, town2, 12, "Main Road");
		road2 = new Road(town1, town3, 13, "Second Road");
		road3 = new Road(town3, town4, 14, "Third Road");
	}

	@AfterEach
	void tearDown() throws Exception {
		road1 = null;
		road2 = null;
		road3 = null;
	}

	@Test
	void testHashCode() {
		assertEquals(town1.hashCode() + town2.hashCode(), road1.hashCode(), 0.1);
		assertEquals(town1.hashCode() + town3.hashCode(), road2.hashCode(), 0.1);
		assertEquals(town3.hashCode() + town4.hashCode(), road3.hashCode(), 0.1);
	}

	@Test
	void testGetSource() {
		assertEquals(true, town1.equals(road1.getSource()));
		assertEquals(true, town1.equals(road2.getSource()));
		assertEquals(true, town3.equals(road3.getSource()));
	}

	@Test
	void testSetSource() {
		road1.setSource(town4);
		assertEquals(true, town4.equals(road1.getSource()));
		road2.setSource(town2);
		assertEquals(true, town2.equals(road2.getSource()));
		road2.setSource(new Town("newtown"));
		assertEquals(true, new Town("newtown").equals(road2.getSource()));
	}

	@Test
	void testGetDestination() {
		assertEquals(true, town2.equals(road1.getDestination()));
		assertEquals(true, town3.equals(road2.getDestination()));
		assertEquals(true, town4.equals(road3.getDestination()));
	}

	@Test
	void testSetDestination() {
		road1.setDestination(town4);
		assertEquals(true, town4.equals(road1.getDestination()));
		road3.setDestination(town2);
		assertEquals(true, town2.equals(road3.getDestination()));
		road2.setDestination(new Town("newtown"));
		assertEquals(true, new Town("newtown").equals(road2.getDestination()));
	}

	@Test
	void testGetName() {
		assertEquals("Main Road", road1.getName());
		assertEquals("Second Road", road2.getName());
		assertEquals("Third Road", road3.getName());
	}

	@Test
	void testSetName() {
		road1.setName("First");
		assertEquals("First", road1.getName());
		road2.setName("Second");
		assertEquals("Second", road2.getName());
		road3.setName("Third");
		assertEquals("Third", road3.getName());
	}

	@Test
	void testGetWeight() {
		assertEquals(12, road1.getWeight());
		assertEquals(13, road2.getWeight());
		assertEquals(14, road3.getWeight());
	}

	@Test
	void testSetWeight() {
		road1.setWeight(20);
		assertEquals(20, road1.getWeight(), 0.1);
		road2.setWeight(40);
		assertEquals(40, road2.getWeight());
		road3.setWeight(60);
		assertEquals(60, road3.getWeight(), 0.1);
	}

	@Test
	void testCompareTo() {
		assertEquals(true, road1.compareTo(road2) < 0);
		assertEquals(true, road1.compareTo(road3) < 0);
		assertEquals(true, road3.compareTo(road2) > 0);
		assertEquals(true, road3.compareTo(road1) > 0);
	}

	@Test
	void testEqualsObject() {
		Road newRoad = new Road(town1, town2, 15, "New Main Road");
		assertEquals(true, road1.equals(newRoad));
		assertEquals(false, road1.equals(road2));
		assertEquals(false, road1.equals(road3));
		assertEquals(false, road3.equals(road2));
		newRoad = new Road(town3, town4, 15, "New Main Road");
		assertEquals(true, road3.equals(newRoad));
	}

	@Test
	void testContains() {
		assertEquals(true, road1.contains(town1));
		assertEquals(true, road1.contains(town2));
		assertEquals(true, road2.contains(town3));
		assertEquals(true, road3.contains(town4));
		assertEquals(false, road3.contains(town1));
		assertEquals(false, road2.contains(town4));
		assertEquals(false, road1.contains(town4));
	}

	@Test
	void testToString() {
		assertEquals("Road: Main Road connects towns: The Sacred District and Bakerstown", road1.toString());
		assertEquals("Road: Second Road connects towns: The Sacred District and Hacksburg", road2.toString());
		assertEquals("Road: Third Road connects towns: Hacksburg and Itopolis", road3.toString());
	}

}
