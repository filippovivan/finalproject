package by.filippov.library.exception;

public class TechnicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TechnicalException(String message, Throwable e) {
		super(message, e);
	}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(Throwable e) {
		super(e);
	}

}
