package com.kerux.utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DBUtility {

	//db configuration
	
	String jdbcDriverName = "QW3ELt19wnX4IncON0pytPYjJQ7R7KdcfgTP7pypuKI";
//	String jdbcUrl ="M0E51/D2unw4mOhSexTFX5FvE+LXhXhYKNoMKX5C8MU=";
//	String dbUserName = "werHkYZovpqBYC9AAVuTVw==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
//	String jdbcUrl ="M0E51/D2unw4mOhSexTFXyVV79oWM28wAud3mew4ZQsDpY35bHMkW7b2OpMI5uk9";
//	String dbUserName = "/VBnm0MGE6NC76s0burwRg==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	
			
	String jdbcUrl ="zp/kWu5b+tDu/jLr/ghDf2veMxqv/JQmMwuTl0iQsMSemYdqNwSEu1ta4QN1N/8M";
	String dbUserName = "Ehjf9dlWTbKya8EjojsH1A==";
	String dbPassword = "c67l0Ac10jF3rdfZru2uhe9fW0wb6YVnsEYgogo9w/w=";

//	String jdbcUrl = "YWFbYV3werqVEzyC9EQ/FlZdEPtjnBhY/157uUySoTZR4bll5A1IpFxaEfvy+E5r";
//	String dbUserName = "WfarJAFhTQFDz1rIksQ+JA==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	//SQL Statements
		String INSERT_ADDRESS = "insert into address ("
			+ "Street, Barangay, City, Province)"
			+ " values(?,?,?,?)";	
		
		String INSERT_CLINIC = "insert into clinic ("
				+ "clinicName, Address_ID, clinicDays, clinicHours, services, contactNo, status)"
				+ "values(?,?,?,?,?,?,?)";	
		String INSERT_CLINIC_ENROLLMENT = "insert into clinic_enrollment (clinic_id, rootadmin_id, status) values (?, ?, ?)";
		String INSERT_ADMIN = "insert into admin ("
				+ "firstname, lastname, email, password, accesslevel_id)"//accesslevel_id
				+ "values(?,?,?,?,?)";
		String INSERT_ADMIN_ENROLLMENT = "insert into admin_enrollment (clinic_id, admin_id, rootadmin_id, status) values (?, ?, ?, ?)"; //admin_enrollment
		String INSERT_ROOT = "insert into root_administrator ("
				+ "firstname, lastname, email, password, accesslevel_id)" //accesslevel_id
				+ "values(?,?,?,?,?)";
		
		
		String LIST_ALL_RECORDS = "select * from clinic where status = 'Active'";
		String LIST_ALL_CLINIC_RECORDS = "select * from clinic";
		String LIST_ALL_ADMIN_RECORDS = "select * from admin inner join admin_enrollment on admin.Admin_ID = admin_enrollment.Admin_ID WHERE admin_enrollment.Status='Active'"; //admin_enrollment
		String LIST_ALL_SERVICES = "select service from clinic_services"; //clinic_services
		String LIST_ALL_CLINIC = "select clinicName from clinic  where status = 'Active'";
		String LIST_ALL_ADMIN = "select AdminName from admin";
		
		String VALIDATION_CLINIC = "Select clinicName from clinic where clinicName = ?";
		String VALIDATION_ADMIN = "Select email from admin where email = ?";
		String VALIDATION_ROOT = "Select email from root_administrator where email = ?";
		
		String LOGIN_CRED = "Select * from root_administrator where Email = ? and Password = ?";
		
		String DELETE_ADMIN = "Delete from admin where admin_id = ?";
		String DELETE_CLINIC = "update clinic set status = ? where clinic_id = ?";
		
		String UPDATE_ADMIN_ENROLLMENT="update admin_enrollment set status = ? where admin_id = ?"; //admin_enrollment
		String UPDATE_CLINIC = "update clinic set status = ? where clinic_id = ?";
		
		String GET_NEW_CLINIC_ID = "Select MAX(clinic_id) from clinic";
		String GET_NEW_ADMIN_ID = "Select MAX(admin_id) from admin";
		String GET_NEW_ADDRESS_ID = "Select MAX(address_id) from address";
		
	    String INSERT_STAT = "INSERT INTO statistics (QueuesServed, QueuesCancelled, HighestDocQueues, HighestDeptQueues, TimeStart, TimeEnd) SELECT " +
	            "(SELECT COUNT(ql.QueueList_ID) FROM queuelist ql INNER JOIN queue q on q.Queue_ID = ql.Queue_ID INNER JOIN queueconnector qc on qc.Queue_ID = q.Queue_ID INNER JOIN queuemanager qm on qm.QueueManager_ID = qc.QueueManager_ID WHERE  ql.Status='Served'), " +
	            "(SELECT COUNT(ql.QueueList_ID) FROM queuelist ql INNER JOIN queue q on q.Queue_ID = ql.Queue_ID INNER JOIN queueconnector qc on qc.Queue_ID = q.Queue_ID INNER JOIN queuemanager qm on qm.QueueManager_ID = qc.QueueManager_ID WHERE  ql.Status='Cancelled'), " +
	            "(SELECT CONCAT(Doctor.FirstName, Doctor.LastName) FROM DOCTOR INNER JOIN queue on queue.Doctor_ID = doctor.Doctor_ID WHERE queue.Queue_ID = (SELECT z.queueid FROM (SELECT w.Queue_ID as queueid, MAX(w.num) FROM (SELECT Queue_ID as Queue_ID, count(*) as num FROM instance GROUP BY Queue_ID) w) z)), " +
	            "(SELECT Department.Name FROM department INNER JOIN queue on queue.Department_ID = department.Department_ID WHERE queue.Queue_ID = (SELECT x.queueid FROM (SELECT y.Queue_ID as queueid, MAX(y.num) FROM (SELECT Queue_ID as Queue_ID, count(*) as num FROM instance GROUP BY Queue_ID) y) x)), CURRENT_TIME, CURRENT_TIME";
	    String SELECT_STAT="SELECT QueuesServed, QueuesCancelled, HighestDocQueues, HighestDeptQueues, TimeStart, TimeEnd from statistics ORDER BY statistics_id desc limit 1";
	    
	    String SELECT_AUDIT_LIST = "SELECT TableName, Event, TimeStamp FROM audit_log";
	    String SELECT_AUDIT = "SELECT Log_ID, TableName, Event, SqlCommand, OldData, NewData, LoginName, TimeStamp from audit_log ORDER BY Log_ID DESC";
	    String INSERT_AUDIT_LOG = "INSERT INTO audit_log (TableName, Event, SqlCommand, OldData, NewData, LoginName)" +
	            "values (?,?,?,?,?,?)";
	    String SELECT_ADMIN_ENROLLMENT="select * from admin_enrollment where admin_id=?";
	    
	    String SELECT_ALEVEL = "SELECT * FROM access_level";
	    
	    String SELECT_ADMIN="SELECT FirstName, LastName, Email FROM admin WHERE admin_id=?";
	    
	    String SELECT_ROOTADMINNAME="SELECT FirstName, LastName FROM root_administrator WHERE RootAdmin_ID=?";
	    String SELECT_RATING="SELECT rating.rating_id, rating.rating, patient.FirstName, patient.LastName, queue.QueueName, rating.TIMESTAMP from rating INNER JOIN patient ON patient.Patient_ID = rating.Patient_ID INNER JOIN instance ON instance.Instance_ID = rating.Instance_ID INNER JOIN queue ON instance.Queue_ID = queue.Queue_ID ORDER BY rating.TIMESTAMP DESC";
		String SELECT_AVE_RATING="Select AVG(rating) from rating";
/*	  Clinic, Queues Served, Queues Cancelled Highest Department Queues, Highest Doctor Queues

Maybe 
No. of patients per queue
Average Time of Turnover (per clinic?)
  
	    
	    
	*/    
}
