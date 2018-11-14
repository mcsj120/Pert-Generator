import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class ReportCreator {
	private Organizer organizer;
	
	public ReportCreator(Organizer o) {
		organizer = o;
	}
	
	public void createReport(String fileName) throws IOException {
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		
		ArrayList<Node> nodeList = organizer.getList().getNodeList();
		//list of all activities alphabetized
		ArrayList[] activityArray = alphabetizeNodeNames(nodeList);
		ArrayList<String> activityNames = activityArray[0];
		ArrayList<Integer> activityDurations = activityArray[1];
		
		ArrayList<PathData> pathList = organizer.getPathList();
		ArrayList[] pathArray = orderedPathNames(pathList);
		ArrayList<String> pathNames = pathArray[0];
		ArrayList<Integer> pathDurations = pathArray[1]; 
		
		int maxActivityNameLength = maxStringLength(activityNames);
		int actColumnLen = Math.max(maxActivityNameLength, 4) + 1; // 5 = "Name"
		
		int maxPathNameLength = maxStringLength(pathNames);
		int pathColumnLen = Math.max(maxPathNameLength, 10) + 1; //10 = "Activities"
		
		
		//bufferedWriter
		FileOutputStream outputStream = new FileOutputStream(fileName + ".txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        
		writer.write("Title: " + fileName);
		writer.newLine();
		writer.write("Create On " + dtFormat.format(now));
		writer.newLine(); writer.newLine();
		writer.write("Activities:");
		writer.newLine();
		//prints header row
		writer.write("Name");
		for(int i = 0; i < actColumnLen - 4; i++) {
			writer.write(" ");
		}
		writer.write("| Duration");
		writer.newLine();
		//prints actual activities
		for(int j = 0; j < activityNames.size(); j++) {
			for(int i = 0; i < actColumnLen; i++) {
				writer.write("-");
			}
			writer.write("|");
			for(int i = 0; i < 9; i++) {
				writer.write("-");
			}
			writer.newLine();
			String str = activityNames.get(j);
			writer.write(str);
			for(int i = 0; i < actColumnLen - str.length(); i++) {
				writer.write(" ");
			}
			writer.write("| ");
			writer.write(activityDurations.get(j).toString());
			writer.newLine();
		}
		writer.newLine();
		writer.write("Paths: ");
		writer.newLine();
		//prints header row
		writer.write("Activities");
		for(int i = 0; i < pathColumnLen - 10; i++) {
			writer.write(" ");
		}
		writer.write("| Duration");
		writer.newLine();
		//prints actual paths
		for(int j = 0; j < pathNames.size(); j++) {
			for(int i = 0; i < pathColumnLen; i++) {
				writer.write("-");
			}
			writer.write("|");
			for(int i = 0; i < 9; i++) {
				writer.write("-");
			}
			writer.newLine();
			String str = pathNames.get(j);
			writer.write(str);
			for(int i = 0; i < pathColumnLen - str.length(); i++) {
				writer.write(" ");
			}
			writer.write("| ");
			writer.write(pathDurations.get(j).toString());
			writer.newLine();
		}
		
		writer.close();
		//List of all paths (with paths being lists of string)
		//ArrayList<ArrayList<String>> pathStrings = new ArrayList<ArrayList<String>>(organizer.getNames());
		//list of all nodes in network

		
	}
	
	
	//returns an alphabetized list of node names
	private ArrayList[] alphabetizeNodeNames(ArrayList<Node> nodeList){
		//list of alphabetized node names
		ArrayList<String> orderedNodeNames = new ArrayList<String>();
		ArrayList<Integer> orderedNodeDurations = new ArrayList<Integer>();
		//adds first node name to orderedNodeNames
		orderedNodeNames.add(nodeList.get(0).getName());
		orderedNodeDurations.add(nodeList.get(0).getDuration());
		//for each node in nodeList
		for(int i = 1; i < nodeList.size(); i++) {
			String nodeName = nodeList.get(i).getName();
			boolean inserted = false;
			for(int j = 0; j < orderedNodeNames.size() && !inserted; j++) {
				if(nodeName.compareTo(orderedNodeNames.get(j)) < 0) {
					orderedNodeNames.add(j, nodeName);
					orderedNodeDurations.add(j, nodeList.get(i).getDuration());
					inserted = true;
				}
			}
			if(!inserted) {
				orderedNodeNames.add(nodeName);
				orderedNodeDurations.add(nodeList.get(i).getDuration());
			}
		}
		ArrayList[] returnArray = {orderedNodeNames, orderedNodeDurations};
		return returnArray;
	}
	
	private int maxStringLength(ArrayList<String> nameList) {
		int max = nameList.get(0).length();
		for(int i = 1; i < nameList.size(); i++) {
			if(nameList.get(i).length() > max) {
				max = nameList.get(i).length();
			}
		}
		return max;
	}
	
	private ArrayList[] orderedPathNames(ArrayList<PathData> pathList) {
		ArrayList<String> pathNames = new ArrayList<String>();
		ArrayList<Integer> pathDurations = new ArrayList<Integer>();
		for(int i = 0; i < pathList.size(); i++) {
			ArrayList<Node> path =  pathList.get(i).getPath();
			//System.out.println(path.size());
			String str = "";
			for(int j = 0; j < path.size() - 1; j++) {
				str = str + path.get(j).getName() +", ";
				//System.out.println(path.get(j).getName());
				//System.out.println(str);
			}
			str += path.get(path.size() - 1).getName();
			pathNames.add(str);
			pathDurations.add(pathList.get(i).getDuration());
		}
		ArrayList[] returnArray = {pathNames, pathDurations};
		return returnArray;
	}
}
