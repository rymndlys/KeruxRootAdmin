package com.kerux.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kerux.security.Security;
import com.kerux.utility.DBUtility;
import com.kerux.utility.Mailer;
import com.kerux.bean.ClinicBean;
import com.kerux.bean.RatingBean;
import com.kerux.bean.StatBean;
import com.kerux.bean.AccessLevelBean;
import com.kerux.bean.AdminBean;
import com.kerux.bean.AuditBean;


public class MainControllerDB implements DBUtility {
	private static Map<String, Object> session;
	 
		
	private static Connection getConnection(){
		Connection connection = null;
		
		try{
			Class.forName(Security.decrypt(jdbcDriverName)); //ensure that the library will be loaded to the memory
			//secure database connection string configuration
			connection = DriverManager.getConnection(Security.decrypt(jdbcUrl),
					Security.decrypt(dbUserName),Security.decrypt(dbPassword));
			
		}catch(ClassNotFoundException cfne){
			//should be displayed on a secure error page code
			System.err.println(cfne.getMessage());
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
		return connection;
	}
	
	///---------------------------------------------Clinic----------------------------------------------------------///
	
	public static String validate(String clinicname,String street, String barangay, String city, String province, String clinichours1M, String clinichours2M,String clinichours1T, String clinichours2T,String clinichours1W, String clinichours2W,String clinichours1TH, String clinichours2TH,String clinichours1F, String clinichours2F,String clinichours1Sat, String clinichours2Sat,String clinichours1Sun, String clinichours2Sun, String clinicdays,String services,String addservices, String contactno, String rootAdminId){
		String result="";
		
		String RAId=rootAdminId;
	    String address_id="";
		String clinic_id="";
		String status = "Active";
		String finalservice="";
		if (services.isEmpty()){
			if (addservices==null||addservices.isEmpty()){
				result="Add service";
			}
			else {
				finalservice=addservices;	
			}
		}
		else{
			if (addservices==null||addservices.isEmpty()){
				finalservice=services;	
			}
			else {
				finalservice=services+", "+addservices;	
			}
		}
		
		try{
			Connection connection = getConnection();
			PreparedStatement psValidate=connection.prepareStatement(VALIDATION_CLINIC);
			psValidate.setString(1, clinicname);
			ResultSet rsValidate=psValidate.executeQuery();
			while(rsValidate.next()){
				result="CLINICNAME THE SAME";
				break;
			}
			if(result.equals("CLINICNAME THE SAME")){
				
			}
			else{
				PreparedStatement psA = connection.prepareStatement(INSERT_ADDRESS);
				if (!street.isEmpty()&&!barangay.isEmpty()&&!city.isEmpty()&&!province.isEmpty()){
					psA.setString(1, street);
					psA.setString(2, barangay);
					psA.setString(3, city);
					psA.setString(4, province);
					psA.executeUpdate();
					
					PreparedStatement psA1 = connection.prepareStatement(GET_NEW_ADDRESS_ID);
					
					ResultSet rsA=psA1.executeQuery();
					while(rsA.next()) {
						address_id=rsA.getString(1);
					}
					try{
						PreparedStatement ps = connection.prepareStatement(INSERT_CLINIC);
						String clinicday=getClinicDays(clinichours1M,clinichours2M,clinichours1T,clinichours2T,clinichours1W,clinichours2W,clinichours1TH,clinichours2TH,clinichours1F,clinichours2F,clinichours1Sat,clinichours2Sat,clinichours1Sun,clinichours1Sun);
						String clinichours=getClinicHours(clinichours1M,clinichours2M,clinichours1T,clinichours2T,clinichours1W,clinichours2W,clinichours1TH,clinichours2TH,clinichours1F,clinichours2F,clinichours1Sat,clinichours2Sat,clinichours1Sun,clinichours1Sun);
						if(!clinicname.isEmpty()&&!clinicdays.isEmpty()&&!finalservice.isEmpty()&&!contactno.isEmpty()&&!status.isEmpty()){
							ps.setString(1, clinicname);
							ps.setString(2, address_id);
							ps.setString(3, clinicday);
							ps.setString(4, clinichours);
							ps.setString(5, finalservice);
							ps.setString(6, contactno);
							ps.setString(7, status);
							
							ps.executeUpdate();
						
							try{
								PreparedStatement ps1 = connection.prepareStatement(GET_NEW_CLINIC_ID);
								
								ResultSet rs=ps1.executeQuery();
								while(rs.next()) {
									clinic_id=rs.getString(1);
								}
								try{
									PreparedStatement ps2 = connection.prepareStatement(INSERT_CLINIC_ENROLLMENT);
									System.out.println(clinic_id + ", " + RAId);
									
										ps2.setString(1, clinic_id);
										ps2.setString(2, RAId);
										ps2.setString(3, "Active");

										
										ps2.executeUpdate();
								}
								catch(Exception e){
									e.printStackTrace();
									result="INSERT CLINIC ENROLLMENT";
									System.out.println("INSERT CLINIC ENROLLMENT");
								}
								
							}catch(Exception e){
								e.printStackTrace();
								result="VALIDATION CLINIC ERROR";
								System.out.println("VALIDATION CLINIC ERROR");
							}
						}
					}catch(Exception e){
						e.printStackTrace();
						result="INSERT CLINIC ENROLLMENT";
						System.out.println("GET NEW ADDRESS ERROR");
					}
					
					
				}
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			result="INSERT CLINIC ERROR";
			System.out.println("INSERT CLINIC ERROR");
		}
		return result;
	}
	public static String getClinicDays(String clinichours1M, String clinichours2M,String clinichours1T, String clinichours2T,String clinichours1W, String clinichours2W,String clinichours1TH, String clinichours2TH,String clinichours1F, String clinichours2F,String clinichours1Sat, String clinichours2Sat,String clinichours1Sun, String clinichours2Sun){
		String clinicdays="";
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Monday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Tuesday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Wednesday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Thursday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Friday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Saturday ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinicdays+="Sunday ";
		}
		return clinicdays;
	}
	public static String getClinicHours(String clinichours1M, String clinichours2M,String clinichours1T, String clinichours2T,String clinichours1W, String clinichours2W,String clinichours1TH, String clinichours2TH,String clinichours1F, String clinichours2F,String clinichours1Sat, String clinichours2Sat,String clinichours1Sun, String clinichours2Sun){
		String clinichours="";
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Monday: "+clinichours1M+" - "+clinichours2M+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Tuesday: "+clinichours1T+" - "+clinichours2T+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Wednesday: "+clinichours1W+" - "+clinichours2W+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Thursday: "+clinichours1TH+" - "+clinichours2TH+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Friday: "+clinichours1F+" - "+clinichours2F+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Saturday: "+clinichours1Sat+" - "+clinichours2Sat+"  ";
		}
		if(clinichours1M.equals("")||clinichours2M.equals("")){
			
		} else{
			clinichours=clinichours+"Sunday: "+clinichours1Sun+" - "+clinichours2Sun+"  ";
		}
		return clinichours;
	}
	
