package ch.hsr.waktu.domain;

import ch.hsr.waktu.services.Md5;

public class Usr {

	private int id;
	private String username;
	private String name;
	private String firstname;
	private String password;
	private int pensum;
	private SystemRole systemRole = SystemRole.EMPLOYEE;
	private double holiday;
	private boolean inactive = false;
	

	public Usr() {
		
	}
	
	public Usr(String username, String firstname, String lastname,
			String password, int pensum, SystemRole role, double holiday) {
		this.username = username;
		this.name = lastname;
		this.firstname = firstname;
		this.password = Md5.hash(password);
		this.pensum = pensum;
		this.systemRole = role;
		this.holiday = holiday;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Md5.hash(password);
	}

	public int getPensum() {
		return pensum;
	}

	public SystemRole getRole() {
		return systemRole;
	}

	public void setRole(SystemRole role) {
		this.systemRole = role;
	}

	public double getHoliday() {
		return holiday;
	}

	public void setHoliday(double holiday) {
		this.holiday = holiday;
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
	
	public boolean isInactive() {
		return inactive;
	}
	
	public boolean isActive() {
		return !inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public void setPensum(int pensum) {
			this.pensum = pensum;		
	}
	
	public SystemRole getSystemRole() {
		return systemRole;
	}
	
	public void setSystemRole(SystemRole systemRole) {
			this.systemRole = systemRole;
	}
	
	public void setInactive() {
		this.inactive = true;
	}
	
	public void setActive() {
		this.inactive = false;
	}
	
	@Override
	public String toString() {
		return firstname + " " + name;
	}

}
