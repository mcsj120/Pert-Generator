import java.util.ArrayList;

// Organizer Class
// written by John Shaeffer

//the class that holds all of the logic 
public class Organizer
{
    public ArrayList<PathData> pathList; 
    public NodeList list;

    public static void errorDependencyCheck()
    {
        //checking to make sure the strings they said are depency match to what they said
    }

    public static boolean errorDuplicateCheck(NodeList list)
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
        return exists;
    }

    public Organizer(NodeList list)
    {
        this.list = list;
    }

}