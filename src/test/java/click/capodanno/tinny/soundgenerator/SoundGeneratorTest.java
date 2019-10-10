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
	 * @throws LineUnavailableException  if the audio card has not available lines.
	 * @throws InterruptedException      thread problems
	 * @throws InvalidFrequencyException not valid frequency input
	 */
	public void testSoundGeneration() throws LineUnavailableException, InterruptedException, InvalidFrequencyException {
		SoundGenerator sg = new SoundGenerator(1, PanType.RIGHT);
		logger.debug("pure sound on");
		CommandState.getInstance().soundOn = true;
		Thread onOffUtilThread = new Thread(new OnOffUtil(1500));
		onOffUtilThread.start();
		sg.generateSound(440);
		logger.debug("sleep 3s until the next test");
		Thread.sleep(3000);
	}

	/**
	 * test the counterphase
	 * 
     * @throws LineUnavailableException  if the audio card has not available lines.
	 * @throws InterruptedException      thread problems
	 * @throws InvalidFrequencyException not valid frequency input
	 */
	public void testCounterPhase() throws LineUnavailableException, InvalidFrequencyException, InterruptedException {
		//start a parallel sound
		CommandState.getInstance().soundOn = true;
		CommandState.getInstance().searchingPhase = true;
		Thread sound1 = new Thread(new ExecuteSoundParallel(440,0));
		sound1.start();
		// off all in 10 second
		Thread onOffUtilThread = new Thread(new OnOffUtil(10000));
		onOffUtilThread.start();
		Thread.sleep(500);
		float shiftPhase;
		for (int i=0; i<20; i++) {
			shiftPhase = (float) i;
			logger.debug("shift phase: {}", shiftPhase);
			CommandState.getInstance().searchingPhase = true;
			CommandState.getInstance().soundOn = true;
			Thread sound2 = new Thread(new ExecuteSoundParallel(440,shiftPhase));
			sound2.start();
			Thread.sleep(1000);
		 }
	}
}