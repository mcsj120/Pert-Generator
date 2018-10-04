import java.util.ArrayList;

/*
 * This class holds the path data for an individual path. Acts like a structure in C++. 
 */
public class PathData 
{
	public ArrayList<Node> path;
	public int duration;
	
	public PathData(ArrayList<Node> path)
	{
		this.path = path;
	}
	
}
