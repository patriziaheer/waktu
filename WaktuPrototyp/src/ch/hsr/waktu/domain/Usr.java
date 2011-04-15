package ch.hsr.waktu.domain;

import ch.hsr.waktu.services.Md5;

public class Usr {

	private int id;
	private String username;
	private String name;
	private String firstname;
	private String password;
	private int pensum;
	private int role;
	private double holiday;
	private boolean inactive;

	public Usr() {
		
	}
	
	public Usr(String username, String firstname, String lastname,
			String password, int pensum, int role, double holiday) {
		this.username = username;
		this.name = lastname;
		this.firstname = firstname;
		this.password = password;
		this.pensum = pensum;
		this.role = role;
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

	public void setPensum(int pensum) {
		this.pensum = pensum;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	@Override
	public String toString() {
		return firstname + " " + name;
	}

}
