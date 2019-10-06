package click.capodanno.tinny;

/**
 * Save the state of the commands of the UI
 * 
 * @author frank
 */
public class CommandState {

	private static CommandState instance;

	private CommandState() {

	}

	synchronized public static CommandState getInstance() {
		if (instance == null) {
			instance = new CommandState();
		}
		return instance;
	}
	
	public boolean soundOn = false;
	public boolean searchingPhase = false;
}