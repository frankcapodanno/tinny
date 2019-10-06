package click.capodanno.tinny.soundgenerator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

import javax.sound.sampled.SourceDataLine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import click.capodanno.tinny.CommandState;

public class SoundGenerator {

	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;

	protected static final Logger logger = LogManager.getLogger();

	public static final float SAMPLE_RATE = 44100.0F;
	public static final float SAMPLE_SIZE = 16; // in bit
	public static final float CHANNELS = 1;
	public static final boolean SIGNED = true;
	public static final boolean BIG_ENDIAN = true;
	public static final int BUFFER_SIZE = 512;
	public static final float SWITCH_PHASE_CONST = 0.1f;

	public SoundGenerator() {
		logger.info("sound generator called...");
	}

	/**
	 * Generate a Pure Tone at a specific frequency
	 * 
	 * @param freq frequency
	 * @throws LineUnavailableException if the audio card line is not available
	 * @throws InvalidFrequencyException 
	 */
	public synchronized void generateSound(float freq) throws LineUnavailableException, InvalidFrequencyException {
		checkFrequency(freq);
		byte[] buf = new byte[BUFFER_SIZE];
		AudioFormat af = new AudioFormat((float) SAMPLE_RATE, 8, 1, SIGNED, BIG_ENDIAN);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();
		int i = 0;

		while (CommandState.getInstance().soundOn) {
			for (int k = 0; k < BUFFER_SIZE; k++) { //buffer
				double angle = i / (SAMPLE_RATE / freq) * 2.0 * Math.PI;
				if (i < SAMPLE_RATE) {
					i++;
				} else if (i == SAMPLE_RATE)
				    i = 0;
				buf[k] = (byte) (Math.sin(angle) * 100);
			}
			sdl.write(buf, 0, buf.length); // consumes buffer
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}

	public synchronized void generateSoundSearchingPhase(float freq) throws LineUnavailableException, InvalidFrequencyException {
		checkFrequency(freq);
		byte[] buf = new byte[BUFFER_SIZE];
		AudioFormat af = new AudioFormat((float) SAMPLE_RATE, 8, 1, SIGNED, BIG_ENDIAN);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();
		int i = 0;
		float switchPhase = 0.1f;

		while (CommandState.getInstance().soundOn) {
			
			if (CommandState.getInstance().searchingPhase) {
				switchPhase = switchPhase + SWITCH_PHASE_CONST;
				logger.debug("switch phase = {}", switchPhase);
			}
			for (int k = 0; k < BUFFER_SIZE; k++) { //buffer
				double angle = i / (SAMPLE_RATE / freq) * 2.0 * Math.PI + switchPhase;
				if (i < SAMPLE_RATE) {
					i++;
				} else if (i == SAMPLE_RATE)
				    i = 0;
				buf[k] = (byte) (Math.sin(angle) * 100);
			}
			sdl.write(buf, 0, buf.length); // consumes buffer
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
	
	public void checkFrequency(float freq) throws InvalidFrequencyException{
		if (freq < 20 && freq > 20000) throw new InvalidFrequencyException("Not valid frequency: " + freq);
	}
}