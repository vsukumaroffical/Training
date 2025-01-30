package com.sukumar.timestamp;

import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/getTimeStamp")
public class TimeStampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called");
		LocalDateTime now = LocalDateTime.now();
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(formatter));
        
        response.setContentType("application/json");
        
        JSONObject obj = new JSONObject();
        PrintWriter writer = response.getWriter();
        
        obj.put("timestamp",now.format(formatter));
        writer.write(obj.toString());
       

	}

}
