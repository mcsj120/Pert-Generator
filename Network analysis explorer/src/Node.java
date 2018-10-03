import java.util.ArrayList;

/*This class represents a node
 * Written by Jacob Baca
 * Last edited by Michael St Onge
 *  */

public class Node {
	private String activityName;
	private int duration;
	private ArrayList<Node> dependencies;
	private ArrayList<Node> ancestors;
	private boolean head;
	
	public Node(String activityName, int duration)
	{
		this.activityName = activityName;
		this.duration = duration;
		this.head = true;
	}
	
	public Node(String activityName, int duration, ArrayList<Node> dependencies)
	{
		this.activityName = activityName;
		this.duration = duration;
		this.dependencies = dependencies;
		this.head = false;
	}
	
	public String getName()
	{
		return this.activityName;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public ArrayList<Node> getDependencies()
	{
		return this.dependencies;
	}
	
	public ArrayList<Node> getAncestors()
	{
		return this.ancestors;
	}
	
	public void setDependencies(ArrayList<Node> dependencies)
	{
		this.dependencies = dependencies;
	}
	public void setAncestors(ArrayList<Node> ancestors)
	{
		this.ancestors = ancestors;
	}
}
