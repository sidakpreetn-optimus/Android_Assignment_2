package com.example.employeeinfoapp;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 0L;
	
	private String name;
	private String designation;
	private int code;
	private byte[] picture;
	private String tagLine;
	private String department;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	
}
