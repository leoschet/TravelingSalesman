package bobsalesman.algorithms;

import java.util.Vector;

public class GlobalGreedy extends ProactiveAlgorithm {

	private Vector<Pair> pairs;
	
	public GlobalGreedy() {
		super(2);
		pairs = new Vector<Pair>();
	}

	@Override
	protected void runSpecific(Node[] nodes, int dimension) {
		
		generatePairs(nodes);
		updateProgress();

		doMatches(dimension); // Part 3
		updateProgress();
	}
	
	private void generatePairs(Node nodes[]){

		for(int i = 0; i < nodes.length; i++){
			for(int j = i+1; j <nodes.length; j++){
				Pair pair = new Pair(nodes[i], nodes[j], distance(nodes[i],nodes[j]));

				pairs.add(pair);
			}
		}
		sort(pairs);
	}

	private void doMatches(int dimension) {
		for(int i = 0, counter = 0; i < pairs.size() && counter < dimension; i++){
			if(pairs.get(i).canMatch())
			{
				pairs.get(i).match(i+1);
				increaseTotalDistance(pairs.get(i).getDistance());
				counter++;
			}
		}
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
