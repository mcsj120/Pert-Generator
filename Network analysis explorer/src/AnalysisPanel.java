import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AnalysisPanel extends JPanel {
	public AnalysisPanel(ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations) {
		setLayout(new GridLayout(1,1));
		//This should always pass. But if it doesn't, we shouldn't show anything
		if (pathStrings.size()==pathDurations.size()) {
			
			JTable resultsTable= new JTable(new ResultsModel(pathStrings,pathDurations));
			JScrollPane s=new JScrollPane(resultsTable);
			add(s);
			
					
		}
		else {
			add( new JLabel("Something went wrong."));
		}
	}
}
