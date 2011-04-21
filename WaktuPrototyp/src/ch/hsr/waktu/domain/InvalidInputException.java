package ch.hsr.waktu.domain;

public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	InvalidInputException() {
		
	}
	
	InvalidInputException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
