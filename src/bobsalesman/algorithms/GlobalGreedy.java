package bobsalesman.algorithms;

import java.util.Vector;

public class GlobalGreedy extends ProactiveAlgorithm {

	public GlobalGreedy() {
		super(4);
	}

	@Override
	public Vector<Node> run(Node[] nodes, int dimension) {
		updateProgress(); // To indicate that the execution has started
		
		generatePairs(nodes);
		updateProgress();

		doMatches(dimension); // Part 3
		updateProgress();

		sort(nodes); // Part 4
		updateProgress();
		
		return sorted;
	}
	
	private void generatePairs(Node nodes[]){
		pairs = new Vector<Pair>();
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
