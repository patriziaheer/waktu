package ch.hsr.waktu.domain;

import ch.hsr.waktu.services.Md5;

public class Usr {

	private int usrid;
	private String username;
	private String name;
	private String firstname;
	private String passwordHash;
	private int pensum;
	private SystemRole systemRole = SystemRole.EMPLOYEE;
	private double holiday;
	private boolean active = true;
	

	public Usr() {
		
	}
	
	public Usr(String username, String firstname, String lastname,
			String password, int pensum, SystemRole role, double holiday) {
		this.username = username;
		this.name = lastname;
		this.firstname = firstname;
		this.passwordHash = password;

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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPassword(String password) {
		this.passwordHash = Md5.hash(password);
	}

	public int getPensum() {
		return pensum;
	}

	public double getHoliday() {
		return holiday;
	}

	public void setHoliday(double holiday) {
		this.holiday = holiday;
	}

	public int getId() {
		return usrid;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActiveState(boolean toActive) {
		this.active = toActive;
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
	
	@Override
	public String toString() {
		return usrid + firstname + " " + name + " " + username;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usr) {
			Usr usr = (Usr)obj;
			if (usr.username.equals(username) && usr.usrid == usrid && 
					usr.firstname.equals(firstname) && usr.name.equals(name) && 
					usr.holiday == holiday && usr.active == active && 
					usr.passwordHash.equals(passwordHash) && usr.pensum == pensum && 
					usr.systemRole.equals(systemRole)) {
				return true;
			}
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode += 31 * usrid;
		hashCode += 31 * username.hashCode();
		hashCode += 31 * name.hashCode();
		hashCode += 31 * firstname.hashCode();
		hashCode += 31 * passwordHash.hashCode();
		hashCode += 31 * pensum;
		hashCode += 31 * systemRole.hashCode();
		hashCode += 31 * (int)holiday;
		return hashCode;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
