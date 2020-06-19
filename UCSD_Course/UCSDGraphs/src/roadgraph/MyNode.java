package roadgraph;
import geography.GeographicPoint;


public class MyNode {
	private GeographicPoint location;
	
	public MyNode(GeographicPoint l) {
		location = l;
	}
	public GeographicPoint getLocation() {
		return location;
	}
	public double disTo(MyNode n) {
		// TODO Auto-generated method stub
		return location.distance(n.location);
	}
}
