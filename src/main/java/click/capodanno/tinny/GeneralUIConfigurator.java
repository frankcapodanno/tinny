package click.capodanno.tinny;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GeneralUIConfigurator {

	private GeneralUIConfigurator() {
	}

	public static void build(JFrame frame) {
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("<html><h1>Hello in in Tinny!</h1><br />"
				+ "Tinny is a simple application and help you to find a phase of a sound for a specific frequency</html>");

		// frequency panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.setName("commands");

		JSlider slider = new JSlider();
		slider.setMaximum(10000);
		slider.setMinimum(30);

		// add to the panel
		panel.add(label, BorderLayout.NORTH);
		panel.add(slider, BorderLayout.CENTER);
		panel.setBackground( new Color( 218, 255, 202) );

		// add to the frame
		frame.getContentPane().add(panel);
	}
}