package click.capodanno.tinny.soundgenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import click.capodanno.tinny.CommandState;

public class OnOffUtil implements Runnable {

	protected static final Logger logger = LogManager.getLogger();

	@Override
	public void run() {
		try {
			logger.debug("OnOff Utility Thread started...");
			Thread.sleep(1000);
			logger.debug("Command Pure Sound Off");
			CommandState.getInstance().soundOn = false;
		} catch (InterruptedException e) {
			logger.error(e.getStackTrace());
			Thread.currentThread().interrupt();
		}
	}
}