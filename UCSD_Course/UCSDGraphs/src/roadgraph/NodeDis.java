package roadgraph;

public class NodeDis implements Comparable{
	
	private MyNode to;
	private double dis;
	
	public NodeDis(MyNode n, double d) {
		to = n;
		dis = d;
	}
	
	public double getDis() {
		return dis;
	}
	
	public MyNode getNode() {
		return to;
	}

	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		NodeDis o = (NodeDis) other;
		return Double.compare(dis, o.dis);
	}
	
	

}
