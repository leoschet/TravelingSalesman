package bobsalesman.algorithms;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class Tester {

	public static void main(String[] args) {
		Solver globalGreedySolver = new Solver(ESolversAlgorithm.GLOBAL_GREEDY);
		Solver localGreedySolver = new Solver(ESolversAlgorithm.LOCAL_GREEDY);
		LocalVNSSolver globalGreedyVNSSolver = new LocalVNSSolver(ESolversAlgorithm.GLOBAL_GREEDY);
		LocalVNSSolver localGreedyVNSSolver = new LocalVNSSolver(ESolversAlgorithm.LOCAL_GREEDY);
//		Solver randomSolver = new Solver(ESolversAlgorithm.RANDOM);
		
		File f;
		JFileChooser chooser = new JFileChooser();

		int response = chooser.showOpenDialog(null);
		if(response != JFileChooser.APPROVE_OPTION)
			return;

		f = chooser.getSelectedFile();
		
//		File f = new File("C:\\Users\\Leonardo\\Desktop\\berlin52");
		
		String globalGreedyResult;
		String localGreedyResult;
		String globalGreedyVNSResult;
		String localGreedyVNSResult;
		String randomResult;
		String curAlgorithm = "";
		
		Timer timer;
		
		try {
			System.out.println("started");
			curAlgorithm = "local VNS";
			timer = new Timer();
			timer.start();
			localGreedyVNSResult = localGreedyVNSSolver.solve(f);
			timer.stop();
			System.out.println(curAlgorithm + ": " + localGreedyVNSSolver.getDistance() + " in " + timer.getFormattedTime());
			
			
			curAlgorithm = "global";
			timer = new Timer();
			timer.start();
			globalGreedyResult = globalGreedySolver.solve(f);
			timer.stop();
			System.out.println(curAlgorithm + ": " + globalGreedySolver.getDistance() + " in " + timer.getFormattedTime());
			
			System.out.println();
			
			curAlgorithm = "local";
			timer = new Timer();
			timer.start();
			localGreedyResult = localGreedySolver.solve(f);
			timer.stop();
			System.out.println(curAlgorithm + ": " + localGreedySolver.getDistance() + " in " + timer.getFormattedTime());
			
			System.out.println();
			
			curAlgorithm = "global VNS";
			timer = new Timer();
			timer.start();
			globalGreedyVNSResult = globalGreedyVNSSolver.solve(f);
			timer.stop();
			System.out.println(curAlgorithm + ": " + globalGreedyVNSSolver.getDistance() + " in " + timer.getFormattedTime());
			
			System.out.println();
			
			curAlgorithm = "local VNS";
			timer = new Timer();
			timer.start();
			localGreedyVNSResult = localGreedyVNSSolver.solve(f);
			timer.stop();
			System.out.println(curAlgorithm + ": " + localGreedyVNSSolver.getDistance() + " in " + timer.getFormattedTime());
			
//			curAlgorithm = "random";
//			randomResult = randomSolver.solve(f);
		} catch (FileNotFoundException e) {
			System.err.println("File not found.");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Null pointer on: " + curAlgorithm + ".");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished.");

	}

}
