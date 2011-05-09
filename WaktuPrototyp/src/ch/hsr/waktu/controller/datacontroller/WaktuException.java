package ch.hsr.waktu.controller.datacontroller;

public class WaktuException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	WaktuException() {
		
	}
	
	public WaktuException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
