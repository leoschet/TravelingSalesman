package bobsalesman.restservices;

import bobsalesman.algorithms.ESolversAlgorithm;
import bobsalesman.algorithms.Solver;

public class SolverManager extends Thread{

	private volatile boolean resultRequested = false;
	public int id;
	private String fileRawData;
	Solver solver;
	private ESolversAlgorithm approach;
	
	public SolverManager(int id, String fileRawData, ESolversAlgorithm approach) {
		this.id = id;
		this.fileRawData = fileRawData;
		this.approach = approach;
	}

	public void run(){

		solver = new Solver(this.approach);
		try {
			fileRawData = solver.solve(fileRawData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(!resultRequested){
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
		SingleBobTon.getInstance().removeSolver(this);
		
	}
	
	public String getResult(){
		resultRequested = true;
		return fileRawData;
	}

}
