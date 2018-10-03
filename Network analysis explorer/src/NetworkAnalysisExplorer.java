/*This class launches the UI via the init function of JApplet
 * Written by Matthew Bohr
 *  */
import javax.swing.*;

public class NetworkAnalysisExplorer extends JApplet {
	public void init(){
		NodeEntryUIPanel mNodeEntryUIPanel=new NodeEntryUIPanel();
		JFrame mFrame=new JFrame();
		mFrame.getContentPane().add(mNodeEntryUIPanel);
		mFrame.setSize(300, 500);
		mFrame.setVisible(true);
		
		
	}

}
