package click.capodanno.tinny;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	/**
	 * show the first page
	 */
    private static void createAndShowGUI() {
        //Create and set up the window.
        logger.info("gui started...");
        JFrame frame = new JFrame("Tinny ver. 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

//        //Add the ubiquitous "Hello World" label.
//        JLabel label = new JLabel("Hello in in Tinny!");
//        frame.getContentPane().add(label);
        
        GeneralUIConfigurator.build(frame);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        logger.info("tinny is started...");
    }
	
	protected static final Logger logger = LogManager.getLogger();
	
	/**
	 * main 
	 * @param args no args accepted
	 */
	public static void main(String[] args) {
	    
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}