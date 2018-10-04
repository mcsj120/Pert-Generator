import java.util.ArrayList;

/*This class represents a node
 * Written by Jacob Baca
 * edited by Michael St Onge
 * Last edited by John Shaeffer
 *  */

public class Node {
	private String activityName;
	private int duration;
	private ArrayList<Node> dependencies;
	private ArrayList<Node> ancestors;
	private ArrayList<String> strDependencies; //necessary for checking that dependency exists
	private boolean head;
	
	public Node(String activityName, int duration)
	{
		this.activityName = activityName;
		this.duration = duration;
		this.head = true;
	}
	
	public Node(String activityName, int duration, ArrayList<String> strDependencies)
	{
		this.activityName = activityName;
		this.duration = duration;
		this.strDependencies = strDependencies;
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
