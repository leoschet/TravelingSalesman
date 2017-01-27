package bobsalesman.algorithms;

import java.util.Random;

public class RandomSalesman extends ProactiveAlgorithm {

	private Random rand;
	private boolean[] usedNodes;

	public RandomSalesman() {
		super(1);
		rand = new Random();
	}

	@Override
	protected void runSpecific(Node[] nodes, int dimension) {
		randomizeConnections(nodes, dimension);
		updateProgress();
	}

	private void randomizeConnections(Node[] nodes, int dimension) {

		usedNodes = new boolean[dimension];

		int index = rand.nextInt(dimension);
		usedNodes[index] = true;

		int connectionIndex;
		for (int counter = 0; counter < dimension; counter++) {
			connectionIndex = getNextFreeRandomIndex(dimension-counter);

			if (!usedNodes[connectionIndex]) {
				nodes[index].right = nodes[connectionIndex];
				nodes[connectionIndex].left = nodes[index];

				increaseTotalDistance(distance(nodes[index], nodes[connectionIndex]));

				usedNodes[connectionIndex] = true;
				index = connectionIndex;
			}
		}
	}

	private int getNextFreeRandomIndex(int availableDimension) {
		int baseIndex = rand.nextInt(availableDimension);
		
		int index = -1;
		while (baseIndex > 0) {
			index += 1;
			if (!usedNodes[index]) {
				baseIndex -= 1;
			}
		}
		
		return index;
	}

}
