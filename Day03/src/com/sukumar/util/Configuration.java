//$Id$
package com.sukumar.util;

public class Configuration {
	public static final String DB_USER = "sukumar";
	public static final String DB_PASSWORD = "mysql@123";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/first_db";
	public static final String REDIS_ADDRESS = "localhost";
	public static final Integer REDIS_PORT = 6379;
	public static final String CHECKIN_QUERY="INSERT INTO attendance(emp_id,check_in) VALUES(?,?);";
	public static final String CHECKOUT_QUERY="UPDATE attendance SET check_out = ? WHERE emp_id = ?;";
	public static final String MARK_WORKING_HOURS_QUERY="UPDATE attendance SET working_duration = ? WHERE emp_id = ?;";
	public static final String GET_EMPLOYEE = "SELECT * FROM employee WHERE emp_id = ?;";

}
