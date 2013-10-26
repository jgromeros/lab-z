/**
 * 
 */
package lab.exceptions;

/**
 * @author juano
 * 
 */
public class LabcaseException extends RuntimeException {

	public LabcaseException() {
		super();
	}

	public LabcaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public LabcaseException(String message) {
		super(message);
	}

	public LabcaseException(Throwable cause) {
		super(cause);
	}

}
