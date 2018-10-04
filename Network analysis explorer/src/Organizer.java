import java.util.ArrayList;

// Organizer Class
// written by John Shaeffer

//the class that holds all of the logic 
public class Organizer
{
    private ArrayList<PathData> pathList; 
    private NodeList list;
    private boolean valid;
    private static int index;
    
    public Organizer(NodeList list)
    {
        this.setList(list);
    }

    public void errorDependencyCheck()
    {
        //checking to make sure the strings they said are depency match to what they said
    }
    
    public void errorCircular()
    {
    	//checks to make sure no circular paths exist
    }

    public void errorDuplicateCheck(NodeList list)
    {
    	/*
    	 * If the function is already valid, we don't need to test it
    	 */
        if(valid == true)
        {
        	boolean exists = false;
	        ArrayList<String> names = new ArrayList<String>();
	        for(int i = 0; i < list.list.size(); i ++)
	        {
	            for(int j = 0; j< names.size(); j++ )
	            {
	                if(list.list.get(i).getName().equals(names.get(i)))
	                {
	                    exists = true;
	                    break;
	                }
	            }
	            if(!exists)
	            {
	                names.add(list.list.get(i).getName());
	            }
	            else
	            {
	                break;
	            }
	        }
        }
    }
    
    public void findDependencies()
    {
    	//This function adds all descendants to a Node, including the dependencies of their dependencies
    }
    
    public void findAncestors()
    {
    	//This function adds all ancestors to a Node, including the ancestors of their ancestors
    }

    /*
     * Getters and Setters
     */
	public boolean isValid() 
	{
		return valid;
	}

	public void setValid(boolean valid) 
	{
		this.valid = valid;
	}

	public ArrayList<PathData> getPathList() 
	{
		return pathList;
	}

	public void setPathList(ArrayList<PathData> pathList) 
	{
		this.pathList = pathList;
	}

	public NodeList getList() 
	{
		return list;
	}

	public void setList(NodeList list) 
	{
		this.list = list;
	}

	public void recursiveStartPath()
	{
		ArrayList<Node> heads = list.getHeads();
		ArrayList<PathData> paths = new ArrayList<PathData>();
		for(Node head : heads)
		{
			ArrayList<PathData> individualPaths = new ArrayList<PathData>();
			index = -1;
			paths.addAll(getPaths(head, individualPaths));
		}
		this.pathList = paths;
	}
	
	private ArrayList<PathData> getPaths(Node head, ArrayList<PathData> paths)
	{
		if(index == -1)
		{
			ArrayList<Node> headList = new ArrayList<Node>();
			headList.add(head);
			PathData headPath = new PathData(headList);
			paths.add(headPath);
			index++;
		}
		ArrayList<Node> ancestors = head.getAncestors();
		if(ancestors.size()>0)
		{
			PathData data = paths.get(index);
			paths.remove(index);
			for(int i = 0; i < ancestors.size();i++)
			{	
				PathData dependPath = new PathData(null);
				for(Node x: data.path)
				{
					dependPath.path.add(x);
				}
				dependPath.path.add(ancestors.get(index+i));
				paths.addAll(getPaths(ancestors.get(index+i), paths));
			}
		}
		index++;
		return paths;
	}
}