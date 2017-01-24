package bobsalesman.restservices;

import java.util.Vector;

public class SingleBobTon {

	static SingleBobTon instance;
	private int id;
	Vector<SolverManager> solvers;
	private Object monitor;
	
	public static SingleBobTon getInstance(){
		if(instance == null){
			instance = new SingleBobTon();
		}
		return instance;
	}
	
	private SingleBobTon(){
		solvers = new Vector<SolverManager>();
		this.id = getIdFromDataBase();
		this.monitor = new Object();
	}
	

	private int getIdFromDataBase() {
		
		return 0;
	}
	
	public synchronized int getSolverId() {
		return id++;
	}
	
	public SolverManager getSolver(int id){
		for(int i = 0; i < solvers.size(); i++){
			if(solvers.get(i).id == id){
				return solvers.get(i);
			}
		}
		return null;
	}
	
	public void removeSolver(SolverManager solverManager){
		synchronized (solverManager) {
			for(int i = 0; i < solvers.size(); i++){
				if(solvers.get(i) == solverManager){
					solvers.remove(i);
					break;
				}
			}
		}
	}
	
	public void spot(SolverManager solverManager) {
		synchronized (monitor) {
			solvers.add(solverManager);
		}
	}
	
}
