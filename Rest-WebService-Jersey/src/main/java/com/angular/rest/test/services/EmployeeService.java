package com.angular.rest.test.services;

import java.util.List;

import com.angular.rest.test.database.EmployeeDao;
import com.angular.rest.test.model.Employee;

public class EmployeeService {
	
	EmployeeDao ed=new EmployeeDao();
	
	public List<Employee> getAllEmployees() {
		System.out.println("Employee Service");
		return ed.getAllEmployees();
	}
	
	public Employee getEmployee(int eNo) {
		return ed.getEmployee(eNo);
	}
	
	public boolean addEmployee(Employee employee) {
		return ed.addEmployee(employee);
	}
	
	public boolean updateEmployee(Employee employee) {
		return ed.updateEmployee(employee);
	}
	
	public boolean deleteEmployee(int eNo) {
		return ed.deleteEmployee(eNo);
	}
	
	public Employee checkCredentials(String uname) {
		return ed.checkCredentials(uname);
	}

}
