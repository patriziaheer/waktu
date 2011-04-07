package ch.hsr.waktu.domain;


public class Usr {

	private int id;
	private String name;
	private String firstname;
	
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
	
	@Override
	public String toString() {
		return name + " " + firstname;
	}

}
