/*This class creates a JPanel with the main part of the UI
 * Written by Matthew Bohr
 *  */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
			// TODO Auto-generated method stub

		}

	}
	public class AnalyzeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// TODO Auto-generated method stub

		}

	}

}
