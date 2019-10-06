package click.capodanno.tinny.soundgenerator;

import javax.sound.sampled.LineUnavailableException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import click.capodanno.tinny.CommandState;
import junit.framework.TestCase;

public class SoundGeneratorTest extends TestCase {
	
	protected static final Logger logger = LogManager.getLogger();

	@Override
	protected void setUp() throws Exception {
	    Configurator.setRootLevel(Level.DEBUG);
	}
	
	/**
	 * Generate a Sound test buffering for a not glitch sound
	 * 
	 * @throws LineUnavailableException if the audio card has not available lines.
	 */
	public void testSoundGeneration() throws LineUnavailableException {
		SoundGenerator sg = new SoundGenerator();
		logger.debug("pure sound on");
		CommandState.getInstance().soundOn = true;
		Thread onOffUtilThread = new Thread(new OnOffUtil());
		onOffUtilThread.start();
		sg.generateSound(440);
	}
}
