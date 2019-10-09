package click.capodanno.tinny.soundgenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import click.capodanno.tinny.CommandState;

public class OnOffUtil implements Runnable {

	protected static final Logger logger = LogManager.getLogger();
	
	private int pauseInMs;
	
	public OnOffUtil(int p) {
		this.pauseInMs = p;
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
}