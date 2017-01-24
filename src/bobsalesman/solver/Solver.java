package bobsalesman.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class Solver {

	public final double TOTAL_PROGRESS = 5;
	public double progress;
	
	private void updateProgress(){
		progress++;
	}
	
	public double getProgress(){
		return progress/TOTAL_PROGRESS;
	}
	
	
	private String FILE_NAME = null;
	private String TYPE = null;
	private String COMMENT = null;
	private int DIMENSION = 0;
	private Node nodes[] = null;
	private double distance = 0;
	private Vector<Node> sorted = null;
	private Vector<Pair> pairs;
	
	private double distance(Node a, Node b){
		double distance = (a.x-b.x)*(a.x-b.x);
		distance += (a.y-b.y)*(a.y-b.y);
		distance = Math.sqrt(distance);
		return distance;
	}

	private void generatePairs(Node nodes[]){
		 pairs = new Vector<Pair>();
		for(int i = 0; i < nodes.length; i++){
			for(int j = i+1; j <nodes.length; j++){
				Pair pair = new Pair(nodes[i], nodes[j],distance(nodes[i],nodes[j]));

				pairs.add(pair);
			}
		}
		sort(pairs);

	}


	private void sort(Vector<Pair> pairs) {
		Pair temp;
		for(int i =0; i< pairs.size(); i++){

			for(int j =0; j< pairs.size()-1;j++){
				if(pairs.get(j).getDistance()>pairs.get(j+1).getDistance()){
					temp = pairs.get(j);
					pairs.remove(j);
					pairs.add(j+1,temp);
				}
			}
		}

	}
	private void doMatches() throws Exception{
		for(int i = 0, counter = 0; i < pairs.size() && counter < DIMENSION; i++){
			if(pairs.get(i).canMatch())
			{
				pairs.get(i).match(i+1);
				distance += pairs.get(i).distance;
				counter++;
			}
		}
	}

	public File solve(File map) throws Exception{
		
		//STATUS 0
		updateProgress();
		read(map);
		//STATUS 1
		updateProgress();
		generatePairs(nodes);
		//STATUS 2
		updateProgress();
		doMatches();
		//STATUS 3
		updateProgress();
		sort();
		//STATUS 4
		updateProgress();
		return printFile();
		
	}
	private void sort(){
		Node first = null;
	Node next;
	for(int i =0; i < nodes.length; i++){
		if(nodes[i].left== null || nodes[i].right == null){ first = nodes[i]; break;}
	}
		if(first.left == null) next = first.right;
		else next = first.left;
		sorted = new Vector<Node>();
		sorted.add(first);

		while(next != null){
			sorted.add(next);
			if(next.left == first){
				first =  next;
				next = next.right;
			}
			else{
				first =  next;
				next = next.left;
			}
		}
	}

	private  File printFile() throws FileNotFoundException {
		String[] split = FILE_NAME.split(".tsp");
		String filename = split[0];
		File file = new File(filename + ".output");
		PrintStream out = new PrintStream(new FileOutputStream(file));
		System.setOut(out);
		out.println("FILE: "+ filename);
		out.println("COMMENT: "+ COMMENT + " TOTAL_DISTANCE " + distance);
		out.println("TYPE: "+ TYPE);
		out.println("DIMENSION: "+ DIMENSION);
		out.println("TOUR_SECTION");
		for(int i = 0; i < DIMENSION; i++){
			out.println(sorted.get(i).id);
		}
		
		return file;
	}

	private void read(File file) throws FileNotFoundException {
		
		Scanner input = new Scanner(file);
		input.next();
		FILE_NAME = input.next();
		input.next();
		TYPE = input.next();
		input.next();
		COMMENT = input.nextLine();
		input.next();
		DIMENSION = input.nextInt();

		input.nextLine(); // trash
		
		input.nextLine();
		input.nextLine();
		nodes = new Node[DIMENSION];
		for(int i = 0; i < DIMENSION; i++){
			int id = input.nextInt();
			double x = Double.parseDouble(input.next());
			double y = Double.parseDouble(input.next());
			Node node = new Node(x,y,id);
			nodes[i] = node;
			input.nextLine();
		}

	}
}
