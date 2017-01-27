package bobsalesman.algorithms;

import java.util.Random;
import java.util.Vector;

public class LocalGreedy extends ProactiveAlgorithm {

	private Random rand;
	
	public LocalGreedy() {
		super(1);
		rand = new Random();
	}

	@Override
	protected void runSpecific(Node[] nodes, int dimension) {
		matchNodes(nodes, dimension);
		updateProgress();
	}

	private void matchNodes(Node[] nodes, int dimension) {
		
		boolean[] usedNodes = new boolean[dimension];
		boolean matched = false;
		int index = rand.nextInt(dimension);
		usedNodes[index] = true;
		
		Vector<Pair> curPairs;
		for(int i = 0; i < dimension; i++) {
			curPairs = getNodePairs(nodes, index, dimension);
			
			matched = false;
			while(!matched && curPairs.size() != 0) {
				Pair pair = curPairs.remove(0);
				int candidateIndex = pair.getRight().getId() - 1;
				
				if ( !usedNodes[candidateIndex] ) {
					nodes[index].right = nodes[candidateIndex];
					nodes[candidateIndex].left = nodes[index];
					
					increaseTotalDistance(distance(nodes[index], nodes[candidateIndex]));
					
					usedNodes[candidateIndex] = true;
					matched = true;
					index = candidateIndex;
				}
			}
		}
		
	}
	
	private Vector<Pair> getNodePairs(Node[] nodes, int nodeIndex, int dimension){
		Vector<Pair> pairs = new Vector<Pair>();
		
		for(int i = 0; i < dimension; i++) {
			if (i != nodeIndex) {
				Pair pair = new Pair(nodes[nodeIndex], nodes[i], distance(nodes[nodeIndex], nodes[i]));
				pairs.add(pair);
			}
		}
		
		sort(pairs);
		return pairs;
	}
	
	private void sort(Vector<Pair> pairs) {
		Pair temp;
		for(int i =0; i< pairs.size(); i++){

			for(int j =0; j< pairs.size()-1;j++){
				if(pairs.get(j).getDistance()>pairs.get(j+1).getDistance()){
					temp = pairs.get(j);
					pairs.remove(j);
					pairs.add(j+1,temp);
				}
			}
		}
	}
}
