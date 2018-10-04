import java.util.ArrayList;

public class NodeList 
{
		private static NodeList instance = null;
		
	   	public ArrayList<Node> list = new ArrayList<Node>();
	   	
		private NodeList() 
	   	{ 
	         
	   	} 
	  
	    // static method to create instance of NodeList class 
	    public static NodeList getInstance() 
	    { 
	        if (instance == null) 
	        {
	            instance = new NodeList(); 
	        }
 	        return instance; 
	    }
	    
	    /*
	     *	Sets the list values 
	     */
	    public void setNodeList(ArrayList<Node> list)
	    {
	    	this.list = list;
	    }
	    
	    /*
	     * Returns the list
	     */
	    public ArrayList<Node> getNodeList()
	    {
	    	return this.list;
	    }
	    
	    /*
	     * Resets the values in the List
	     */
	    public void resetList()
	    {
	    	this.list = null;
	    }
	    
	    /*
	     * Adds a single node to the list
	     */
	    public void addToList(Node task)
	    {
	    	list.add(task);
	    }
	    
	    public ArrayList<Node> getHeads()
	    {
	    	ArrayList<Node> heads = new ArrayList<Node>();
	    	for(Node x: this.list)
	    	{
	    		if(x.headValue() == true)
	    		{
	    			heads.add(x);
	    		}
	    	}
	    	return heads;
	    }
}
