package by.filippov.library.exceptions;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}
