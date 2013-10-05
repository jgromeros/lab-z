/**
 * 
 */
package lab.model.labcase;

/**
 * @author juanromero
 *
 */
public class IllegalLabcaseStatusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalLabcaseStatusException(String status){
		super("Operaci—n no permitida cuando el caso se encuentra en estado " + status);
	}

}
