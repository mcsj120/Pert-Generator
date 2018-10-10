import java.util.ArrayList;

// Organizer Class

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
        errorCode = -1;
        valid = true;
    }
    
    /*
     * Public void Functions
     */
    
    /**
     * Checks all error scenarios and quits if one is found
     */
    public void checkAll()
    {
    	int functionIndex = 0;
    	while(errorCode == -1)
    	{
    		checkHelper(functionIndex);
    		functionIndex++;
    	}
    }
    
    /**
     * Prepares Numbers in pathList
     */
	public void preparePathList() 
	{
		sumPaths();
		insertionSort();
	}
	
	/**
	 * Public function to find paths
	 */
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
	
	/*
	 * Public non void functions
	 */
	
	/**
	 * Returns names of activities in paths
	 */
	public ArrayList<ArrayList<String>> getNames()
	{
		ArrayList<ArrayList<String>> fullNames = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < this.pathList.size(); i++)
		{
			ArrayList<String> arr = new ArrayList<String>();
			for(int j = 0; j < this.pathList.get(i).path.size(); j++)
			{
				arr.add(this.pathList.get(i).path.get(j).getName());
			}
			fullNames.add(arr);
		}
		return fullNames;
	}
	
	/**
	 * Returns duration of paths
	 */
	public ArrayList<Integer> getDurations()
	{
		ArrayList<Integer> durations = new ArrayList<Integer>();
		for(int i = 0; i < this.pathList.size(); i++)
		{
			durations.add(pathList.get(i).duration);
		}
		return durations;
	}

    
  
	/*
	 * Error Checks
	 */
	
	
    /*
     * This function should check to make sure that all Dependencies of a Nodes dependencies exists
     * and then set the dependencies for the node
     * 
     * for each node : get the strDependenices, then you can verify that a node exists for each dependency
     * (maybe with a boolean array), and then once you are sure that they all exists, double check my 
     * createDependencies function I just created and call that
     */
	//This function test to make sure that all dependency names exist in the nodeList exist
    public void checkDependencies() {
    	ArrayList<Node> nodeList = this.list.getNodeList();
    	//go through all nodes in the node list
    	for(int i = 0; i < nodeList.size();i++) {
    		Node n = nodeList.get(i);
    		if(!n.headValue())
    		{
    			//cycle through dependencies of n
	    		for(int j = 0; j < n.getStrDependencies().size();j++) 
	    		{
	    			boolean nameInNodeList = false;
	    			String depName = n.getStrDependencies().get(j);
	    			//cycle through all nodes in nodeList searching for depName
	    			for(int k = 0; k < nodeList.size();k++) {
	    				if(nodeList.get(k).getName().equals(depName)) {
	    					nameInNodeList = true;
	    				}
	    			}
	    			if(!nameInNodeList) {
	    				//depName not in nodeList
	    				errorCode = 2;
	    				this.valid = false; 
	    				break;
	    			}
	    		}
	    		
	    		if(valid)
	    		{
	    			ArrayList<String> dep = n.getStrDependencies();
	    			createDependencies(dep, n);
	    		}
    		}
    		if(!this.valid)
    		{
    			break;
    		}
    		
    	}
    }    

    public void checkAllNodesConnected() 
    {
    	ArrayList<Node> nodeList = this.list.getNodeList();
    	//cycles through all nodes in nodeList
    	for(int i = 0; i < nodeList.size(); i++) 
    	{
    		Node n = nodeList.get(i);
    		//if node is not a startNode and nodeList does not contain
    		// the node is unconnected
    		if(!n.headValue())
    		{
    			if(n.getDependencies() == null)
    			{
        			this.valid = false;
        			errorCode = 3;
    			}
    		}

    	}
    }
    
    /**
     * Checks to make sure no circular paths exist
     */
    public void checkForCycle() {
    	ArrayList<Node> nodeList = this.list.getNodeList();
    	//create a tail node
    	//create copy of nodeList
    	ArrayList<Node> tempList = new ArrayList<Node>();
    	for(int i = 0; i < nodeList.size(); i++) {
    		tempList.add(nodeList.get(i));
    	}
    	//find node that are not dependencies of other node
    	for(int i = 0; i < nodeList.size(); i++ ) {
    		Node n = nodeList.get(i);
    		for(int j = 0; j < n.getDependencies().size(); j++) {
    			Node d = n.getDependencies().get(j);
    			if(tempList.contains(d)) {
    				tempList.remove(d);
    			}
    		}
    	}
    	if(tempList.isEmpty()) {
    		errorCode = 4;
			valid = false;
    	}
    	else {
    		//set tail to tempList
        	Node tail = new Node();
        	tail.setDependencies(tempList);
        	//call tracePath on tail node
        	tracePath(tail, new ArrayList<Node>());
    	}
    	
    	
    }
    //will set the error code and valid fields
    private boolean tracePath(Node node, ArrayList<Node> tracedPath){
    	if(tracedPath.contains(node)) {
    		errorCode = 4;
			valid = false;
			return false;
    	}
    	tracedPath.add(node);
    	if(node.headValue()) {
    		return true;
    	}
    	for(int i = 0; i < node.getDependencies().size(); i++) {
    		Node d = node.getDependencies().get(i);
    		ArrayList<Node> clonedArrayList = (ArrayList<Node>) tracedPath.clone();
    		tracePath(d, clonedArrayList);
    	}
    	return true;
    }


    /**
     * Checks for duplicates
     */
    public void errorDuplicateCheck()
    {
    	/*
    	 * If the function is already valid, we don't need to test it
    	 */
        if(valid == true)
        {
        	boolean exists = false;
	        /*ArrayList<String> names = new ArrayList<String>();
	        for(int i=0; i < list.list.size(); i++)
	        {
	        	names.add(list.list.get(i).getName());
	        }
	        */
	        for(int i = 0; i < list.list.size(); i ++)
	        {
	            for(int j = i+1; j < list.list.size(); j++ )
	            {
	                if(list.list.get(i).getName().equals(list.list.get(j).getName()))
	                {
	                    valid = false;
	                    errorCode = 0;
	                    break;
	                }
	            }
	            /*
	            if(!exists)
	            {
	                names.add(list.list.get(i).getName());
	            }
	            else
	            {
	            	break;
	            }*/
	        }
        }
    }
    



	
	/*
	 * Private functions
	 */
	
    /**
     * Magic
     */
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
	
	/**
	 * Iterates through errorCodes
	 * @param functionIndex
	 */
	private void checkHelper(int functionIndex)
    {
    	switch(functionIndex)
    	{
	    	case(0):
	    		errorDuplicateCheck();
	    		break;
	    	case(1):
	    		checkDependencies();
	    		break;
	    	case(2):
	    		checkForCycle();
	    		break;
	    	case(3):
	    		checkAllNodesConnected();
	    		break;
	    	default:
	    		errorCode = -2;
	    		
    	
    	}
    }
	
	/**
	 * Sets dependencies for Node given string list and Node
	 */
	private void createDependencies(ArrayList<String> arr, Node n) //dependencies precede the activity
	{
		ArrayList<Node> depend = new ArrayList<Node>();
		for(String node: arr)
		{
			for(Node x: this.list.list)
			{
				if(node.equals(x.getName()))
				{
					depend.add(x);
					x.addAncestor(n);
				}
			}
		}
		n.setDependencies(depend);
	}
	
	/**
	 * Sets ancestors for Node given string list and Node
	 */
	private void createAncestors(ArrayList<String> arr, Node n) //ancestors come after activity (could be called children)
	{
		ArrayList<Node> depend = new ArrayList<Node>();
		for(String node: arr)
		{
			for(Node x: this.list.list)
			{
				if(node.equals(x.getName()))
				{
					depend.add(x);
				}
			}
		}
		n.setAncestors(depend);
	}
	
	/**
	 * Sums each path in pathList
	 */
	private void sumPaths()
	{
		for(PathData x: this.pathList)
		{
			x.setDuration();
		}
	}
	
	/**
	 * Insertion sort to organize durations
	 */
	private void insertionSort() 
	{  
	   for (int i = 1; i < this.pathList.size(); i++) 
	   { 
	       int key = pathList.get(i).duration; 
	       int j = i-1; 
	  
	       while (j >= 0 && pathList.get(j).duration > key) 
	       { 
	           pathList.get(j+1).duration = pathList.get(j).duration; 
	           j = j-1; 
	       } 
	       pathList.get(j+1).duration = key; 
	   } 
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
	
	public static int getErrorCode()
	{
		return errorCode;
	}
}