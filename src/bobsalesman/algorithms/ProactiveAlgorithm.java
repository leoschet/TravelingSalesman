package bobsalesman.algorithms;

import java.util.Vector;
import java.lang.Math;

public abstract class ProactiveAlgorithm {
	
	private int totalPhases;
	private double progress;
	private double totalDistance;
	
	protected Vector<Pair> pairs;
	protected Vector<Node> sorted;
	
	public ProactiveAlgorithm(int totalPhases) {
		this.sorted = new Vector<Node>();
		this.totalPhases = totalPhases;
		this.progress = 0;
		this.totalDistance = 0;
	}
	
	protected double distance(Node a, Node b){
		double distance = (a.getX() - b.getX()) * (a.getX() - b.getX());
		distance += (a.getY() - b.getY()) * (a.getY() - b.getY());
		distance = Math.sqrt(distance);
		return distance;
	}
	
	protected void updateProgress() {
		progress += Math.ceil(1/totalPhases);
	}
	
	public double getProgress() {
		return progress;
	}
	
	protected void increaseTotalDistance(double newDistance) {
		totalDistance += newDistance;
	}
	
	public double getTotalDistance() {
		return totalDistance;
	}
	
	protected void sort(Node[] nodes) {
		Node first = null;
		Node next;
		
		for(int i = 0; i < nodes.length; i++)
			if(nodes[i].left == null || nodes[i].right == null) {
				first = nodes[i]; 
				break;
			}

		if(first.left == null) 
			next = first.right;
		else 
			next = first.left;
		
		sorted.add(first);

		while(next != null){
			sorted.add(next);
			if(next.left == first){
				first =  next;
				next = next.right;
			}
			else{
				first =  next;
				next = next.left;
			}
		}
	}
	
	public String getSortedNodes(int dimension) {
		String out = "";
		
		for(int i = 0; i < dimension; i++) {
			out += (sorted.get(i).getId() + "\n");
		}
		
		return out;
	}
	
	public abstract Vector<Node> run(Node[] nodes, int dimension);
}
