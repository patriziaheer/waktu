package ch.hsr.waktu.controller.datacontroller;

public class WaktuGeneralException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "";
	
	WaktuGeneralException() {
		
	}
<<<<<<< HEAD
	
	public WaktuGeneralException(String message) {
=======
	
	public WaktuGeneralException(String message) {
>>>>>>> 590efe9fae3d6ec5103b6030d9bddcb845a7f01b
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
