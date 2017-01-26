package bobsalesman.algorithms;
public class Node{
	double x;
	double y;
	int team;
	Node left;
	Node right;
	private int id;

	public Node(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.team = 0;
		this.left = null;
		this.right = null;

	}
	public Node(double x, double y,int id)
	{
		this.x = x;
		this.y = y;
		this.team = 0;
		this.left = null;
		this.right = null;
		this.setId(id);

	}

	public Node()
	{
		this.x = 0;
		this.y = 0;
		this.team = 0;
		this.left = null;
		this.right = null;

	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
