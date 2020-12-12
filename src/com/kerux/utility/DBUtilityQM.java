package com.kerux.utility;


public interface DBUtilityQM {

	String jdbcDriverName = "QW3ELt19wnX4IncON0pytPYjJQ7R7KdcfgTP7pypuKI";

//	String jdbcUrl ="M0E51/D2unw4mOhSexTFX5FvE+LXhXhYKNoMKX5C8MU=";
//	String dbUserName = "werHkYZovpqBYC9AAVuTVw==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	String jdbcUrl ="zp/kWu5b+tDu/jLr/ghDf2veMxqv/JQmMwuTl0iQsMSemYdqNwSEu1ta4QN1N/8M";
	String dbUserName = "Ehjf9dlWTbKya8EjojsH1A==";
	String dbPassword = "c67l0Ac10jF3rdfZru2uhe9fW0wb6YVnsEYgogo9w/w=";
//	String jdbcUrl = "YWFbYV3werqVEzyC9EQ/FlZdEPtjnBhY/157uUySoTZR4bll5A1IpFxaEfvy+E5r";
//	String dbUserName = "WfarJAFhTQFDz1rIksQ+JA==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	
	String LOGIN_CRED="select queuemanager_id, clinic_id, firstname, lastname, email from queuemanager where email=? and password =?";

    String SELECT_DEPARTMENT_LIST="SELECT Department_ID, Name from department WHERE Status='Active' and clinic_id=? ";
    String SELECT_DOCTOR_LIST="SELECT * from doctor WHERE Status='Active' and clinic_id=? "; //WHERE DEPARTMENT ID IS ALSO INCLUDED; NOT YET COMPLETE
    String SELECT_PATIENT_LIST="SELECT i.instance_id, i.patient_id, i.queuetype_id, i.timestamp, i.status, i.priority, i.queuenumber from instance i inner join queuelist ql on ql.instance_id=i.instance_id where ql.queue_id = ? AND ql.status!='Served'";
    String SELECT_QUEUE="SELECT Queue_ID from queue WHERE Doctor_ID=? and Department_ID=? and Status='Active'";
    String SELECT_ACTIVE_QUEUE="SELECT queue.Queue_ID, queue.QueueName from queue INNER JOIN queueconnector ON queueconnector.Queue_ID = queue.Queue_ID WHERE queueconnector.QueueManager_ID=? and queue.Status='Active'";
    String SELECT_ACTIVE_QUEUE_NUMBER="SELECT COUNT(Queue_ID) from queuelist WHERE Status='Active' and Queue_ID=?";
    String SELECT_REPORT_QUEUE_NUMBER="SELECT COUNT(Queue_ID) from queuelist WHERE Queue_ID=?";
    String SELECT_REPORT_QUEUE_LIST="SELECT i.queuenumber, i.status from instance i inner join queuelist ql on ql.instance_id=i.instance_id where ql.queue_id = ?";
    String SELECT_REPORT_QUEUE_CANCELLED="SELECT COUNT(i.queuenumber) from instance i inner join queuelist ql on ql.instance_id=i.instance_id where ql.queue_id = ? and ql.status='Cancelled'";
    String SELECT_REPORT_QUEUE_SERVED="SELECT COUNT(i.queuenumber) from instance i inner join queuelist ql on ql.instance_id=i.instance_id where ql.queue_id = ? and ql.status='Served'";

    String GET_NEW_QUEUE_ID="SELECT MAX(queue_id) from queue";

    String INSERT_INTO_QUEUE="INSERT INTO queue (QueueName, TimeStamp, Doctor_ID, Department_ID, Status) values (?, ?, ?, ?, 'Active')";
    String INSERT_INTO_QUEUE_CONNECTOR="INSERT INTO queueconnector (Queue_ID, QueueManager_ID) values (?, ?)";

    String UPDATE_MARK_PATIENT_AS_SERVED="UPDATE instance set status = 'Served' where instance_id= ?";
    String UPDATE_MARK_PATIENT_AS_NO_SHOW="UPDATE instance set status = 'NoShow' where instance_id= ?";
    String UPDATE_QUEUELIST_AS_SERVED="UPDATE queuelist set status = 'Served' where instance_id= ? and queue_id=?";
    String UPDATE_QUEUELIST_AS_NO_SHOW="UPDATE queuelist set status = 'NoShow' where instance_id= ? and queue_id=?";
    String UPDATE_MARK_PATIENT_AS_CALLED="UPDATE instance set status = 'Called' where instance_id= ?";
    String UPDATE_QUEUELIST_AS_CALLED="UPDATE queuelist set status = 'Called' where instance_id= ? and queue_id=?";
    String UPDATE_QUEUE_ENDQUEUE="UPDATE queue set EndTime = CURRENT_TIMESTAMP, Status='Closed' where queue_id=?";
    String UPDATE_QUEUE_CANCELQUEUE="UPDATE queue set EndTime = CURRENT_TIMESTAMP, Status='Cancelled' where queue_id=?";
    String UPDATE_QUEUELIST_AS_CANCELLED="UPDATE queuelist set status = 'Cancelled' where queue_id=? AND status='Active'";
    String UPDATE_INSTANCE_AS_CANCELLED="UPDATE instance set status = 'Cancelled' where queue_id=? AND status='Active'";
    
    String UPDATE_PROFILE="UPDATE queuemanager SET FirstName = ?, LastName = ?, Email = ?, Username = ? WHERE QueueManager_ID = ?";

    //sql statement for edit profile to not require the input of password when just editing basic patient information
    String UPDATE_QM_PASS = "update queuemanager set password = ? where QueueManager_ID=?";

    //sql statement to compare the "old password" the patient has inputted in the text field from the one in the database
    String CONFIRM_QM_PASS = "select password from queuemanager where QueueManager_ID = ?";
    
	String GET_SCHEDULE="SELECT Schedule1, Schedule2 from doctor where doctor_id=?";
}
