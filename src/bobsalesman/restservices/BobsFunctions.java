package bobsalesman.restservices;

import bobsalesman.algorithms.ESolversAlgorithm;

public class BobsFunctions {

	public int requestBestRoute(String fileRawData, ESolversAlgorithm approach) {
		SingleBobTon singleton = SingleBobTon.getInstance();
		int id = singleton.getSolverId();
		SolverManager solver = new SolverManager(id, fileRawData, approach);
		solver.start();
		singleton.spot(solver);
		return id;
	}

	public double getProgress(int id){
		SingleBobTon singleton = SingleBobTon.getInstance();
		SolverManager solverManager = singleton.getSolver(id);
		return solverManager.solver.getProgress();

	}

	public String getBestRoute(int id){
		SingleBobTon singleton = SingleBobTon.getInstance();
		SolverManager solver = singleton.getSolver(id);
		String rawData = solver.getResult();
//		try {
////			rawData = storeInDataBase(result);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return rawData;
	}

}
