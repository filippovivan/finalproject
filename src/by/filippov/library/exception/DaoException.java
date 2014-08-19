package by.filippov.library.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
