package com.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="student")
public class Student {
	
	public Student(int id, String firstName, String secondName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email=email;
		
	}
	
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@JsonProperty("first_Name")
	private String firstName;
	
	@JsonProperty("last_Name")
	private String secondName;
	
	@Column(length=200)
	private String email;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email
				+ "]";
	}
}
