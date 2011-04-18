package ch.hsr.waktu.domain;

public enum SystemRole {
	Admin(0), ProjectManager(1), Employee(2);
	
	private int index;
	
	SystemRole(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
}
