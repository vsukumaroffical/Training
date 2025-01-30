//$Id$
package com.sukumar.view;

import java.util.Scanner;

import com.sukumar.controller.CheckInOutController;
import com.sukumar.dto.Employee;
import com.sukumar.dto.EmployeeBuilder;

public class OfficeLogView {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		while(true) {
			
			System.out.println("1. Check-in");
			System.out.println("2. CHeck-out");
			System.out.println("3. Exit");
			
			byte choice = scanner.nextByte();
			scanner.nextLine();
			
			if(choice == 1) {
				new CheckInOutController().setCheckIn(getEmployeeInfo());
			}else if(choice == 2){
				new CheckInOutController().setCheckOut(getEmployeeInfo());
			}else if(choice == 3) {
				System.exit(1);
			}
		}
		
	}

	private static Employee getEmployeeInfo() {
		System.out.println("Enter the emp id");
		String id = scanner.nextLine();
		System.out.println("Enter the emp name");
		String name = scanner.nextLine();

		return new EmployeeBuilder().setEmployeeId(id).setEmployeeName(name).getEmployee();
	}
}
