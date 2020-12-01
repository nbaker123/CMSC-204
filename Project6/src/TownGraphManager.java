/**
 * The purpose of TownGraphManager is to serve as the main utility class
 * for the manager program, which uses the methods and constructors of
 * graph to achieve functionality.
 * 
 * I swear I have not copied any code.
 * @author Nicholas A. Baker
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import java.util.Iterator;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface{

	private Graph graph = new Graph();
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		graph.addVertex(source);
		graph.addVertex(destination);
		Road result = graph.addEdge(source, destination, weight, roadName);
		if(result == null) return false;
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Road result = graph.getEdge(new Town(town1), new Town(town2));
		if(result == null) return null;
		return result.getName();
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Set<Town> vertices = graph.vertexSet();
		Iterator<Town> iterator = vertices.iterator();
		Town target = new Town(name);
		Town current;
		while(iterator.hasNext()) {
			current = iterator.next();
			if(current.equals(target)) return current;
		}
		return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		return graph.containsEdge(source, destination);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> result = new ArrayList<String>();
		for(Road r: roads) result.add(r.getName());
		Collections.sort(result);
		return result;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		Road target = graph.getEdge(source, destination);
		if(target == null) return false;
		graph.removeEdge(source, destination, target.getWeight(), road);
		return true;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> result = new ArrayList<String>();
		for(Town t: towns) result.add(t.getName());
		Collections.sort(result);
		return result;

	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		ArrayList<String> result = new ArrayList<String>();
		if((graph.containsVertex(source) && graph.containsVertex(destination)) 
				&& (!graph.edgesOf(source).isEmpty() && !graph.edgesOf(destination).isEmpty())) {
			result = graph.shortestPath(source, destination);
			if(result == null) return new ArrayList<String>();
			return result;
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Populates the graph with the roads provided
	 * in a file. The file extension must be included in
	 * the file name or this method will not work.
	 * @param fileName - the file name
	 */
	public void readFile(String fileName) {
		try {
			Scanner inFile = new Scanner(new File(fileName));
			String currentLine = "";
			String name = "";
			int weight = 0;
			String source = "";
			String destination = "";
			
			while(inFile.hasNext()) {
				currentLine = inFile.nextLine();
				
				//Get vertex name, weight, source and destination
				name = currentLine.substring(0, currentLine.indexOf(','));
				
				weight = Integer.parseInt(currentLine.substring(currentLine.indexOf(',') + 1, 
						currentLine.indexOf(';')));
				
				source = currentLine.substring(currentLine.indexOf(';') + 1);
				source = source.substring(0, source.indexOf(';'));
				
				destination = currentLine.substring(currentLine.indexOf(';') + 1);
				destination = destination.substring(destination.indexOf(';') + 1, destination.length());
				
				//If the vertices are not already in the graph, 
				//add them in, and do the same for the edge
				Town sourceTown = new Town(source);
				Town destinationTown = new Town(destination);
				if(!graph.containsVertex(sourceTown)) graph.addVertex(sourceTown);
				if(!graph.containsVertex(destinationTown)) graph.addVertex(destinationTown);
				if(!graph.containsEdge(sourceTown, destinationTown)) 
					graph.addEdge(sourceTown, destinationTown, weight, name);
			}
		}catch(Exception e) {
			System.out.println("error in readFile");
			e.printStackTrace();
		}
	}

}
