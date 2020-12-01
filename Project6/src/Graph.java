/**
 * The purpose of Graph is to serve as
 * the main data structure to be used
 * in TownGraphManager, being able to
 * store vertices and edges, and even
 * find the shortest path between
 * vertices.
 * 
 * I swear I have not copied any code
 * @author Nicholas A. Baker
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph implements GraphInterface<Town, Road>{
	private HashSet<Town> vertices;
	private HashSet<Road> edges;
	private ArrayList<String> shortestPath;
	
	public Graph(){
		vertices = new HashSet<Town>();
		edges = new HashSet<Road>();
	}
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) return null;
		
		//If none of the target vertices are null, then the normal search is done, otherwise return null.
		else{
			Iterator<Road> iterator = edges.iterator();
			Road current;
			while(iterator.hasNext()) {
				current = iterator.next();
				if(current.contains(sourceVertex) && current.contains(destinationVertex))
					return current;
			}
		}
		return null;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
			throws IllegalArgumentException, NullPointerException{
		
		/*
		 * If any of the vertexes are null or not contained
		 * in the graph, then an exception is thrown.
		 */
		if(sourceVertex == null || destinationVertex == null) 
			throw new NullPointerException("Cannot add an edge that uses a null");
		
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			throw new IllegalArgumentException("One or both vertices is not contained in the graph");
		
		/*
		 * Creates a new edge, adds it to the graph, and
		 * updates the neighbors list for each node.
		 */
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		boolean result = edges.add(newRoad);
		
		//BE CAREFUL OF THIS
		sourceVertex.addNeighbor(destinationVertex);
		destinationVertex.addNeighbor(sourceVertex);
		if(!result) return null;
		return newRoad;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) throws NullPointerException{
		
		//Throws an exception if the vertex is null, otherwise tries to add it.
		if(v == null) throw new NullPointerException("Cannot add a null");
		else {
			if(vertices.contains(v)) return false;
			else {
				vertices.add(v);
				
				/*
				 * If you need to add the neighbors, 
				 * then add them here.
				 */
				return true;
			}
		}
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		/*
		 * The contains method in HashSet uses the equals method in
		 * the class being searched for, and the equals method in road
		 * is only based on the towns on the ends of the roads, 
		 * so we can just do this.
		 */
		Road test = new Road(sourceVertex, destinationVertex, "for searching purposes");
		if(edges.contains(test)) return true;
		return false;
	}

	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		
		//Iterates through the vertex set to find the desired town
		Iterator<Town> iterator = vertices.iterator();
		Town current;
		while(iterator.hasNext()) {
			current = iterator.next();
			if(current.equals(v)) return true;
		}
		return false;
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		return edges;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) throws NullPointerException, IllegalArgumentException{
		
		/*
		 * If the vertex is null or is not in the graph,
		 * then an exception is thrown.
		 */
		if(vertex == null) throw new NullPointerException("Cannot find a vertex that contains a null");
		if(!containsVertex(vertex)) throw new IllegalArgumentException("Vertex does not exist in the graph");
		
		/*
		 * Iterates through the edge set to find
		 * all edges that have the vertex and stores them in the result set.
		 */
		HashSet<Road> result = new HashSet<Road>();
		Iterator<Road> iterator = edges.iterator();
		Road current;
		
		while(iterator.hasNext()) {
			current = iterator.next();
			if(current.contains(vertex)) result.add(current);
		}
		return result;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road removedRoad = new Road(sourceVertex, destinationVertex, weight, description);
		Iterator<Road> iterator = edges.iterator();
		Road current;
		
		/*
		 * Iterates through the edge set and finds
		 * the edge to be removed. Otherwise, returns null.
		 */
		while(iterator.hasNext()) {
			current = iterator.next();
			
			//If the two edges have the same vertices
			if(current.equals(removedRoad)) {
				
				//If the weights are equal or if the weight in question is -1
				if((weight > -1 && weight == current.getWeight()) || weight == -1) {
					
					//If they have the same names or if the description in question is null
					if((description != null && description.equals(current.getName())) || description == null) {
						
						//All conditions met. Remove and return edge, and update neighbors.
						edges.remove(current);
						
						//BE CAREFUL OF THIS
						sourceVertex.removeNeighbor(destinationVertex);
						destinationVertex.removeNeighbor(sourceVertex);
						return current;
					}
				}
			}
		}
		return null;
	}

	 /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		HashSet<Road> edgesToBeRemoved = new HashSet<Road>();
		if(!vertices.contains(v)) return false;
		else {
			vertices.remove(v);
			Iterator<Road> iterator = edges.iterator();
			Road current;
			
			while(iterator.hasNext()) {
				current = iterator.next();
				if(current.contains(v)) edgesToBeRemoved.add(current);
			}
			for(Road r: edgesToBeRemoved) edges.remove(r);
			return true;
		}
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return vertices;
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */  
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> result = new ArrayList<String>();
		
		//Find the path from the source to the destination
		Stack<String> path = new Stack<String>();
		String currentNode = "";
		boolean pathExists = false;
		
		for(int i = 0; i < shortestPath.size(); i++) {
			if(shortestPath.get(i).contains("to " + destinationVertex.getName())) {
				path.push(shortestPath.get(i));
				currentNode = shortestPath.get(i).substring(0, shortestPath.get(i).indexOf(" via"));
				pathExists = true;
				break;
			}
		}
		if(!pathExists) return null;
		while(!currentNode.equals(sourceVertex.getName())) {
			for(int i = 0; i < shortestPath.size(); i++) {
				if(shortestPath.get(i).contains("to " + currentNode)) {
					path.push(shortestPath.get(i));
					currentNode = shortestPath.get(i).substring(0, shortestPath.get(i).indexOf(" via"));
					break;
				}
			}
		}
		
		//Gets the path to the destination
		while(!path.empty()) {
			result.add(path.pop());
		}
		
		return result;
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		/*
		 * The notation for each shortest path is stored
		 * in shortestPath
		 */
		shortestPath = new ArrayList<String>();
		HashSet<Town> containedVertices = new HashSet<Town>();
		HashSet<Road> containedEdges = new HashSet<Road>();
		HashSet<Road> possibleEdges = new HashSet<Road>();
		HashSet<Road> currentEdges = new HashSet<Road>();
		HashSet<Town> toBeRemoved = new HashSet<Town>();
		Queue<Town> vertexQueue = new PriorityQueue<Town>();
		Town current;
		
		shortestPath.add(sourceVertex.getName() + " via NONE to " + sourceVertex.getName() + " 0 mi");
		containedVertices.add(sourceVertex);
		vertexQueue.add(sourceVertex);
		
		/*
		 * Inserts the notation for the neighbors of the source node 
		 */
		currentEdges = (HashSet<Road>) edgesOf(sourceVertex);
		for(Road r: currentEdges) {
			Town destination;
			if(r.getSource().equals(sourceVertex)) destination = r.getDestination();
			else destination = r.getSource();
			shortestPath.add(sourceVertex.getName() + " via " + r.getName() + 
					" to " + destination.getName() + " " + r.getWeight() + " mi");
		}
		
		//BEGIN ALGORITHM
		while((containedVertices.size() != vertices.size()) && vertexQueue.size() >= 0) {
			
			/*
			 * Gets the minimum road for each node in vertexQueue
			 * and puts them in possibleEdges
			 */
			for(Town t: vertexQueue) {
				currentEdges = (HashSet<Road>) edgesOf(t);
				int minWeight = 999999999;
				Road minimum = null;
				
				/*
				 * Gets the minimum road for the node
				 */
				for(Road r: currentEdges) {
					
					if((r.getWeight() < minWeight) && !containedEdges.contains(r) 
							&& !(containedVertices.contains(r.getSource()) 
									&& containedVertices.contains(r.getDestination()))) {
						minimum = r;
						minWeight = r.getWeight();
					}
				}
				if(minimum != null) {
					if(minimum.getSource().equals(t)) possibleEdges.add(minimum);
					else possibleEdges.add(new Road(minimum.getDestination(), minimum.getSource(),
							minimum.getWeight(), minimum.getName()));
				}
				else toBeRemoved.add(t);
			}
			
			//remove nodes to be removed
			for(Town t: toBeRemoved) vertexQueue.remove(t);
			
			/*
			 * Gets the smallest edge, adds the new node to the queue
			 * and containedEdges, and does notation on the shortest path
			 * to the neighbors of said node
			 */
			int min = 999999;
			Road minimum = null;
			
			for(Road r: possibleEdges) {
				if(r.getWeight() < min) {
					minimum = r;
					min = r.getWeight();
				}
			}
			
			//Notation time
			if(minimum != null) {
				
				//Gets the neighboring node and adds it to the queue and containedVertices
				containedEdges.add(minimum);
				Town newNode = minimum.getDestination();
				containedVertices.add(newNode);
				vertexQueue.add(newNode);
				
				//Notation starts here
				currentEdges = (HashSet<Road>) edgesOf(newNode);
				
				for(Road r: currentEdges) {
					
					//Gets name of neighbor
					String nextNodeName;
					if(r.getSource().equals(newNode)) 
						nextNodeName = r.getDestination().getName();
					else nextNodeName = r.getSource().getName();
					
					//Determines if there is a notation for the neighbor already in shortestPath
					int notationIndex = -1;
					for(int i = 0; i < shortestPath.size(); i++) {
						if(shortestPath.get(i).contains("to " + nextNodeName)) {
							notationIndex = i;
							break;
						}
					}
					
					//If there is no notation present
					if(notationIndex == -1) {
						
						//Get weight of route to newly added node
						int newNodeWeight = -1;
						for(int i = 0; i < shortestPath.size(); i++) {
							if(shortestPath.get(i).contains("to " + newNode.getName())) {
								newNodeWeight = getTotalWeight(shortestPath.get(i), sourceVertex);
								break;
							}
						}
						
						//Add notation of path from source to neighbor through the newly added node
						shortestPath.add(newNode.getName() + " via " + r.getName() + " to " + nextNodeName + " "
								+ (r.getWeight()) + " mi");
					}
					else {
						
						//gets the weight of both nodes
						int neighborWeight = getTotalWeight(shortestPath.get(notationIndex), sourceVertex);
						int newNodeWeight = -1;
						for(int i = 0; i < shortestPath.size(); i++) {
							if(shortestPath.get(i).contains("to " + newNode.getName())) {
								newNodeWeight = getTotalWeight(shortestPath.get(i), sourceVertex);
								break;
							}
						}
						
						//If a new shortest path from the new node to the neighbor has been found,
						//overwrite that data
						if(newNodeWeight + r.getWeight() < neighborWeight) {
							shortestPath.remove(notationIndex);
							shortestPath.add(newNode.getName() + " via " + r.getName() + 
									" to " + nextNodeName + " " + (r.getWeight()) + " mi");
						}
					}
				}
				//END NOTATION LOOP
			}
			else break;
			possibleEdges.clear();
			toBeRemoved.clear();
		}
	}
	
	/**
	 * Returns the weight value contained in the notation String provided.
	 * Example: "Town_1 via Road_1 to Town_2 30 mi" would return 30 because that
	 * is the weight value according to the notation.
	 * @param str
	 * @return the weight value contained in the notation String provided
	 */
	private int getInteger(String str) {
		for(int i = str.indexOf("mi") -2; i > -1; i--) {
			if(str.charAt(i) == ' ') return Integer.parseInt(str.substring(i + 1, str.indexOf("mi") - 1));
		}
		return -1;
	}
	
	/**
	 * Gets the total cost of traversal from the source node (the Town)
	 * to the destination node (contained in the provided notation String)
	 * using other notation strings found in dijkstraShortestPath().
	 * @param str - the notation of the last step before reaching the destination.
	 * @param sourceVertex
	 * @return the total cost of traversal for the shortest path from the source vertex
	 * to the destination
	 */
	private int getTotalWeight(String str, Town sourceVertex) {
		String currentNode = str.substring(0, str.indexOf(" via"));
		Stack <String> path = new Stack<String>();
		int weight = 0;
		path.push(str);
		
		while(!currentNode.equals(sourceVertex.getName())) {
			for(int i = 0; i < shortestPath.size(); i++) {
				if(shortestPath.get(i).contains("to " + currentNode)) {
					path.push(shortestPath.get(i));
					currentNode = shortestPath.get(i).substring(0, shortestPath.get(i).indexOf(" via"));
					break;
				}
			}
		}
		while(!path.empty())
			weight += getInteger(path.pop());
		return weight;
	}

}
