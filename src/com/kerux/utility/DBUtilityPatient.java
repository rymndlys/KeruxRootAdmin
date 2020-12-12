package com.kerux.utility;

public interface DBUtilityPatient {

	String jdbcDriverName = "QW3ELt19wnX4IncON0pytPYjJQ7R7KdcfgTP7pypuKI";

//	String jdbcUrl ="M0E51/D2unw4mOhSexTFX+HA+SqRfEUa2idWoReGfP4=";
//	String dbUserName = "werHkYZovpqBYC9AAVuTVw==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	String jdbcUrl ="zp/kWu5b+tDu/jLr/ghDf2veMxqv/JQmMwuTl0iQsMSemYdqNwSEu1ta4QN1N/8M";
	String dbUserName = "Ehjf9dlWTbKya8EjojsH1A==";
	String dbPassword = "c67l0Ac10jF3rdfZru2uhe9fW0wb6YVnsEYgogo9w/w=";
//	String jdbcUrl = "YWFbYV3werqVEzyC9EQ/FlZdEPtjnBhY/157uUySoTZR4bll5A1IpFxaEfvy+E5r";
//	String dbUserName = "WfarJAFhTQFDz1rIksQ+JA==";
//	String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
	
	  //login
    String LOGIN_PATIENT = "select patient.patient_id, patient.contactno, patient.password, patient.email, patient.firstname, patient.lastname, patient_type.PatientType_ID from patient inner join patient_type on patient.PatientType_ID = patient_type.PatientType_ID where contactno = ? and password = ?";

    //register patient
    String REGISTER_PATIENT = "insert into patient (email, password, patienttype_id, firstname, lastname, contactno, status) " +
            "values(?, ?, ?, ?, ?, ?, 'Active')";
    
    //insert guest
    String REGISTER_GUEST ="INSERT INTO guest( Guest_No, FirstName, LastName, ContactNo) VALUES ('','','','')";
    String SELECT_NEW_GUEST = "Select MAX(guest_id) from guest";

    //check for existing account
    String CHECK_PATIENT = "select contactno, email from patient where contactno = ? or email = ?";

    //queueing
    String SELECT_QUEUE="select queue.queue_id from queue inner join department " +
            "on department.department_id = queue.department_id inner join doctor " +
            "on doctor.doctor_id = queue.doctor_id where department.name = ? and " +
            "CONCAT(doctor.firstname, ' ', doctor.lastname) = ? and queue.status != 'Served'";

    //updated queueing
    String QUEUE_PATIENT = "insert into instance (patient_id, queue_id, queuetype_id, status, priority) " +
            "values( ? , ? , ? , ?, ?)";
    String SELECT_NEW_INSTANCE = "Select MAX(instance_id) from instance";
    String SELECT_COUNT_QUEUELIST = "Select COUNT(queue_id) from queuelist where queue_id=? and status='Active'";
    String INSERT_QUEUE_LIST = "insert into queuelist (queue_id, instance_id, status) values(?, ?, 'Active')";
    String UPDATE_QUEUE_NUMBER = "update instance set queuenumber = ? where instance_id= ?";
    String SELECT_CLINIC="select clinic_id, clinicname, clinichours, clinicdays, status from clinic where status='Active'";
    String VIEW_QUEUE = "";

    String VIEW_PATIENT_QUEUE = "select queuenumber from instance where patient_id = ?";


    //IN THE VIEW PAGE TICKET
    String SELECT_QUEUENUMBER="SELECT queuenumber from instance where instance_id=?";
    String SELECT_CURRENTLY_CALLING="select instance.queuenumber from instance inner join queuelist " +
            "on queuelist.instance_id = instance.instance_id inner join queue " +
            "on queue.queue_id = queuelist.queue_id where queue.queue_id = ? and " +
            "queuelist.status = 'Called'";

    //to retrieve info for edit profile
    String EDIT_PROFILE="select email, password, patienttype_id, name, contactno from patient" +
            "where patient_id = ?";

    //update profile string
    String UPDATE_PROFILE="update patient set email = ?, firstname = ?, lastname = ?, contactno = ? where patient_id =  ?";

    //sql statement for edit profile to not require the input of password when just editing basic patient information
    String UPDATE_PROFILE_PASS = "update patient set password = ? where patient_id=?";

    //sql statement to compare the "old password" the patient has inputted in the text field from the one in the database
    String CONFIRM_PATIENT_PASS = "select password from patient where patient_id = ?";
    
    
    //RATING
    String INSERT_RATING="insert into rating (rating, patient_id, instance_id, timestamp) values(?,?,?,CURRENT_TIMESTAMP)";
    
    String SELECT_PHOTO="Select photo from doctor where firstname=? and lastname=?";
}
