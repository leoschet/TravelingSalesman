package bobsalesman.restservices;

import java.io.File;

import bobsalesman.solver.Solver;



public class SolverManager extends Thread{

	private volatile boolean resultRequested = false;
	public int id;
	private File file;
	Solver solver;
	
	public SolverManager(int id, File file) {
		this.id = id;
		this.file = file;
	}

	public void run(){
		SingleBobTon.getInstance().spot(this);

		solver = new Solver();
		try {
			file = solver.solve(file);
		} catch (Exception e) {}
		
		while(!resultRequested){
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
		try {
			sleep(10);
		} catch (InterruptedException e) {}

	}
	
	public File getResult(){
		resultRequested = true;
		return file;
	}

}
