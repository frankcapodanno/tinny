package click.capodanno.tinny.soundgenerator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import click.capodanno.tinny.CommandState;

public class SoundGenerator {

	protected static final Logger logger = LogManager.getLogger();

	public static final float SAMPLE_RATE = 44100.0F;
	public static final int SAMPLE_SIZE = 16; // in bit
	public static final boolean SIGNED = true;
	public static final boolean BIG_ENDIAN = true;
	public static final int BUFFER_SIZE = 512;
	private SourceDataLine sdl;
	private AudioFormat af;

	/**
	 * this constructor need define the type of Pan and the channel where the sound
	 * play
	 * 
	 * @param channel channel where play the sound
	 * @param p       panning LEFT, CENTER or RIGHT if is supported
	 * @throws LineUnavailableException when there is problems with the line
	 */
	public SoundGenerator(int channel, PanType p) throws LineUnavailableException {
		af = new AudioFormat((float) SAMPLE_RATE, SAMPLE_SIZE, channel, SIGNED, BIG_ENDIAN);
		sdl = AudioSystem.getSourceDataLine(af);
//		logger.debug(getLineInfos());
//		Control[] controls = sdl.getControls();
//		if (logger.getLevel() == Level.DEBUG) logger.debug(Arrays.deepToString(controls));
		switch (p) {
		case CENTER:
			changePan(0, sdl);
			break;
		case LEFT:
			changePan(-1, sdl);
			break;
		case RIGHT:
			changePan(+1, sdl);
			break;
		default:
			break;
		}
	}

	/**
	 * Generate a Pure Tone at a specific frequency
	 * 
	 * @param freq frequency
	 * @throws LineUnavailableException  if the audio card line is not available
	 * @throws InvalidFrequencyException not valid frequency
	 */
	public synchronized void generateSound(float freq) throws LineUnavailableException, InvalidFrequencyException {
		checkFrequency(freq);
		executeSound(freq, 0);
	}

	/**
	 * Generate the Sound Searching with a translated phase
	 * 
	 * @param freq frequency
	 * @phase phase the phase that will be translated
	 * @throws LineUnavailableException  if the line is not available
	 * @throws InvalidFrequencyException not valid frequency
	 */
	public synchronized void generateSoundSearchingPhase(float freq, float phase)
			throws LineUnavailableException, InvalidFrequencyException {
		checkFrequency(freq);
		executeSound(freq, phase);
	}

	private synchronized void checkFrequency(float freq) throws InvalidFrequencyException {
		if (freq < 20 && freq > 20000)
			throw new InvalidFrequencyException("Not valid frequency: " + freq);
	}

	private synchronized void executeSound(float freq, float switchPhase) throws LineUnavailableException {
		byte[] buf = new byte[BUFFER_SIZE];
		sdl.open();
		sdl.start();
		int i = 0;
		while (CommandState.getInstance().soundOn) {
			for (int k = 0; k < BUFFER_SIZE; k++) { // buffer
				double angle = i / (SAMPLE_RATE / freq) * 2.0 * Math.PI + switchPhase;
				if (i < SAMPLE_RATE) {
					i++;
				} else if (i == SAMPLE_RATE) {
					i = 0;
				}
				buf[k] = (byte) (Math.sin(angle) * 100);
			}
			sdl.write(buf, 0, buf.length); // consumes buffer
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}

	/**
	 * change the pan of the sound
	 * 
	 * @param pan  -1.0 left +1.0 right
	 * @param line the line of the sound
	 */
	private synchronized void changePan(float pan, SourceDataLine line) {
		if (line.isControlSupported(FloatControl.Type.PAN)) {
			FloatControl panControl = (FloatControl) line.getControl(FloatControl.Type.PAN);
			panControl.setValue(pan);
		} else {
			logger.warn("panning is not supported...");
		}
	}

	public String getLineInfos() {
		return sdl.getLineInfo().toString();
	}
}