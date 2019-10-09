package click.capodanno.tinny.soundgenerator;

import javax.sound.sampled.LineUnavailableException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecuteSoundParallel implements Runnable{
	
	protected static final Logger logger = LogManager.getLogger();
	
	private float freq;
	private float shiftPhase;
	private SoundGenerator sg;
	private SoundGenerator sg2;

	/**
	 * Just the second sound change the phase
	 * 
	 * @param freq frequency constant with the sounds
	 * @param phase change in the sound two
	 * @throws LineUnavailableException 
	 */
	public ExecuteSoundParallel(float freq, float shiftPhase) throws LineUnavailableException {
		logger.debug("double sound with freq: {} ", freq);
		this.freq = freq;
		this.shiftPhase = shiftPhase;
		sg = new SoundGenerator(1, PanType.LEFT);
		sg2 = new SoundGenerator(2, PanType.RIGHT);
	}

	@Override
	public void run() {
		try {
			if (shiftPhase == 0) {sg.generateSound(freq);}
			else if (shiftPhase > 0) {sg2.generateSoundSearchingPhase(freq, shiftPhase);}
		} catch (LineUnavailableException | InvalidFrequencyException e) {
			logger.error(e.getStackTrace());
		}	
	}
}