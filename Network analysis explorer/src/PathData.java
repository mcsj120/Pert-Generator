import java.util.ArrayList;

/*
 * This class holds the path data for an individual path. Acts like a structure in C++. 
 */
public class PathData 
{
	public ArrayList<Node> path;
	public int duration;
	
	/*
	 * Remove Exception in final build. Exception is for error checking
	 */
	public PathData(ArrayList<Node> path, int duration) throws Exception
	{
		this.path = path;
		this.duration = duration;
		boolean test = this.errorCheck();
		if(!test)
		{
			throw new Exception("BadInputs");
		}
	}
	
	/*
	 * This function tests to make sure that the duration placed in pathData matches the actual duration
	 */
	private boolean errorCheck()
	{
		int tempDuration = 0;
		for(int i = 0; i < path.size();i++)
		{
			tempDuration += path.get(i).getDuration();
		}
		if(tempDuration == duration)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
