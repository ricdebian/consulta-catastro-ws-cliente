package es.meh.catastro.exception;

public class CatastroException extends Exception {

	private static final long serialVersionUID = -8076052004880440700L;

	public CatastroException() {
		super();
	}

	public CatastroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super();
	}

	public CatastroException(String message, Throwable cause) {
		super(message, cause);
	}

	public CatastroException(String message) {
		super(message);
	}

	public CatastroException(Throwable cause) {
		super(cause);
	}
}
