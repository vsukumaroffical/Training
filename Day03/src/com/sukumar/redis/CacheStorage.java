//$Id$
package com.sukumar.redis;

import java.util.Set;

import com.sukumar.dto.Employee;
import com.sukumar.util.Configuration;

import redis.clients.jedis.Jedis;

public class CacheStorage {
	private static CacheStorage instance = null;
	private Jedis storage = new Jedis(Configuration.REDIS_ADDRESS, Configuration.REDIS_PORT);;

	private CacheStorage() {

	}

	public static CacheStorage getInstance() {
		if (instance == null)
			instance = new CacheStorage();
		return instance;
	}

	public boolean checkIn(String empId, String timeStamp) {
		storage.set(empId + " check-in", timeStamp);
		return true;
	}

	public boolean checkOut(String empId, String timeStamp) {
		storage.set(empId + " check-out", timeStamp);
		return true;
	}

	public String getCheckinTime(String empId) {
		return storage.get(empId + " check-in");

	}
	

	public String getEmployeeName(String empId) {
		return storage.get(empId);
	}

	public void setEmployee(Employee emp) {
		storage.set(emp.getEmployeeId(), emp.getEmployeeName());
	}

	public boolean isCheckIn(String empId) {
		Set<String> keys = storage.keys(empId + " check-in");
		return keys.contains(empId + " check-in");
	}

	public boolean isCheckOut(String empId) {
		Set<String> keys = storage.keys(empId + " check-out");
		return keys.contains(empId + " check-out");
		
	}

	public void deleteEmployeeCheckInOut(String employeeId) {
		storage.del(employeeId+" check-in");
		storage.del(employeeId+" check-out");
		
	}

}
