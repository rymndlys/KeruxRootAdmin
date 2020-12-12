package com.kerux.utility;

public interface DBUtilitySpinner {
	

		String jdbcDriverName = "QW3ELt19wnX4IncON0pytPYjJQ7R7KdcfgTP7pypuKI";

//		String jdbcUrl ="M0E51/D2unw4mOhSexTFX+HA+SqRfEUa2idWoReGfP4=";
//		String dbUserName = "werHkYZovpqBYC9AAVuTVw==";
//		String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
//		String jdbcUrl ="M0E51/D2unw4mOhSexTFXyLJ1y2heOxMbWABpPTB2KI=";
//		String dbUserName = "ITh/vKPd856YlAUMyuuNVQ==";
//		String dbPassword = "NyiG//i7loPFfdUoO9BFuA==";
		String jdbcUrl ="zp/kWu5b+tDu/jLr/ghDf2veMxqv/JQmMwuTl0iQsMSemYdqNwSEu1ta4QN1N/8M";
		String dbUserName = "Ehjf9dlWTbKya8EjojsH1A==";
		String dbPassword = "c67l0Ac10jF3rdfZru2uhe9fW0wb6YVnsEYgogo9w/w=";
//		String jdbcUrl = "YWFbYV3werqVEzyC9EQ/FlZdEPtjnBhY/157uUySoTZR4bll5A1IpFxaEfvy+E5r";
//		String dbUserName = "WfarJAFhTQFDz1rIksQ+JA==";
//		String dbPassword = "aliv/jZ2ur6kYXLP+CeJFQ==";
		
		String clinicSpinner="SELECT * FROM clinic where status='Active'";
		String departmentSpinner="SELECT * FROM department WHERE Status ='Active'";
		String doctorSpinner="SELECT * FROM doctor";
		
		String doctorTypeSpinner="SELECT * FROM doctor_type ";
		String patientSpinner="SELECT * FROM department";
		String reasonRevokeSpinner="SELECT * FROM reason_revoke";
		
		String reasonSpinnerDept="SELECT * FROM reason_revoke WHERE TableName ='Department'";
		String reasonSpinnerDoctor="SELECT * FROM reason_revoke WHERE TableName = 'Doctor'";
		String reasonSpinnerPatient="SELECT * FROM reason_revoke WHERE TableName = 'User Patient'";
		String reasonSpinnerQM="SELECT * FROM reason_revoke WHERE TableName = 'QueueManager'";
		
	}