	public static List<String> getServices() {
		List<String> service=new ArrayList<String>();
		try{
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(LIST_ALL_SERVICES);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				service.add(rs.getString(1));
			}
			
		}catch(Exception e){e.printStackTrace();}
		return service;
	}
	
	public static List<ClinicBean> getClinic() {
		List<ClinicBean> clinicList=new ArrayList<ClinicBean>();
		try{
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(LIST_ALL_RECORDS);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
					   ClinicBean clinic=new ClinicBean();  
					   clinic.setClinicid(rs.getInt("clinic_id"));
					   clinic.setClinicname(rs.getString("clinicname"));  
					   clinic.setAddress(rs.getString("address_id"));  
					   clinic.setClinicdays(rs.getString("clinicdays"));
					   clinic.setClinichours(rs.getString("clinichours"));
					   clinic.setContactno(rs.getString("contactno"));
					   clinic.setStatus(rs.getString("status"));
					   clinicList.add(clinic);  
					   
			}
			
		}catch(Exception e){e.printStackTrace();}
		
		return clinicList;
	}
	

	public static boolean UnenrollClinic(String clinicid, String status, String reason) {
		boolean condition=false;
		Connection connection = getConnection();
		String statusreason=status;
		if(status.equals("Others..")){
			statusreason=reason;
		}
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_CLINIC);
			ps.setString(1, statusreason);
			ps.setString(2, clinicid);
			System.out.println("WENT HERE::"+clinicid);
			ps.executeUpdate();
			condition=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return condition;
	}
	
	///---------------------------------------------END Clinic------------------------------------------------------///
	///---------------------------------------------Admin-----------------------------------------------------------///
	public static String validate(int clinicid, String firstname,String lastname,String email, int accesslevel, String rootAdminId){
		String result="";
		String RAId=rootAdminId;
	    String password=generateString(8);
		String admin_id="";
		
		try{
			Connection connection = getConnection();
			PreparedStatement psValidate=connection.prepareStatement(VALIDATION_ADMIN);
			psValidate.setString(1, Security.encrypt(email));
			ResultSet rsValidate=psValidate.executeQuery();
			while(rsValidate.next()){
				result= "EMAIL THE SAME";
				break;
			}
			if(result.equals("EMAIL THE SAME")){
				
			}
			else{
				if(accesslevel==1){
					if(!firstname.isEmpty()&&!lastname.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&accesslevel<=3&&accesslevel>=0){
						PreparedStatement psRoot = connection.prepareStatement(INSERT_ROOT);

						psRoot.setString(1, firstname);
						psRoot.setString(2, lastname);
						psRoot.setString(3, Security.encrypt(email));
						psRoot.setString(4, Security.encrypt(password));
						psRoot.setInt(5, accesslevel);
						
						psRoot.executeUpdate();
						String emailmsg = "Good day!\n\nWe've successfully enrolled you as a Root Admin\nHere are your credentials:\npassword: "+password+"\n\nPlease change your password immediately after receiving this email\nThank you!";
						Mailer.send("keruxapp@gmail.com","letstalkkeruxcsb1120",email,"KERUX Root Admin Approval",emailmsg);
				
					}
					else{
						
					}
				}else{
					
					if(!firstname.isEmpty()&&!lastname.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&accesslevel<3&&accesslevel>0){
						PreparedStatement ps = connection.prepareStatement(INSERT_ADMIN);
						ps.setString(1, firstname);
						ps.setString(2, lastname);
						ps.setString(3, Security.encrypt(email));
						ps.setString(4, Security.encrypt(password));
						ps.setInt(5, accesslevel);
						
						ps.executeUpdate();

	//					
			
							PreparedStatement ps1 = connection.prepareStatement(GET_NEW_ADMIN_ID);
							
							ResultSet rs=ps1.executeQuery();
							while(rs.next()) {
								admin_id=rs.getString(1);
							}

								PreparedStatement ps2 = connection.prepareStatement(INSERT_ADMIN_ENROLLMENT);
								System.out.println(admin_id + ", " + RAId);
								
									ps2.setInt(1, clinicid);
									ps2.setString(2, admin_id);
									ps2.setString(3, RAId);
									ps2.setString(4, "Active");
	
									
									ps2.executeUpdate();

									String emailmsg = "Good day!\n\nWe've successfully enrolled you as an Admin\nHere are your credentials:\npassword: "+password+"\n\nPlease change your password immediately after receiving this email\nThank you!";
									String messageM = Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,"KERUX Admin Approval",emailmsg);
									result=messageM;
					}
					else{
						result="Access Level";
					}
					
				}
			}
			
		}catch(Exception e){e.printStackTrace();
		System.out.println(e.getMessage());}
		return result;
	}
	
	private static String generateString(int length){
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMmnbvcxzlkjhgfdsapoiuytrewq1234567890!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();
        for(int i = 0; i < length; i++){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
	
	public static List<AdminBean> getAdmin() {
		List<AdminBean> adminList=new ArrayList<AdminBean>();
		try{
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(LIST_ALL_ADMIN_RECORDS);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
					   AdminBean admin=new AdminBean();  
					   admin.setAdminid(rs.getInt("admin_id"));
					   admin.setFirstname(rs.getString("firstname"));
					   admin.setLastname(rs.getString("lastname"));
					   admin.setEmail(rs.getString("email"));
					   admin.setPassword(rs.getString("password"));
					   admin.setAccesslevel(rs.getInt("accesslevel_id"));
					   adminList.add(admin);  
			}
			
		}catch(Exception e){e.printStackTrace();}
		return adminList;
	}
	
	
	public static boolean UnenrollAdmin(String adminid, String status, String reason) {
		boolean condition=false;
		Connection connection = getConnection();
		
		String statusreason=status;
		if(status.equals("Others..")){
			statusreason=reason;
		}
		try{
			PreparedStatement ps1 = connection.prepareStatement(SELECT_ADMIN_ENROLLMENT);

			ps1.setString(1, adminid);

			boolean check=false;
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				check=true;
			}
			
			if(check){
				PreparedStatement ps2 = connection.prepareStatement(UPDATE_ADMIN_ENROLLMENT);
				ps2.setString(1, statusreason);
				ps2.setString(2, adminid);

				
				ps2.executeUpdate();
				condition=true;
				
				PreparedStatement ps3 = connection.prepareStatement(SELECT_ADMIN);
				ps3.setString(1,  adminid);
				
				ResultSet rs1=ps3.executeQuery();
				if(rs1.next()){
					String email=Security.decrypt(rs1.getString(3).trim());
					//System.out.println("hereee"+rs.getString(1)+" "+rs.getString(2)+Security.decrypt(rs.getString(3)).trim());
					String emailmsg = "Good day, "+rs1.getString(1)+" "+rs1.getString(2)+"\n\nWe regret to inform you that you have been unenrolled as an Admin\nin the Kerux Queueing Mobile App System";
					Mailer.send("roychristian.yabut@benilde.edu.ph","33CYzrpj",email,"KERUX Admin Unenrollment",emailmsg);
				}
				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("INSERT ADMIN ENROLLMENT");
		}
			
		
		return condition;
	}
	///---------------------------------------------END Admin-------------------------------------------------------///
	///---------------------------------------------Statistics-------------------------------------------------------///
	public static List<StatBean> getStat() {
		List<StatBean> statList=new ArrayList<StatBean>();
		try{
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_STAT);
			int i =ps.executeUpdate();
			if (i==1){
				PreparedStatement ps1=connection.prepareStatement(SELECT_STAT);
				ResultSet rs=ps1.executeQuery();
				while(rs.next()) {
						   StatBean stat=new StatBean();  
						   stat.setQueueserved(rs.getInt("QueuesServed"));
						   stat.setQueuescancelled(rs.getInt("QueuesCancelled"));
						   stat.setHighestdeptqueues(rs.getString("HighestDeptQueues"));
						   stat.setHighestdocqueues(rs.getString("HighestDocQueues"));
						   stat.setTimestart(rs.getString("TimeStart"));
						   stat.setTimeend(rs.getString("TimeEnd"));
						   statList.add(stat);  
				}
			}
			
			

		}catch(Exception e){e.printStackTrace();}
		return statList;
	}
	///---------------------------------------------END Statistics-------------------------------------------------------///
	///---------------------------------------------AUDIT-------------------------------------------------------///
	public static List<AuditBean> getAudit() {
		List<AuditBean> auditList=new ArrayList<AuditBean>();
		try{
			Connection connection = getConnection();
			Security sec = new Security();
			PreparedStatement ps=connection.prepareStatement(SELECT_AUDIT);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			   AuditBean audit=new AuditBean();  
			   audit.setAuditid(rs.getInt("Log_ID"));
			   audit.setTablename(sec.decrypt(rs.getString("TableName")));
			   audit.setEventtype(sec.decrypt(rs.getString("Event")));
			   audit.setSqlcommand(sec.decrypt(rs.getString("SqlCommand")));
			   audit.setOlddata(sec.decrypt(rs.getString("OldData")));
			   audit.setNewdata(sec.decrypt(rs.getString("NewData")));
			   audit.setLoginname(sec.decrypt(rs.getString("LoginName")));
			   audit.setTimestamp(rs.getString("TimeStamp"));
			   auditList.add(audit);  
				
			}
			
			

		}catch(Exception e){e.printStackTrace();}
		return auditList;
	}
	///---------------------------------------------ACCESSLEVEL-------------------------------------------------------///
	public static List<AccessLevelBean> getALevel() {
		List<AccessLevelBean> accessList=new ArrayList<AccessLevelBean>();
		try{
			Connection connection = getConnection();
			
			PreparedStatement ps=connection.prepareStatement(SELECT_ALEVEL);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AccessLevelBean access=new AccessLevelBean();  
			   access.setAccesslevel_id(rs.getInt(1));
			   access.setAccesslevel(rs.getString(2));
			   accessList.add(access);  
				
			}
			
			

		}catch(Exception e){e.printStackTrace();}
		return accessList;
	}
	
	///---------------------------------------------Root Admin----------------------------------------------------------///
	public static String getRootadminname(String adminid) {
		String rootadminname="";
		try{
			Connection connection = getConnection();
			
			PreparedStatement ps=connection.prepareStatement(SELECT_ROOTADMINNAME);
			ps.setString(1, adminid );
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				rootadminname=rs.getString(1)+" "+rs.getString(2);
			}
			
		}catch(Exception e){e.printStackTrace();}
		return rootadminname;
	}
	///---------------------------------------------RATING-------------------------------------------------------///

	public static List<RatingBean> getRating() {
		List<RatingBean> ratingList=new ArrayList<RatingBean>();
		try{
			Connection connection = getConnection();
			
			PreparedStatement ps=connection.prepareStatement(SELECT_RATING);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			   RatingBean rating=new RatingBean();  
			   rating.setRating_id(rs.getInt(1));
			   rating.setRating(rs.getInt(2));
			   rating.setPatientname(rs.getString(4)+", "+rs.getString(3));
			   rating.setQueuename(rs.getString(5));
			   rating.setTimestamp(rs.getString(6));
			   ratingList.add(rating);  
				
			}
			
			

		}catch(Exception e){e.printStackTrace();}
		return ratingList;
	}
	
	public static String getAveRating(){
		String average="";
		try{
			Connection connection = getConnection();
			
			PreparedStatement ps=connection.prepareStatement(SELECT_AVE_RATING);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			   average=rs.getString(1);
				
			}
			
			

		}catch(Exception e){e.printStackTrace();}
		return average;
	}
}
