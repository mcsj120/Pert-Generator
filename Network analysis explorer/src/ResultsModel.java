import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ResultsModel extends AbstractTableModel{
	ArrayList<ArrayList<String>> strings;
	ArrayList<Integer> durations;
	public ResultsModel(ArrayList<ArrayList<String>> pathStrings, ArrayList<Integer> pathDurations) {
		pathStrings=strings;
		pathDurations=durations;
	}
	
	@Override
	public int getColumnCount() {
		
		return 2;
	}

	@Override
	public int getRowCount() {
		return strings.size();
	}
	public String getColumnName(int col) {
		if (col==0) {
			return "Path";
		} 
		else {
	      return "Duration";
	      }
	    }

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (arg1==0) {
		return strings.get(arg0);
		}
		else {
		return durations.get(arg0);
		}
	}

}
