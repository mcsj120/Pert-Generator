/*This class creates a JPanel with the main part of the UI
 * Written by Matthew Bohr
 *  */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class NodeEntryUIPanel extends JPanel {
	
	private JButton addNode,analyze,about,help,restart,exit;
	private JTextField nodeName,nodeDuration,nodeDependencies;
	private JCheckBox isStartingNode;
	private JLabel nameLabel,durationLabel,dependenciesLabel;
	
	//Constructor
	public NodeEntryUIPanel() {
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

}
