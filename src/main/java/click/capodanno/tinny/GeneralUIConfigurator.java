package click.capodanno.tinny;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class GeneralUIConfigurator extends JFrame implements ActionListener {
	//pure sound
	JButton button1;
	
	//play the sound
	JButton button2;
	
	//slider
	JSlider sliderSwitchPhase;
	
	//buttonSave config
	JButton buttonSave;

	public void build(JFrame frame) {
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("<html><h1>Hello in Tinny!</h1><br />"
				+ "Tinny is a simple application and help you to find a counterphase of a specific sound frequency</html>");

		GridLayout gLayout = new GridLayout(4, 0);
		BorderLayout bLayout = new BorderLayout();
		BorderLayout bLayout2 = new BorderLayout();
		BorderLayout bLayout3 = new BorderLayout();

		Font f3 = new Font(Font.DIALOG, Font.BOLD, 15);

		// set Border
		Border simpleBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.CYAN, Color.DARK_GRAY);
		Border orangeBorder = BorderFactory.createLineBorder(Color.ORANGE, 5, true);

		// Menu Load and About
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem loadMenuItem = new JMenuItem("load");
		JMenuItem saveMenuItem = new JMenuItem("save");
		JMenuItem aboutMenuItem = new JMenuItem("about");
		menu.add(loadMenuItem);
		menu.add(saveMenuItem);
		menu.add(aboutMenuItem);
		menuBar.add(menu);
		

		// frequency panel command
		JPanel panel = new JPanel();
		JPanel subPanel = new JPanel();
		JPanel subPanel2 = new JPanel();
		JPanel subPanel3 = new JPanel();

		// panel configuration
		panel.setName("commands");
		panel.setLayout(gLayout);

		panel.setBorder(simpleBorder);
		panel.setBackground(new Color(218, 255, 202));
		// sub panel frequency
		subPanel.setName("frequency");
		subPanel.setLayout(bLayout);
		subPanel.setBackground(new Color(217, 255, 251));

		subPanel.setBorder(orangeBorder);
		// subpanel2 phase
		subPanel2.setName("phase");
		subPanel2.setLayout(bLayout2);
		subPanel2.setBackground(new Color(217, 255, 251));
		subPanel2.setBorder(orangeBorder);

		// subpanel3 phase
		subPanel3.setName("output");
		subPanel3.setLayout(bLayout3);
		subPanel3.setBackground(new Color(217, 255, 251));
		subPanel3.setBorder(orangeBorder);

		// subpanel
		JLabel fLabel = new JLabel("<html><h2>Select the frequency</h2><p>select a sound that is near your tinnitus</p></html>");

		JSlider sliderFreq = new JSlider();
		sliderFreq.setMaximum(20000);
		sliderFreq.setMinimum(30);
		sliderFreq.setBorder(simpleBorder);

		JTextField text = new JTextField();
		text.setEnabled(true);
		text.setEditable(false);
		text.setText("20 Hz");
		text.setFont(f3);
		
		button1 = new JButton();
		button1.setText("Play the pure sound");

		// subpanel2
		JLabel fLabel2 = new JLabel("<html><h2>Search your counterphase</h2><p>now change the phase and try to find if there are disturbances with your tinnitus</p></html>");
		
		sliderSwitchPhase = new JSlider();
		sliderSwitchPhase.setMaximum(100);
		sliderSwitchPhase.setMinimum(0);
		sliderSwitchPhase.setBorder(simpleBorder);
		
		JTextField textSwitchPhase = new JTextField();
		textSwitchPhase.setEnabled(true);
		textSwitchPhase.setEditable(false);
		textSwitchPhase.setText("0");
		textSwitchPhase.setFont(f3);

		button2 = new JButton();
		button2.setText("Play the sound");
		button2.setBorder(simpleBorder);

		// subpanel3
		JLabel fLabel3 = new JLabel("<html><h2>Output</h2></html>");

		JTextPane textOutput = new JTextPane();

		buttonSave = new JButton();
		buttonSave.setText("Save the actual configuration");

		// positioning
		panel.add(label);

		subPanel.add(fLabel, BorderLayout.NORTH);
		subPanel.add(text, BorderLayout.CENTER);
		subPanel.add(button1, BorderLayout.EAST);
		subPanel.add(sliderFreq, BorderLayout.SOUTH);

		subPanel2.add(fLabel2, BorderLayout.NORTH);
		subPanel2.add(textSwitchPhase, BorderLayout.CENTER);
		subPanel2.add(button2, BorderLayout.EAST);
		subPanel2.add(sliderSwitchPhase, BorderLayout.SOUTH);

		subPanel3.add(fLabel3, BorderLayout.NORTH);
		subPanel3.add(textOutput, BorderLayout.CENTER);
		subPanel3.add(buttonSave, BorderLayout.SOUTH);

		panel.add(subPanel);
		panel.add(subPanel2);
		panel.add(subPanel3);

		// add Listeners
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		
		// add to the frame
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.button1) {
			
		}
		
	}
}