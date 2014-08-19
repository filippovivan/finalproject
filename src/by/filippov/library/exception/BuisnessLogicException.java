package by.filippov.library.exception;

public class BuisnessLogicException extends Exception {

	private static final long serialVersionUID = 1L;

	public BuisnessLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuisnessLogicException(String message) {
		super(message);
	}

	public BuisnessLogicException(Throwable cause) {
		super(cause);
	}

}
