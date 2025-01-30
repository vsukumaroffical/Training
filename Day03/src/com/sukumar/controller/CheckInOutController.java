//$Id$
package com.sukumar.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sukumar.dto.Employee;
import com.sukumar.redis.CacheStorage;
import com.sukumar.repository.OfficeLogRepo;

public class CheckInOutController {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void setCheckIn(Employee employee) {
		CacheStorage cache = CacheStorage.getInstance();
		String currentTime = currentTime();
		String name = cache.getEmployeeName(employee.getEmployeeId());

		if (name == null || !name.equals(employee.getEmployeeName())) {
			try {
				Employee temp = OfficeLogRepo.getInstance().getEmployee(employee.getEmployeeId());
				CacheStorage.getInstance().setEmployee(temp);
				System.out.println(employee.getEmployeeId() + " now available cache memory");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("employee available cache memory");
			if (cache.isCheckIn(employee.getEmployeeId())) {
				System.out.println("this employee already check in");
				return;
			}
		}
		
		try {
			if (OfficeLogRepo.getInstance().checkIn(employee.getEmployeeId(), currentTime)) {
				System.out.println("check in status updated db");
				if (CacheStorage.getInstance().checkIn(employee.getEmployeeId(), currentTime)) {
					System.out.println("check in status updates cache memory");
				}
			} else {
				System.out.println("not updated db please check valid data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCheckOut(Employee employee) {
		
		String currentTime = currentTime();
		CacheStorage cacheMemory = CacheStorage.getInstance();
		OfficeLogRepo repository = null;
		
		if(cacheMemory.isCheckOut(employee.getEmployeeId())) {
			System.out.println("ALready check-out");
			return;
		}
		if (!cacheMemory.isCheckIn(employee.getEmployeeId())) {
			System.out.println("Please check in first");
			return;
		}
		
		try {
			repository = OfficeLogRepo.getInstance();
			if (repository.checkOut(employee.getEmployeeId(), currentTime)) {
				System.out.println("check out uptaded db");
				if (cacheMemory.checkOut(employee.getEmployeeId(), currentTime)) {
					System.out.println("check out updated cache memory");
				}
				if (repository.markWorkingHours(employee.getEmployeeId(), calculateWorkingHours(CacheStorage.getInstance().getCheckinTime(employee.getEmployeeId()), currentTime))) {
					System.out.println("updated working hours");
					cacheMemory.deleteEmployeeCheckInOut(employee.getEmployeeId());
				}

			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}


	}

	private String currentTime() {

		return dateFormat.format(new Date());
	}

	private String calculateWorkingHours(String checkinTime, String checkoutTime) {

		Long checkInTimestamp = null;
		Long checkOutTimestamp = null;
		try {
			checkInTimestamp = dateFormat.parse(checkinTime).getTime();
			checkOutTimestamp = dateFormat.parse(checkoutTime).getTime();
			long workSeconds = (checkOutTimestamp - checkInTimestamp) / 1000;

			int hours = (int) (workSeconds / 3600);
			int minutes = (int) ((workSeconds % 3600) / 60);
			int seconds = (int) (workSeconds % 60);

			String workDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			return workDuration;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
