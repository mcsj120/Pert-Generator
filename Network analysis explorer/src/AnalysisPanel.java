import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AnalysisPanel extends JPanel {
	private JButton recalculate,report,changeOk;
	private JTextField changeNodeName,changeNodeDuration;
	private JLabel changeNameLabel,changeDurationLabel;
	public JPanel entryPanel3,buttonPanel2,buttonPanel3; 
	public AnalysisPanel(ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations) {
		
		setLayout(new GridLayout(2,1));
		//This should always pass. But if it doesn't, we shouldn't show anything
		if (pathStrings.size()==pathDurations.size()) {
			JTable resultsTable= new JTable(new ResultsModel(pathStrings,pathDurations));
			JScrollPane s=new JScrollPane(resultsTable);
			add(s);
			//add the new button panel
			buttonPanel2=new JPanel();
			recalculate = new JButton();
			report = new JButton();
			buttonPanel2.setLayout(new GridLayout(1,2));
			recalculate.setText("Change Node");
			report.setText("Make Report");
			recalculate.addActionListener(new RecalculateListener());
			report.addActionListener(new ReportListener());
			buttonPanel2.add(recalculate);
			buttonPanel2.add(report);
			add(buttonPanel2);
			
					
		}
		else {
			add( new JLabel("Something went wrong."));
		}
	}
	public class RecalculateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent action)
		{
			JFrame changeNodeFrame = new JFrame();
			buttonPanel3 = new JPanel();
			entryPanel3=new JPanel();
			changeOk = new JButton();
			changeNodeName = new JTextField();
			changeNodeDuration = new JTextField();
			changeNameLabel = new JLabel();
			changeDurationLabel = new JLabel();
			changeNameLabel.setText("Name of node to change:");
			changeDurationLabel.setText("New duration:");
			changeNodeDuration.setEditable(true);
			changeNodeName.setEditable(true);
			changeNodeFrame.setTitle("Change Node Duration");
			changeNodeFrame.setBounds(getX(), getY(), getWidth()*3, getHeight()/4);
			entryPanel3.setLayout(new GridLayout(2,3));
			entryPanel3.add(changeNameLabel);
			entryPanel3.add(changeNodeName);
			entryPanel3.add(changeDurationLabel);
			entryPanel3.add(changeNodeDuration);
			entryPanel3.add(buttonPanel3);
			changeOk.setText("OK");
			changeOk.addActionListener(new ChangeOKListener());
			buttonPanel3.add(changeOk);
			changeNodeFrame.add(entryPanel3);
			changeNodeFrame.setVisible(true);
		}
	}
	public class ReportListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent action)
		{
			//NodeList list = NodeList.getInstance();
			//Organizer organizingList = new Organizer(list);
			//ReportCreator creator = new ReportCreator(organizingList);
			//String textReport = "";
			//try {
			//	creator.createReport(textReport);
			//} catch(IOException e)
			//{
				
			//}
			//TODO: @Jacob, could you review the code necessary to produce the report here?
			//Update from Matthew: commented this out because it was unimplemented and didn't want any side effects temporarily. Uncomment when finished with the implementation of this.
			}
	}
	public class ChangeOKListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent action)
		{
			//Recalculate the paths and close the frame
		}
		
	}
}
