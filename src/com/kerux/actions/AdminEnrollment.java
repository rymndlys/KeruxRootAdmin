package com.kerux.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kerux.controllers.MainControllerDB;
import com.opensymphony.xwork2.ActionSupport;

public class AdminEnrollment extends ActionSupport{
	int clinicid;
	private int adminid;
	private String firstname;
	private String lastname;
	private String email;
	private int accesslevel;
	

	public String execute(){
		return "success";
	}
	
	public void validate(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String rootAdminId=(String)session.getAttribute("rootAdminId");
		System.out.println(accesslevel+"------*******--------||");
		System.out.println(clinicid+"---------------------||");
		String result=MainControllerDB.validate(clinicid, firstname, lastname, email, accesslevel, rootAdminId);
		if(result.equals("")){
			addActionMessage("Admin successfully inserted");
		}
		else if(result.equals("EMAIL THE SAME")){
			addActionError("Admin email already taken");
		}
		else if(result.equals("Access Level")){
			addActionError("Please choose an access level");
		}
		else{
			addActionError(result);
		}
	}
	
	public int getClinicid() {
		return clinicid;
	}

	public void setClinicid(int clinicid) {
		this.clinicid = clinicid;
	}

	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getAccesslevel() {
		return accesslevel;
	}
	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}
	




}

