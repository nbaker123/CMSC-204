import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {

	private TownGraphManager graph;
	private String[] town;
	
	@BeforeEach
	void setUp() throws Exception {
		graph = new TownGraphManager();
		  town = new String[7];
		  
		  for (int i = 1; i < 7; i++) {
			  town[i] = "Town Number " + i;
			  graph.addTown(town[i]);
		  }

		  graph.addRoad(town[1], town[2], 6, "Road_1");
		  graph.addRoad(town[1], town[6], 3, "Road_2");
		  graph.addRoad(town[1], town[5], 10, "Road_3");
		  graph.addRoad(town[1], town[4], 5, "Road_4");
		  graph.addRoad(town[2], town[3], 4, "Road_5");
		  graph.addRoad(town[3], town[5], 1, "Road_6");
		  graph.addRoad(town[5], town[6], 7, "Road_7");
		  graph.addRoad(town[5], town[4], 2, "Road_8");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
		town = null;
	}

	@Test
	void testAddRoad() {
		assertEquals(false, graph.containsRoadConnection(town[2], town[5]));
		graph.addRoad(town[2], town[5], 1, "Test road");
		assertEquals(true, graph.containsRoadConnection(town[2], town[5]));
	}

	@Test
	void testGetRoad() {
		String r1 = graph.getRoad(town[1], town[2]);
		String r2 = graph.getRoad(town[2], town[3]);
		String r3 = graph.getRoad(town[3], town[5]);
		String r4 = graph.getRoad(town[2], town[5]);
		
		assertEquals("Road_1", r1);
		assertEquals("Road_5", r2);
		assertEquals("Road_6", r3);
		assertEquals(null, r4);
	}

	@Test
	void testAddTown() {
		assertEquals(false, graph.containsTown("Town Number 20"));
		graph.addTown("Town Number 20");
		assertEquals(true, graph.containsTown("Town Number 20"));
	}

	@Test
	void testGetTown() {
		Town t1 = graph.getTown("Town Number 1");
		Town t2 = graph.getTown("Town Number 5");
		Town t3 = graph.getTown("Town Number 3");
		Town t4 = graph.getTown("Town Number 20");
		
		assertEquals(true, t1.equals(new Town("Town Number 1")));
		assertEquals(true, t2.equals(new Town("Town Number 5")));
		assertEquals(true, t3.equals(new Town("Town Number 3")));
		assertEquals(true, t4 == null);
	}

	@Test
	void testContainsTown() {
		assertEquals(true, graph.containsTown("Town Number 1"));
		assertEquals(true, graph.containsTown("Town Number 2"));
		assertEquals(true, graph.containsTown("Town Number 6"));
		assertEquals(true, graph.containsTown("Town Number 4"));
		assertEquals(false, graph.containsTown("Town Number 20"));
	}

	@Test
	void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[6]));
		assertEquals(true, graph.containsRoadConnection(town[1], town[2]));
		assertEquals(true, graph.containsRoadConnection(town[1], town[5]));
		assertEquals(true, graph.containsRoadConnection(town[1], town[4]));
		assertEquals(false, graph.containsRoadConnection(town[1], town[3]));
	}

	@Test
	void testAllRoads() {
		ArrayList<String> result = graph.allRoads();
		assertEquals("Road_1", result.get(0));
		assertEquals("Road_2", result.get(1));
		assertEquals("Road_3", result.get(2));
		assertEquals("Road_4", result.get(3));
		assertEquals("Road_5", result.get(4));
		assertEquals("Road_6", result.get(5));
	}

	@Test
	void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[4], town[5]));
		graph.deleteRoadConnection(town[4], town[5], "Road_8");
		assertEquals(false, graph.containsRoadConnection(town[4], town[5]));
	}

	@Test
	void testDeleteTown() {
		assertEquals(true, graph.containsTown(town[4]));
		graph.deleteTown(town[4]);
		assertEquals(false, graph.containsTown(town[4]));
	}

	@Test
	void testAllTowns() {
		ArrayList<String> result = graph.allTowns();
		assertEquals("Town Number 1", result.get(0));
		assertEquals("Town Number 2", result.get(1));
		assertEquals("Town Number 3", result.get(2));
		assertEquals("Town Number 4", result.get(3));
		assertEquals("Town Number 5", result.get(4));
		assertEquals("Town Number 6", result.get(5));
	}

	@Test
	void testGetPathA() {
		ArrayList<String> result = graph.getPath(town[1], town[4]);
		assertEquals("Town Number 1 via Road_4 to Town Number 4 5 mi", result.get(0).trim());
	}
	
	@Test
	void testGetPathB() {
		ArrayList<String> result = graph.getPath(town[1], town[5]);
		assertEquals("Town Number 1 via Road_4 to Town Number 4 5 mi", result.get(0).trim());
		assertEquals("Town Number 4 via Road_8 to Town Number 5 2 mi", result.get(1).trim());
	}
	
	@Test
	void testGetPathC() {
		ArrayList<String> result = graph.getPath(town[5], town[2]);
		assertEquals("Town Number 5 via Road_6 to Town Number 3 1 mi", result.get(0).trim());
		assertEquals("Town Number 3 via Road_5 to Town Number 2 4 mi", result.get(1).trim());
	}

	@Test
	void testReadFile() {
		TownGraphManager testGraph = new TownGraphManager();
		TownGraphManager testGraph2 = new TownGraphManager();
		
		testGraph.readFile("US Towns.txt");
		assertEquals(true, testGraph.containsRoadConnection("Chicago", "Detroit"));
		assertEquals(true, testGraph.containsRoadConnection("Kansas City", "Nashville"));
		assertEquals(true, testGraph.containsRoadConnection("Atlanta", "Dallas"));
		
		testGraph2.readFile("MD Towns.txt");
		assertEquals(true, testGraph2.containsRoadConnection("Potomac", "Rockville"));
		assertEquals(true, testGraph2.containsRoadConnection("Frederick", "Clarksburg"));
		assertEquals(true, testGraph2.containsRoadConnection("Gaithersburg", "Olney"));
	}

}
