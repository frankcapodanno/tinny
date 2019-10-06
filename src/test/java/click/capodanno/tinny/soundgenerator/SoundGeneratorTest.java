package click.capodanno.tinny.soundgenerator;

import javax.sound.sampled.LineUnavailableException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import click.capodanno.tinny.CommandState;
import junit.framework.TestCase;

public class SoundGeneratorTest extends TestCase {

	protected void setUp() throws Exception {
	    Configurator.setRootLevel(Level.DEBUG);
	}
	
	public void test_SoundGeneration() throws LineUnavailableException {
		SoundGenerator sg = new SoundGenerator();
		CommandState.getInstance().soundOn = true;
		sg.generateSound(440);
	}
}
