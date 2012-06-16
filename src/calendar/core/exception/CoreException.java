package calendar.core.exception;
/**
 * Parent class of all application specific exceptions.
 * Contains a detail information field which contains useful error
 * data to threat the exception.
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.16
 */
public abstract class CoreException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Detail information
	 */
	public Object detailInformation;
}
