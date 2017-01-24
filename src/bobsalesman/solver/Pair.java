package bobsalesman.solver;

public class Pair{
	Node left;
	Node right;
	double distance;

	public Pair(Node left, Node right, double distance)
	{
		this.left = left;
		this.right = right;
		this.distance = distance;
	}

	public Pair()
	{
		this.left = null;
		this.right = null;
		this.distance = 0;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean canMatch() {
		if((left.left == null || left.right == null) && 
				(right.left == null || right.right == null) && 
				(left.team == 0 || right.team == 0 || left.team != right.team)) return true;
		return false;
	}

	public void match(int team) throws Exception {
		if(left.team == 0 && right.team == 0){
			setTeam(left, team);
			setTeam(right, team);
		}
		else if(left.team == 0) 
			setTeam(left, right.team);

		else 
			setTeam(right, left.team);		

		if(left.left == null) left.left = right;
		else left.right = right;
		if(right.left == null) right.left = left;
		else right.right = left;

		System.out.println("match " + left.id + " - " + right.id+" team>"+left.team);
	}

	private void setTeam(Node node, int team) {
		node.team = team;
		if(node.left!= null && node.left.team != team)
			setTeam(node.left, team);
		if(node.right != null && node.right.team != team)
			setTeam(node.right, team);

	}

}