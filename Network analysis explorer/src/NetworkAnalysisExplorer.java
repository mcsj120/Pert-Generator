/*This class launches the UI via the init function of JApplet
 * Written by Matthew Bohr
 *  */
import java.awt.Frame;
import javax.swing.*;

public class NetworkAnalysisExplorer extends JApplet {
	public void init(){
		NodeEntryUIPanel mNodeEntryUIPanel=new NodeEntryUIPanel();
		getContentPane().add(mNodeEntryUIPanel);
		setSize(300, 500);
		setVisible(true);
		Frame appletFrame = (Frame)this.getParent().getParent();
		appletFrame.setTitle("Network Analyzer");
		
		
	}

	public static void endApplication() {
		System.exit(0);
	}

}
