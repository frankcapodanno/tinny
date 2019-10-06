package click.capodanno.tinny.soundgenerator;

/**
 * This exception occour when the frequency is not major then 20 and minor then 20000
 * 
 * @author frank
 */
public class InvalidFrequencyException extends Exception {

	public InvalidFrequencyException(String s) {
		super(s);
	}

	private static final long serialVersionUID = 441536413747240693L;

}