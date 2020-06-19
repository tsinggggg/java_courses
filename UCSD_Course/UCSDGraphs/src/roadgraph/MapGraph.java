/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.Queue;
import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private int numVertices;
	private int numEdges;
	private HashMap<GeographicPoint, MyNode> nodes;
	private HashMap<MyNode, HashMap<MyNode,MyEdge>> edges;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		numVertices = 0;
		numEdges = 0;
		nodes = new HashMap<GeographicPoint, MyNode>();
		edges = new HashMap<MyNode, HashMap<MyNode,MyEdge>>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		return nodes.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		for(GeographicPoint l: nodes.keySet()) {
			if (l.getX()==location.getX() & l.getY()==location.getY()) {
				return false;
			}
		}
		MyNode curr = new MyNode(location);
		nodes.put(location, curr);
		numVertices += 1;
		edges.put(curr, new HashMap<MyNode, MyEdge>());
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		// checks
		if (from==null) {throw new IllegalArgumentException();}
		if (to==null) {throw new IllegalArgumentException();}
		if (roadName==null) {throw new IllegalArgumentException();}
		if (roadType==null) {throw new IllegalArgumentException();}
		if (length<0) {throw new IllegalArgumentException();}
		if (!(nodes.containsKey(from) & nodes.containsKey(to))) {
			throw new IllegalArgumentException();
		}
		
		// add edge
		MyEdge e = new MyEdge(nodes.get(from), nodes.get(to), roadName, roadType, length);
		edges.get(nodes.get(from)).put(nodes.get(to), e);
		numEdges += 1;
		//TODO: Implement this method in WEEK 3
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		//init
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		Queue<GeographicPoint> q = new LinkedList<GeographicPoint>(); 
		HashMap<GeographicPoint, GeographicPoint> n = new HashMap<GeographicPoint, GeographicPoint>();
		
		visited.add(start);
		q.add(start);
		
		while (!q.isEmpty()) {
			GeographicPoint curr = q.poll();
			if (curr.getX()==goal.getX() & curr.getY()==goal.getY()) {
				return constructPath(n, goal, start);
			}
			else {
				Set<MyNode> s = edges.get(nodes.get(curr)).keySet();
				if (!s.isEmpty()) {
				for(MyNode nb: s) {
					if (!visited.contains(nb.getLocation())) {
						q.add(nb.getLocation());
						visited.add(nb.getLocation());

						n.put(nb.getLocation(),curr);
					}
					
				}
				}
			}
		}

		return new LinkedList<GeographicPoint>();
	}
	
	private List<GeographicPoint> constructPath(HashMap<GeographicPoint, GeographicPoint> n,
			GeographicPoint goal, GeographicPoint start){
		
		LinkedList<GeographicPoint> ret = new LinkedList<GeographicPoint>();

		GeographicPoint curr = goal;
		while((curr.getX() != start.getX()) | (curr.getY() != start.getY()) ) {
			ret.addFirst(curr);
			curr = n.get(curr);
		}
		ret.addFirst(start);
		
		return ret;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		//init
		MyNode s = nodes.get(start) ;
		MyNode g = nodes.get(goal);
		
		HashMap<MyNode, Double> disFromStart = new HashMap<MyNode, Double>();
		for(MyNode n:edges.keySet()) {
			if(n!=s) {
				disFromStart.put(n, Double.POSITIVE_INFINITY);
			}
			else {
				disFromStart.put(n, 0.0);
			}
		}
		PriorityQueue<NodeDis> pQueue = new PriorityQueue<NodeDis>();
		HashSet<MyNode> visited  = new HashSet<MyNode>();
		HashMap<GeographicPoint, GeographicPoint> pmap = new HashMap<GeographicPoint, GeographicPoint> ();
		
		pQueue.add(new NodeDis(s, 0.0));
		//visited.add(s);
		
		while (!pQueue.isEmpty()) {
			
			NodeDis curr_nd = pQueue.poll();
			MyNode curr = curr_nd.getNode();
			//double curr_dis = curr_nd.getDis();
			System.out.println(curr.getLocation().toString());
			if(!visited.contains(curr)) {
				
				if (curr == g) {
					//System.out.println("found goal");
					//return new LinkedList<GeographicPoint>();
					return constructPath(pmap, goal,  start);
				}
				else {
					
					for(MyNode n: edges.get(curr).keySet()) {
						if(!visited.contains(n)) {
							double s_to_curr = disFromStart.get(curr);
							double s_to_n = disFromStart.get(n);
							//double curr_to_n = curr.disTo(n);
							double curr_to_n = edges.get(curr).get(n).getL();
							
							if((s_to_curr + curr_to_n) < s_to_n) {
								disFromStart.put(n, s_to_curr + curr_to_n);
								pQueue.add(new NodeDis(n, s_to_curr + curr_to_n));
								//System.out.println(n.getLocation().toString() + "---------" + curr.getLocation().toString());
								pmap.put(n.getLocation(), curr.getLocation());
								
							}
							
						}
					}
					visited.add(curr);
				}
				
				
			}
		}
		
		return new LinkedList<GeographicPoint>();
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		MyNode s = nodes.get(start);
		MyNode g = nodes.get(goal);

		HashSet<MyNode> visited = new HashSet<MyNode>();
		PriorityQueue<NodeDis> pQueue = new PriorityQueue<NodeDis>();
		HashMap<MyNode, Double> disFromS = new HashMap<MyNode, Double>();
		for(MyNode n:edges.keySet()) {
			if(n!=s) {
				disFromS.put(n, Double.POSITIVE_INFINITY);
			}
			else {
				disFromS.put(n, 0.0);
			}
		}
		HashMap<GeographicPoint, GeographicPoint> pmap = new HashMap<GeographicPoint, GeographicPoint>();
		
		//init 
		pQueue.add(new NodeDis(s, s.disTo(g)));
		
		while (!pQueue.isEmpty()) {
			
			NodeDis curr_nd = pQueue.poll();
			MyNode curr = curr_nd.getNode();
			System.out.println(curr.getLocation().toString());
			if(!visited.contains(curr)) {
				
				if(curr == g) {
					return constructPath(pmap, goal, start);
				}
				else {
					
					for(MyNode n: edges.get(curr).keySet()) {
						if(!visited.contains(n)) {
							Double s_to_curr =  disFromS.get(curr);
							Double curr_to_n = edges.get(curr).get(n).getL();
							Double s_to_n = disFromS.get(n);
							
							if((s_to_curr + curr_to_n) < s_to_n) {
								disFromS.put(n, s_to_curr + curr_to_n);
								pQueue.add(new NodeDis(n, s_to_curr + curr_to_n + n.disTo(g)));
								pmap.put(n.getLocation(), curr.getLocation());
								
							}
							
						}
						
					}
					visited.add(curr);
				}
			}

		}
		
		return new LinkedList<GeographicPoint>();
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		System.out.println("Test D");
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		System.out.println("Test A");
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
	}
	
}
