import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_TEST {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		town = new Town[7];

		for (int i = 1; i < 7; i++) {
			town[i] = new Town("Town Number " + i);
			graph.addVertex(town[i]);
		}

		graph.addEdge(town[1], town[2], 6, "Road_1");
		graph.addEdge(town[1], town[6], 3, "Road_2");
		graph.addEdge(town[1], town[5], 10, "Road_3");
		graph.addEdge(town[1], town[4], 5, "Road_4");
		graph.addEdge(town[2], town[3], 4, "Road_5");
		graph.addEdge(town[3], town[5], 1, "Road_6");
		graph.addEdge(town[5], town[6], 7, "Road_7");
		graph.addEdge(town[5], town[4], 2, "Road_8");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}

	@Test
	void testGetEdge() {
		assertEquals(new Road(town[1], town[2], 6, "Road_1"), graph.getEdge(town[1], town[2]));
		assertEquals(new Road(town[3], town[5], 1, "Road_6"), graph.getEdge(town[3], town[5]));
		assertEquals(new Road(town[5], town[4], 2, "Road_8"), graph.getEdge(town[4], town[5]));
	}

	@Test
	void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[2], town[5]));
		graph.addEdge(town[2], town[5], 1, "Road_TEST");
		assertEquals(true, graph.containsEdge(town[2], town[5]));
	}

	@Test
	void testAddVertex() {
		assertEquals(false, graph.containsVertex(new Town("newtown")));
		graph.addVertex(new Town("newtown"));
		assertEquals(true, graph.containsVertex(new Town("newtown")));
	}

	@Test
	void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[1], town[2]));
		assertEquals(true, graph.containsEdge(town[4], town[5]));
		assertEquals(false, graph.containsEdge(town[2], town[5]));
		assertEquals(false, graph.containsEdge(town[1], town[3]));
	}

	@Test
	void testContainsVertex() {
		assertEquals(true, graph.containsVertex(town[1]));
		assertEquals(true, graph.containsVertex(town[4]));
		assertEquals(false, graph.containsVertex(new Town("test1")));
		assertEquals(false, graph.containsVertex(new Town("test2")));
	}

	@Test
	void testEdgeSet() {
		Set<Road> edges = graph.edgeSet();
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Road> iterator = edges.iterator();

		while(iterator.hasNext())
			result.add(iterator.next().getName());
		Collections.sort(result);
		assertEquals("Road_1", result.get(0));
		assertEquals("Road_2", result.get(1));
		assertEquals("Road_3", result.get(2));
	}

	@Test
	void testEdgesOf() {
		Set<Road> edges = graph.edgesOf(town[1]);
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Road> iterator = edges.iterator();
		
		while(iterator.hasNext())
			result.add(iterator.next().getName());
		Collections.sort(result);
		assertEquals("Road_1", result.get(0));
		assertEquals("Road_2", result.get(1));
		assertEquals("Road_3", result.get(2));
		assertEquals("Road_4", result.get(3));
	}

	@Test
	void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[1], town[2]));
		graph.removeEdge(town[1], town[2], 6, "Road_1");
		assertEquals(false, graph.containsEdge(town[1], town[2]));
	}

	@Test
	void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[3]));
		assertEquals(true, graph.containsEdge(town[3], town[2]));
		assertEquals(true, graph.containsEdge(town[3], town[5]));
		graph.removeVertex(town[3]);
		assertEquals(false, graph.containsVertex(town[3]));
		assertEquals(false, graph.containsEdge(town[3], town[2]));
		assertEquals(false, graph.containsEdge(town[3], town[5]));
	}

	@Test
	void testVertexSet() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> result = new ArrayList<String>();
		for(Town t: towns) result.add(t.getName());
		Collections.sort(result);
		assertEquals("Town Number 1", result.get(0));
		assertEquals("Town Number 2", result.get(1));
		assertEquals("Town Number 3", result.get(2));
		assertEquals("Town Number 4", result.get(3));
	}
	
	@Test
	void testTownNumber1ToTownNumber4(){
		ArrayList<String> result = graph.shortestPath(town[1], town[4]);
		assertEquals("Town Number 1 via Road_4 to Town Number 4 5 mi", result.get(0).trim());
	}
	
	@Test
	void testTownNumber1ToTownNumber5(){
		ArrayList<String> result = graph.shortestPath(town[1], town[5]);
		assertEquals("Town Number 1 via Road_4 to Town Number 4 5 mi", result.get(0).trim());
		assertEquals("Town Number 4 via Road_8 to Town Number 5 2 mi", result.get(1).trim());
	}
	
	@Test
	void testTownNumber5ToTownNumber2(){
		ArrayList<String> result = graph.shortestPath(town[5], town[2]);
		assertEquals("Town Number 5 via Road_6 to Town Number 3 1 mi", result.get(0).trim());
		assertEquals("Town Number 3 via Road_5 to Town Number 2 4 mi", result.get(1).trim());
	}

}
