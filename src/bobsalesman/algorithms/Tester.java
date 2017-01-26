package bobsalesman.algorithms;

import java.io.File;
import java.io.FileNotFoundException;

public class Tester {

	public static void main(String[] args) {
		Solver globalGreedySolver = new Solver(ESolversAlgorithm.GLOBAL_GREEDY);
		Solver localGreedySolver = new Solver(ESolversAlgorithm.LOCAL_GREEDY);
		Solver randomSolver = new Solver(ESolversAlgorithm.RANDOM);
		
		File f = new File("C:\\Users\\Leonardo\\Desktop\\berlin52");
		
		String globalGreedyResult;
		String localGreedyResult;
		String randomResult;
		String curAlgorithm = "";
		try {
			curAlgorithm = "global";
			globalGreedyResult = globalGreedySolver.solve(f);
			
			curAlgorithm = "local";
			localGreedyResult = localGreedySolver.solve(f);
			
			curAlgorithm = "random";
			randomResult = randomSolver.solve(f);
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
		} catch (NullPointerException e) {
			System.err.println("Null pointer on: " + curAlgorithm + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
