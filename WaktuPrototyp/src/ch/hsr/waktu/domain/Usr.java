package ch.hsr.waktu.domain;


public class Usr {

	private int id;
	private String name;
	private String firstname;
	private String username;
	private boolean inactive = false;
	private int pensum;	
	private int systemRole;
	
	public Usr() {
		
	}

	public Usr(String name, String firstname) {
		this.name = name;
		this.firstname = firstname;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public int getPensum() {
		return pensum;
	}
	
	public void setPensum(int pensum) throws InvalidInputException {
		if(pensum > 0 && pensum <= 100) {
			this.pensum = pensum;
		} else {
			throw new InvalidInputException("Pensum muss gršsser 0%, maximal 100% sein");
		}		
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public int getSystemRole() {
		return systemRole;
	}
	
	public void setSystemRole(int systemRole) throws InvalidInputException {
		if(systemRole > 0 && systemRole <= 2) {
			this.systemRole = systemRole;
		} else {
			throw new InvalidInputException("Systemrollen: 0=Admin, 1=Projektleiter, 2=Projektmitarbeiter");
		}
		
	}
	
	public boolean isInactive() {
		return inactive;
	}
	
	public void setInactive() {
		this.inactive = true;
	}
	
	public void setActive() {
		this.inactive = false;
	}
	
	@Override
	public String toString() {
		return name + " " + firstname;
	}

}
