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
    private static int errorCode;
    
    public Organizer(NodeList list)
    {
        this.setList(list);
    }

    /*
     * This function should check to make sure that all Dependencies of a Nodes dependencies exists
     * and then set the dependencies for the node
     * 
     * for each node : get the strDependenices, then you can verify that a node exists for each dependency
     * (maybe with a boolean array), and then once you are sure that they all exists, double check my 
     * createDependencies function I just created and call that
     */
    public boolean checkDependencies() {
    	boolean valid = true;
    	ArrayList<Node> nodeList = this.list.getNodeList();
    	for(int i = 0; i < nodeList.size();i++) 
    	{
    		Node n = nodeList.get(i);
    		/*
    		 * if node is not a startNode
    		 */
    		if(!n.headValue()) 
    		{
    			/*
    			 * Iterates through all dependencies of Node
    			 */
    			for(int j = 0; j < n.getDependencies().size();j++) 
    			{
    				Node n2 = n.getDependencies().get(j);
    				// if a dependency is not in the nodeList return error
    				if(!nodeList.contains(n2)) 
    				{
    					valid = false;
    					errorCode = 0;
    		
    				}
    			}
    		}
    	}
    	return valid;
    }
    
    /*
     * This function might actually be unnecessary. Instead of this function, we can just create a checkAncestors
     * that does the opposite, which will basically verify that everything is connected.
     */
    public void allNodesConnectedCheck() {
    	ArrayList<Node> nodeList = this.list.getNodeList();
    	//cycles through all nodes in nodeList
    	for(int i = 0; i < nodeList.size(); i++) {
    		Node n = nodeList.get(i);
    		//if node is not a startNode and nodeList does not contain
    		// the node is unconnected
    		if(!n.headValue() || n.getDependencies().size() == 0) {
    			System.out.println("Node " + n.toString() + " is not connected to other nodes.");
    		}
    	}
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
			getPaths(head, individualPaths);
			paths.addAll(individualPaths);
		}
		this.pathList = paths;
	}
	
	private void getPaths(Node head, ArrayList<PathData> paths)
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
			PathData data = paths.remove(index);
			//paths.remove(index);
			for(int i = 0; i < ancestors.size();i++)
			{	
				ArrayList<Node> empty = new ArrayList<Node>();
				PathData dependPath = new PathData(empty);
				for(Node x: data.path)
				{
					dependPath.path.add(x);
				}
				dependPath.path.add(ancestors.get(i));
				paths.add(dependPath);
				getPaths(ancestors.get(i), paths);
			}
		}
		else
		{
			index++;
		}
	}
	
	private void createDependencies(ArrayList<String> arr, Node n)
	{
		ArrayList<Node> depend = new ArrayList<Node>();
		for(String node: arr)
		{
			for(Node x: this.list.list)
			{
				if(arr.equals(x.getName()))
				{
					depend.add(x);
				}
			}
		}
		n.setDependencies(depend);
	}
}