/*This class launches the UI via the init function of JApplet
 * Written by Matthew Bohr
 *  */
import javax.swing.*;

public class NetworkAnalysisExplorer extends JApplet {
	public void init(){
		NodeEntryUIPanel mNodeEntryUIPanel=new NodeEntryUIPanel();
		getContentPane().add(mNodeEntryUIPanel);
		
		
	}

}
