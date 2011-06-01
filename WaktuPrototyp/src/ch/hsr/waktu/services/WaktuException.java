package ch.hsr.waktu.services;

public class WaktuException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";

	WaktuException() {

	}

	public WaktuException(final String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
