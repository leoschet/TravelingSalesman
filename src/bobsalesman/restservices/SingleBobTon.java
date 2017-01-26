package bobsalesman.restservices;

import java.util.Vector;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class SingleBobTon {

	static SingleBobTon instance;
	private int id;
	Vector<SolverManager> solvers;
	private Object monitor;
	AmazonS3 s3client;
	AWSCredentials credentials;
	String bucketName = "us-west-2";
	
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
		credentials = new BasicAWSCredentials("AKIAJFRF2LVWEVD2WDXQ", "q40kbuz9ivLrEWEfJexi8Ng/c2/bb9RVusE2Ew3q");
		s3client = new AmazonS3Client(credentials);
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
