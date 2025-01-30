//$Id$
package com.sukumar.dto;

import java.time.LocalDateTime;

public class EmployeeBuilder{
	
	private String employeeId;
	private String employeeName;
	
	public EmployeeBuilder setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		return this;
	}
	public EmployeeBuilder setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
		return this;
	}
	
	public Employee getEmployee() {
		return new Employee(employeeId,employeeName);
	}
	
}
