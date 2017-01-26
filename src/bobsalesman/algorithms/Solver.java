package bobsalesman.algorithms;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solver {

	private String FILE_NAME = null;
	private String TYPE = null;
	private String COMMENT = null;
	private int DIMENSION = 0;
	private Node nodes[] = null;
	
	private ProactiveAlgorithm algorithm;

	public Solver(ESolversAlgorithm approach) {
		switch (approach) {

		case GLOBAL_GREEDY:
			algorithm = new GlobalGreedy();
			break;
		case RANDOM:
			algorithm = new RandomSalesman();
			break;
		default:
			algorithm = new RandomSalesman(); // Random approach is the default.
			break;
		}
	}
	
	public double getProgress(){
		return algorithm.getProgress();
	}

	public String solve(String map) throws Exception{

		read(map);
		
		algorithm.run(nodes, DIMENSION);

		return printString();
	}

	private  String printString() {
		String out = "FILE: "+ FILE_NAME+
				"\nCOMMENT: "+ COMMENT + " TOTAL_DISTANCE " + algorithm.getTotalDistance() +
				"\nTYPE: "+ TYPE+
				"\nDIMENSION: "+ DIMENSION+
				"\nTOUR_SECTION\n";
		
		out += algorithm.getSortedNodes(DIMENSION);

		return out;
	}

	private void read(String file) throws FileNotFoundException {

		Scanner input = new Scanner(file);
		input.next();
		FILE_NAME = input.next();
		input.next();
		TYPE = input.next();
		input.next();
		COMMENT = input.nextLine();
		input.next();
		DIMENSION = input.nextInt();

		input.nextLine(); // trash

		input.nextLine();
		input.nextLine();
		nodes = new Node[DIMENSION];
		for(int i = 0; i < DIMENSION; i++){
			int id = input.nextInt();
			double x = Double.parseDouble(input.next());
			double y = Double.parseDouble(input.next());
			Node node = new Node(x,y,id);
			nodes[i] = node;
			input.nextLine();
		}
		
		input.close();
	}
}
