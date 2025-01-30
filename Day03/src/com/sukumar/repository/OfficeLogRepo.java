package com.sukumar.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sukumar.util.Configuration;
import com.sukumar.util.DBConnection;
import com.sukumar.dto.*;

public class OfficeLogRepo {

	private static OfficeLogRepo instance;
	private Connection connection;

	private OfficeLogRepo() throws SQLException {
		connection = DBConnection.getInstance().getConnection();
	}

	public static OfficeLogRepo getInstance() throws SQLException {
		if (instance == null)
			instance = new OfficeLogRepo();
		return instance;
	}

	public boolean checkIn(String empId, String timeStamp) throws SQLException {

		PreparedStatement statement = connection.prepareStatement(Configuration.CHECKIN_QUERY);
		statement.setString(1, empId);
		statement.setString(2, timeStamp);
		int result = statement.executeUpdate();
		return result > 0;
	}

	public boolean checkOut(String empId, String timeStamp) throws SQLException {

		PreparedStatement statement = connection.prepareStatement(Configuration.CHECKOUT_QUERY);
		statement.setString(2, empId);
		statement.setString(1, timeStamp);
		int result = statement.executeUpdate();
		return result > 0;
	}

	public boolean markWorkingHours(String empId, String workingHours) throws SQLException {

		PreparedStatement statement = connection.prepareStatement(Configuration.MARK_WORKING_HOURS_QUERY);
		statement.setString(2, empId);
		statement.setString(1, workingHours);
		int result = statement.executeUpdate();
		return result > 0;
	}

	public Employee getEmployee(String empId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(Configuration.GET_EMPLOYEE);
		statement.setString(1, empId);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			String id = result.getString("emp_id");
			String name = result.getString("emp_name");
			return new EmployeeBuilder().setEmployeeId(id).setEmployeeName(name).getEmployee();
		}
		return null;
	}

}
