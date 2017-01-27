package bobsalesman.algorithms;

import java.util.Vector;
import java.lang.Math;

public abstract class ProactiveAlgorithm {
	
	private int totalPhases;
	private double progress;
	private double totalDistance;
	
	protected Vector<Node> sorted;
	
	public ProactiveAlgorithm(int specificPhases) {
		this.sorted = new Vector<Node>();
		this.totalPhases = 2 + specificPhases;
		this.progress = 0;
		this.totalDistance = 0;
	}
	
	protected static double distance(Node a, Node b){
		double distance = (a.getX() - b.getX()) * (a.getX() - b.getX());
		distance += (a.getY() - b.getY()) * (a.getY() - b.getY());
		distance = Math.sqrt(distance);
		return distance;
	}
	
	protected void updateProgress() {
		progress += Math.ceil(1/totalPhases);
	}
	
	private void terminateExecution() {
		progress = 1;
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
	
	public double getUpdatedDistance() {
		double totalDist = 0;
		
		for(int i =0; i< sorted.size()-1; i++){
			totalDist+= distance(sorted.get(i), sorted.get(i+1));
		}
		totalDist+= distance(sorted.get(0), sorted.get(sorted.size()));
		return totalDist;
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
	
	public Vector<Node> run(Node[] nodes, int dimension) {
		updateProgress(); // To indicate that the execution has started
		
		runSpecific(nodes, dimension);
		
		sort(nodes);
		terminateExecution();
		return sorted;
	}
	
	protected abstract void runSpecific(Node[] nodes, int dimension);
}
