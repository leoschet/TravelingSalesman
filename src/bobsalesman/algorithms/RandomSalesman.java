package bobsalesman.algorithms;

import java.util.Vector;
import java.util.Random;

public class RandomSalesman extends ProactiveAlgorithm {

	private Random rand;
	
	public RandomSalesman() {
		super(3);
		rand = new Random();
	}

	@Override
	public Vector<Node> run(Node[] nodes, int dimension) {
		updateProgress(); // To indicate that the execution has started
		
		randomizeConnections(nodes);
		updateProgress();
		
		sort(nodes);
		updateProgress();
		return null;
	}

	private void randomizeConnections(Node[] nodes) {
		
		int len = nodes.length;
		
		boolean[] usedNodes = new boolean[len];
		
		int index = rand.nextInt(len);
		usedNodes[index] = true;
		
		int connectionIndex;
		for (int counter = 0; counter < len;) {
			connectionIndex = rand.nextInt(len);
			
			if (!usedNodes[connectionIndex]) {
				nodes[index].right = nodes[connectionIndex];
				nodes[connectionIndex].left = nodes[index];
				
				usedNodes[connectionIndex] = true;
				index = connectionIndex;
			}
		}
	}

}
