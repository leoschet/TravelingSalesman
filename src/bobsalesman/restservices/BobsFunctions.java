package bobsalesman.restservices;

import java.io.File;

public class BobsFunctions {

	public void requestBestRoute(File file) {
		SingleBobTon singleton = SingleBobTon.getInstance();
		SolverManager solver = new SolverManager(singleton.getSolverId(),file);
		solver.start();
	}
	
	public double getProgress(int id){
		SingleBobTon singleton = SingleBobTon.getInstance();
		SolverManager solverManager = singleton.getSolver(id);
		return solverManager.solver.getProgress();
		
	}
	
	public String getBestRoute(int id){
		SingleBobTon singleton = SingleBobTon.getInstance();
		SolverManager solver = singleton.getSolver(id);
		File result = solver.getResult();
		String url = storeInDataBase(result);
		return url;
	}

	private String storeInDataBase(File result) {
		// TODO Auto-generated method stub
		return null;
	}

}
