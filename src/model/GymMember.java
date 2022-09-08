package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tony Ehlert - aehlert
 * CIS175 - Fall 2022
 * Sep 7, 2022
 */

@Entity
@Table(name = "member_info")
public class GymMember {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column (name = "LAST_NAME")
	private String lastName;
	
	@Column (name = "AGE")
	private int age;
	
	@Column (name = "WAIVER_SIGNED")
	private boolean waiverSigned = false; // false is default value for any new GymMember object.
	
	// default no arg constructor
	public GymMember() {
		super();
	}
	
	public GymMember (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public GymMember(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	// getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isWaiverSigned() {
		return waiverSigned;
	}

	public void setWaiverSigned(boolean waiverSigned) {
		this.waiverSigned = waiverSigned;
	}

	// helper method to easily view member info
	public String returnMemberInfo() {
		String waiverStatus;
		if (this.waiverSigned == true) {
			waiverStatus = "Yes";
		} else {
			waiverStatus = "No";
		}
		return "Member ID: " + this.id + ", Name: "+ this.firstName + " " + this.lastName + ", Age: " + this.age + ", Waiver Signed: " + waiverStatus;
	}
	
	// toString method
	@Override
	public String toString() {
		return "GymMember [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", waiverSigned=" + waiverSigned + "]";
	}
	
	
}
