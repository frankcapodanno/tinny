package click.capodanno.tinny.soundgenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import click.capodanno.tinny.CommandState;

public class OnOffUtil implements Runnable {

	protected static final Logger logger = LogManager.getLogger();
	
	private int pauseInMs;
	
	public OnOffUtil() {
		pauseInMs = 1000;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(pauseInMs);
			logger.debug("Command Pure Sound Off");
			CommandState.getInstance().soundOn = false;
			logger.debug("Searching phase Off");
			CommandState.getInstance().searchingPhase = false;
		} catch (InterruptedException e) {
			logger.error(e.getStackTrace());
			Thread.currentThread().interrupt();
		}
	}

	public int getPauseInMs() {
		return pauseInMs;
	}

	public void setPauseInMs(int pauseInMs) {
		this.pauseInMs = pauseInMs;
	}
}