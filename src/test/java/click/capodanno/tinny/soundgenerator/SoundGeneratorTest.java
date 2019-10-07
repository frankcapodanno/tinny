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
	 * Generate a Sound test buffered for a not glitch sound
	 * 
	 * @throws LineUnavailableException if the audio card has not available lines.
	 * @throws InterruptedException thread problems
	 * @throws InvalidFrequencyException not valid frequency input
	 */
	public void testSoundGeneration() throws LineUnavailableException, InterruptedException, InvalidFrequencyException {
		SoundGenerator sg = new SoundGenerator();
		logger.debug("pure sound on");
		CommandState.getInstance().soundOn = true;
		Thread onOffUtilThread = new Thread(new OnOffUtil());
		onOffUtilThread.start();
		sg.generateSound(440);
		logger.debug("sleep 3s until the next test");
		Thread.sleep(3000);
		OnOffUtil onOffobj = new OnOffUtil();
		onOffobj.setPauseInMs(10000);
		onOffUtilThread = new Thread(onOffobj);
		onOffUtilThread.start();
		CommandState.getInstance().soundOn = true;
		CommandState.getInstance().searchingPhase = true;
		sg.generateSoundSearchingPhase(440);
	}
	
	public void testCounterPhase() {
		
	}
}
