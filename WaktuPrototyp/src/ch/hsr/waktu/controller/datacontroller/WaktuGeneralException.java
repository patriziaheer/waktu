package ch.hsr.waktu.controller.datacontroller;

public class WaktuGeneralException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	WaktuGeneralException() {
		
	}
	
	WaktuGeneralException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
