package com.angular.rest.test.model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	int id;
	String name;
	float sal;
	String uname;
	String upass;
	
	public Employee() {}
	public Employee(int id, String name, float sal, String uname, String upass) {
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.uname = uname;
		this.upass = upass;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sal=" + sal + ", uname=" + uname + ", upass=" + upass + "]";
	}
	
}