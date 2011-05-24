package ch.hsr.waktu.domain;

public enum SystemRole {
//	ADMIN, PROJECTMANAGER, EMPLOYEE
	ADMIN(0), PROJECTMANAGER(1), EMPLOYEE(2);
	
	private int index;
	
	SystemRole(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String toString() {
		if(this.getIndex() == 0) {
			return "Admin";
		}
		return "Hallo";
	}
}
