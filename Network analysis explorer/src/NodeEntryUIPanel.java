/*This class creates a JPanel with the main part of the UI
 * Written by Matthew Bohr
 *  */
import javax.swing.*;

public class NodeEntryUIPanel extends JPanel {
	private JButton addNode,analyze,about,help,restart,exit;
	private JTextField nodeName,nodeDuration,nodeDependencies;
	private JCheckBox isStartingNode;
	private JLabel nameLabel,durationLabel,dependenciesLabel;
	
	public NodeEntryUIPanel() {
		addNode.setText("Add Node");
		analyze.setText("Analyze Network");
		about.setText("About");
		help.setText("Help");
		restart.setText("Exit");
		isStartingNode.setText("Starting Node");
		nameLabel.setText("Activity name:");
		durationLabel.setText("Duration:");
		dependenciesLabel.setText("Dependencies");
		
		
	};

}
