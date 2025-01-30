//$Id$
package com.sukumar.dto;

public class Employee {
	private String employeeId;
	private String employeeName;

	public Employee() {

	}

	public Employee(String employeeId, String employeeName) {

		this.employeeId = employeeId;
		this.employeeName = employeeName;

	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

}
