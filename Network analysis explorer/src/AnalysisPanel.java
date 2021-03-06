import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AnalysisPanel extends JPanel {
	private JButton recalculate,report,changeOk, reportNameOKButton;
	private JTextField changeNodeName,changeNodeDuration,reportNameTextField;
	private JLabel changeNameLabel,changeDurationLabel,enterNamePromptLabel;
	public JPanel entryPanel3,buttonPanel2,buttonPanel3,reportNamePanel,reportEntryNamePanel;
	private Organizer mostRecentOrganizer; //organizer that contains the most recent Organizer that has had analysis done on it
	public AnalysisPanel(ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations, Organizer organizer) {
		// sets mostRecentOrganizer to the passed Organizer
		mostRecentOrganizer = organizer;
		
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
			recalculate.addActionListener(new RecalculateListener(pathStrings,pathDurations));
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
		private ArrayList<ArrayList<String>> pathString;
		private ArrayList<Integer> pathDuration;
		
		RecalculateListener(ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations){
			pathString=pathStrings;
			pathDuration=pathDurations;
			
		}
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
			changeNodeFrame.setBounds(getX(), getY(), getWidth(), getHeight());
			entryPanel3.setLayout(new GridLayout(3,2));
			entryPanel3.add(changeNameLabel);
			entryPanel3.add(changeNodeName);
			entryPanel3.add(changeDurationLabel);
			entryPanel3.add(changeNodeDuration);
			entryPanel3.add(buttonPanel3);
			changeOk.setText("OK");
			changeOk.addActionListener(new ChangeOKListener(changeNodeFrame,pathString,pathDuration));
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
			
			//prompt for file name
			JFrame reportNameFrame = new JFrame();
			reportNameFrame.setTitle("Enter Project Name");
			reportNameFrame.setSize(400,200);
			reportNamePanel = new JPanel();
			reportEntryNamePanel = new JPanel();
			reportNameTextField = new JTextField();
			reportNameOKButton = new JButton("OK");
			reportNameOKButton.addActionListener(new ReportNameOKListener(reportNameFrame));
			enterNamePromptLabel = new JLabel("Enter Report Name: ");
			reportEntryNamePanel.setLayout(new GridLayout(1,2));
			reportEntryNamePanel.add(enterNamePromptLabel);
			reportEntryNamePanel.add(reportNameTextField);
			reportNamePanel.setLayout(new GridLayout(2,1));
			reportNamePanel.add(reportEntryNamePanel);
			reportNamePanel.add(reportNameOKButton);
			reportNameFrame.add(reportNamePanel);
			reportNameFrame.setVisible(true);
			
			
			
		}
	}
	public class ChangeOKListener implements ActionListener
	{
		JFrame frameToClose;
		ArrayList<ArrayList<String>> pathNames;
		ArrayList<Integer> pathDuration;
		ChangeOKListener(JFrame frame,ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations){
			super();
			frameToClose = frame;
			pathNames=pathStrings;
			pathDuration=pathDurations;
		}
		@Override
		public void actionPerformed(ActionEvent action)
		{ 
			int d = 0;
		  try  
		  {  
		    d = Integer.parseInt(changeNodeDuration.getText());  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
			  JOptionPane.showMessageDialog(null,"Duration is Not a Number","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
		    return; 
		  }  
			boolean x = mostRecentOrganizer.reCalculate(changeNodeName.getText(), d);
			if(!x)
			{
				JOptionPane.showMessageDialog(null,"Node was not found","Could Not Analyze",JOptionPane.ERROR_MESSAGE);			
				return;
			}
			if(NodeEntryUIPanel.copOut)
			{
				ArrayList<PathData> criticalPaths = mostRecentOrganizer.getCritical();
				
				pathNames = new ArrayList<ArrayList<String>>(mostRecentOrganizer.getCriticalNames(criticalPaths));
				
				pathDuration = new ArrayList<Integer>(mostRecentOrganizer.getCriticalDuration(criticalPaths));
			}
			else
			{
				mostRecentOrganizer.preparePathList();
				pathNames = new ArrayList<ArrayList<String>>(mostRecentOrganizer.getNames());
				pathDuration = new ArrayList<Integer>(mostRecentOrganizer.getDurations());
			}

			JFrame newFrame=new JFrame();
			newFrame.add(new AnalysisPanel(pathNames,pathDuration,mostRecentOrganizer));
			newFrame.setSize(400, 800);
			newFrame.setVisible(true);
			frameToClose.dispose();
			NodeEntryUIPanel.analysisFrame.dispose();
			NodeEntryUIPanel.analysisFrame = newFrame;
		}
		
	}
	public class ReportNameOKListener implements ActionListener
	{
		JFrame frameToClose;
		ReportNameOKListener(JFrame frame){
			super();
			frameToClose = frame;
		}
		@Override
		public void actionPerformed(ActionEvent action)
		{
			//set
			ReportCreator report = new ReportCreator(mostRecentOrganizer);
			String reportName = reportNameTextField.getText();
			try {
				report.createReport(reportName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frameToClose.dispose();
			//textfile created in bin
			//Project Name can also be path as long as program has permissions to write to location (may need to test if jar file)
		}
		
	}
	
}
