package bobsalesman.restservices;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BobsFunctions {

	public int requestBestRoute(File file) {
		SingleBobTon singleton = SingleBobTon.getInstance();
		int id = singleton.getSolverId();
		SolverManager solver = new SolverManager(id, file);
		solver.start();
		singleton.spot(solver);
		return id;
	}
	
	public int requestBestRoute(String file) {
		SingleBobTon singleton = SingleBobTon.getInstance();
		int id = singleton.getSolverId();
		SolverManager solver = new SolverManager(id, file);
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

	private String storeInDataBase(File result) throws FileNotFoundException {

		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(result));
		byte[] buffer = new byte[(int) result.length()];
		
		try {
			bin.read(buffer);
			bin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileStr = new String(buffer);
		
		return fileStr;
	}

}
