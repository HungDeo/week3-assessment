package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author DeoTheHung -The Deo
 * CIS175 Spring 2022
 * Feb 3, 2022
 */

@Entity
@Table(name="student") 
public class Student {
	@Id
	@GeneratedValue
	@Column(name="studentID")
	private int studentID;
	@Column(name="lastName")
	private String lastName;
	@Column(name="firstName")
	private String firstName;
	public Student() {
		super();
	}
	
	public Student(String lastName, String firstName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String fullName() {
		return this.firstName + " " + this.lastName;
	}
}
