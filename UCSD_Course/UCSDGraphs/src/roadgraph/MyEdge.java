package roadgraph;

public class MyEdge {
	private MyNode from;
	private MyNode to;
	private String name;
	private String type;
	private double length;
	
	public MyEdge(MyNode f, MyNode t, String n, String ty, double l) {
		from = f;
		to = t;
		name = n;
		type = ty;
		length = l;
	}
	
	public double getL() {
		return length;
	}
}
