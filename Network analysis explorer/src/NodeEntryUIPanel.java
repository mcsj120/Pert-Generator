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
		isStartingNode.addItemListener(new StartingNodeListener());
		addNode.addActionListener(new AddNodeListener());
		analyze.addActionListener(new AnalyzeListener());
		
		
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
			 */
			boolean invalidNode = false;
			/*
			 * Because we know that the nodeDependencies field will be editable if it is not a 
			 * head, we can use that to determine which constructor to use
			 */
			Node addedNode = null;
			if (nodeDependencies.isEditable()) {
				/*
				 * Tests to make sure all necessary field have been filled
				 */
				if(nodeDependencies.getText().length()==0 || nodeDuration.getText().length()==0 || nodeName.getText().length()==0)
				{
					invalidNode = true;
				}
				else
				{
					/*
					 * Parses the dependencies into an ArrayList
					 */
					ArrayList<String> nodes = new ArrayList<String>(Arrays.asList(nodeDependencies.getText().split(",")));
					String name = nodeName.getText();
					/*
					 * For each loop to verify nodeDuration stores a number
					 */
					for (char number : nodeDuration.getText().toCharArray())
				    {
				        if (!Character.isDigit(number))
				        {
				        	invalidNode = true;
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
				list.addToList(addedNode);
			}
			else
			{
				//Code to show UI indicating an error in entering the Node
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
		public void actionPerformed(ActionEvent action) {
			// TODO Auto-generated method stub

		}

	}

}
