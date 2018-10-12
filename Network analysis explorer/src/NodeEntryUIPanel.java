/*This class creates a JPanel with the main part of the UI
 * Written by Matthew Bohr
 *  */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;

public class NodeEntryUIPanel extends JPanel {
	
	private JButton addNode,analyze,about,help,restart,exit;
	private JTextField nodeName,nodeDuration,nodeDependencies;
	private JCheckBox isStartingNode;
	private JLabel nameLabel,durationLabel,dependenciesLabel;
	public JPanel entryPanel, entryPanel2,buttonPanel;
	
	//Constructor
	public NodeEntryUIPanel() {
		//Declare some things
		setLayout(new GridLayout(0,1));
		entryPanel= new JPanel();
		entryPanel2=new JPanel();
		buttonPanel=new JPanel();
		addNode=new JButton();
		analyze=new JButton();
		about=new JButton();
		help=new JButton();
		restart=new JButton();
		exit=new JButton();
		isStartingNode=new JCheckBox();
		nodeName=new JTextField();
		nodeDuration=new JTextField();
		nodeDependencies=new JTextField();
		nameLabel=new JLabel();
		durationLabel=new JLabel();
		dependenciesLabel=new JLabel();
		//Add them to layouts
		
		entryPanel.setLayout(new GridLayout(2,2));
		entryPanel.add(nameLabel);
		entryPanel.add(nodeName);
		entryPanel.add(durationLabel);
		entryPanel.add(nodeDuration);
		add(entryPanel);
		add(isStartingNode);
		entryPanel2.setLayout(new GridLayout(1,2));
		entryPanel2.add(dependenciesLabel);
		entryPanel2.add(nodeDependencies);
		add(entryPanel2);
		buttonPanel.setLayout(new GridLayout(3,2));
		buttonPanel.add(addNode);
		buttonPanel.add(analyze);
		buttonPanel.add(about);
		buttonPanel.add(help);
		buttonPanel.add(restart);
		buttonPanel.add(exit);
		add(buttonPanel);
		
		//Get things ready
		addNode.setText("Add Node");
		analyze.setText("Analyze Network");
		about.setText("About");
		help.setText("Help");
		restart.setText("Restart");
		exit.setText("Exit");
		isStartingNode.setText("Starting Node");
		isStartingNode.setSelected(false);
		nameLabel.setText("Activity name:");
		durationLabel.setText("Duration:");
		dependenciesLabel.setText("Dependencies");
		nodeName.setEditable(true);
		nodeDuration.setEditable(true);
		nodeDependencies.setEditable(true);
		//Add event listeners
		isStartingNode.addItemListener(new StartingNodeListener());
		addNode.addActionListener(new AddNodeListener());
		analyze.addActionListener(new AnalyzeListener());
		about.addActionListener(new AboutListener());
		help.addActionListener(new HelpListener());
		restart.addActionListener(new RestartListener());
		exit.addActionListener(new QuitListener());
		
		
	};
	//Listener for Starting node check box that turns off the dependencies text field if the check box is checked
	public class StartingNodeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange()==ItemEvent.SELECTED) {
				nodeDependencies.setEditable(false);
			} else {
				nodeDependencies.setEditable(true);
			}

		}
		
	}
	public class AddNodeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			/*
			 * Because NodeList is a singleton, this will return the NodeList in the init method
			 */
			NodeList list = NodeList.getInstance();
			/*
			 * If the input represents an invalid Node, this variable is set to true, which allows us to
			 * show an error UI at the end;
			 * The error code will be used to show the correct UI error message
			 * ErrorCode 0: No node dependencies, but it is not a head
			 * ErrorCode 1: The duration is NaN
			 * ErrorCode 2: nodeDuration or or nodeName is empty
			 * ErrorCode 3: node is dependency of itself
			 */
			boolean invalidNode = false;
			int errorCode = -1;
			/*
			 * Because we know that the nodeDependencies field will be editable if it is not a 
			 * head, we can use that to determine which constructor to use
			 */
			Node addedNode = null;
			if (nodeDependencies.isEditable()) {
				/*
				 * Tests to make sure all necessary field have been filled
				 */
				if(nodeDependencies.getText().length()==0)
				{
					invalidNode = true;
					errorCode = 0;
				}
				else if(nodeDuration.getText().length()==0 || nodeName.getText().length()==0)
				{
					invalidNode = true;
					errorCode = 2;
				}
				else
				{
					/*
					 * Parses the dependencies into an ArrayList
					 */
					ArrayList<String> nodes = new ArrayList<String>(Arrays.asList(nodeDependencies.getText().split(",")));
					String name = nodeName.getText();
					/*
					 * Tests to make sure a node isn't a dependency of itself
					 */
					for(String node: nodes)
					{
						if(name.equals(node)) {
							invalidNode = true;
							errorCode = 3;
						}
					}
					/*
					 * For each loop to verify nodeDuration stores a number
					 */
					for (char number : nodeDuration.getText().toCharArray())
				    {
				        if (!Character.isDigit(number))
				        {
				        	invalidNode = true;
				        	errorCode = 1;
				        	break;
				        }
				    }
					/*
					 * If node duration stores a number creates a new node
					 */
					if(!invalidNode)
					{
						int duration = Integer.parseInt(nodeDuration.getText());
						addedNode = new Node(name, duration, nodes);
					}
				}
			} 
			else 
			{
				if(nodeDuration.getText().length()==0 || nodeName.getText().length()==0)
				{
					invalidNode = true;
					errorCode = 0;
				}
				else
				{
					String name = nodeName.getText();
					/*
					 * For each loop to verify nodeDuration stores a number
					 */
					for (char number : nodeDuration.getText().toCharArray())
				    {
				        if (!Character.isDigit(number))
				        {
				        	invalidNode = true;
				        	errorCode = 1;
				        	break;
				        }
				    }
					/*
					 * If node duration stores a number creates a new node
					 */
					if(!invalidNode)
					{
						int duration = Integer.parseInt(nodeDuration.getText());
						addedNode = new Node(name, duration);
					}
				}
			}
			if(!invalidNode && addedNode != null) 
			{
				//Node gets added
				list.addToList(addedNode);
				JOptionPane.showMessageDialog(null,"Added node "+addedNode.getName()+" with duration "+addedNode.getDuration(), "Node added",JOptionPane.PLAIN_MESSAGE);
			}
			else if(invalidNode)
			{
				if(errorCode == 0) 
				{
					JOptionPane.showMessageDialog(null,"Could not add node because the node was not a head and had no dependencies","Node not added",JOptionPane.ERROR_MESSAGE);			
				}
				else if(errorCode == 1) 
				{
					JOptionPane.showMessageDialog(null,"Could not add node because the duration was not a number","Node not added",JOptionPane.ERROR_MESSAGE);			
				}
				else if(errorCode == 2) 
				{
					JOptionPane.showMessageDialog(null,"Could not add node because the node did not have name or duration","Node not added",JOptionPane.ERROR_MESSAGE);			
				}
				else if(errorCode == 3) 
				{
					JOptionPane.showMessageDialog(null,"Could not add node because a node cannot be a dependency of itself","Node not added",JOptionPane.ERROR_MESSAGE);			
				}
				
			}
			nodeDuration.setText("");
			nodeName.setText("");
			if(nodeDependencies.isEditable())
			{
				nodeDependencies.setText("");
			}

		}

	}
	
	public class AnalyzeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) 
		{
			NodeList list = NodeList.getInstance();
			Organizer organizingList = new Organizer(list);
			/*
			 * Tests to see if there are any errors in the nodeList
			 */
			organizingList.checkAll();
			/*
			 * If error found, goes through error codes to show correct message
			 */
			if(organizingList.isValid()==false)
			{
				if(Organizer.getErrorCode() == 0)
				{
					/**
					 * Display Message saying that there were multiple instances of a node
					 */
					{
						JOptionPane.showMessageDialog(null,"Could not determine paths because multiple nodes had the same name","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
					}
				}
				if(Organizer.getErrorCode() == 1)
				{
					/**
					 * Display Message saying that there were multiple instances of a node
					 */
					{
						JOptionPane.showMessageDialog(null,"Could not determine paths because dependencies do not exist","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
					}
				}
				if(Organizer.getErrorCode() == 2)
				{
					/**
					 * Display Message saying that there were multiple instances of a node
					 */
					{
						JOptionPane.showMessageDialog(null,"A circular path of nodes exist","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
					}
				}
				if(Organizer.getErrorCode() == 3)
				{
					/**
					 * 
					 */
					{
						JOptionPane.showMessageDialog(null,"All nodes are not properly connected to dependencies","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
					}
				}
				if(Organizer.getErrorCode() == 4)
				{
					/**
					 * 
					 */
					{
						JOptionPane.showMessageDialog(null,"No nodes have been added","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
					}
				}
			}
			
			/*
			 * If no errors found, proceeds to Analyze function
			 */
			else
			{
				/*
				 * Performs the recursive function to find all of the paths
				 */
				organizingList.recursiveStartPath();
				/*
				 * Sets the pathList's value and organizes the pathList by value
				 */
				organizingList.preparePathList();
				/*
				 * List of Strings that are the name of nodes in each path
				 */
				ArrayList<ArrayList<String>> pathStrings = new ArrayList<ArrayList<String>>(organizingList.getNames());
				/*
				 * List of length of each path
				 */
				ArrayList<Integer> pathDurations = new ArrayList<Integer>(organizingList.getDurations());
				/**
				 * 
				 * 	INSERT
				 * FRAME
				 * INFO
				 * FOR 
				 * PATHS
				 * HERE
				 * WITH
				 * ABOVE
				 * ARRAYS
				 * 
				 */
				//UI for showing analysis
				JFrame analysisFrame=new JFrame();
				analysisFrame.setTitle("Analysis Results");
				analysisFrame.setBounds(getX(), getY(), getWidth(), getHeight());
				analysisFrame.add(new AnalysisPanel(pathStrings,pathDurations));
				analysisFrame.setVisible(true);
				
			}
			NodeList.getInstance().resetList();
		}

	}
	public class AboutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			JOptionPane.showMessageDialog(null,"Built by CSE360 Group 1: Jacob Baca, Matthew Bohr, John Shaeffer, Michael St Onge\n"+
			"This program allows you to input an activity name and duration, as well as dependencies, and receive as output a network diagram as long as there is no cycle in the given information.\n"+
			"For more information, refer to our User Guide in the Help section. Thank you.","About",JOptionPane.PLAIN_MESSAGE);

		}

	}
	public class HelpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			JOptionPane.showMessageDialog(null,"Need help? In your internet browser, go to our User Guide, https://goo.gl/zJYWGg\n"+
			"'Add Node' will add a node into the system based on the specifications provided in the text boxes and starting node checkbox.\n"+
			"'Analyze Network' creates another window that shows the paths in the network, sorted by the longest paths first.\n"+
			"'About' will produce a window stating the authors of this program, along with a small description.\n"+
			"'Restart' and 'Quit' restart and quit the program, respectively, saving no data in the process.","Help",JOptionPane.PLAIN_MESSAGE);

		}

	}
	public class RestartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to restart? All data will be lost.","Restart", JOptionPane.YES_NO_OPTION);
			if (selectedOption==JOptionPane.YES_OPTION) {
			NodeList.getInstance().resetList();
			}
		}
	}
	public class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			int selectedOption=JOptionPane.showConfirmDialog(null,"Are you sure you want to quit? All data will be lost.","Quit",JOptionPane.YES_NO_OPTION);
			if (selectedOption==JOptionPane.YES_OPTION) {
				NodeList.getInstance().resetList();
				NetworkAnalysisExplorer.endApplication();
				}

		}

	}

}
