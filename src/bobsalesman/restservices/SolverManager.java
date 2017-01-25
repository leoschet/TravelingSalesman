package bobsalesman.restservices;

import java.io.File;

import bobsalesman.solver.Solver;



public class SolverManager extends Thread{

	private volatile boolean resultRequested = false;
	public int id;
	private File file;
	private String filestr;
	Solver solver;
	
	public SolverManager(int id, File file) {
		this.id = id;
		this.file = file;
	}

	public SolverManager(int id, String file) {
		this.id = id;
		this.filestr = file;
	}

	public void run(){

		solver = new Solver();
		try {
			filestr = solver.solve(filestr);
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
		return filestr;
	}

}
