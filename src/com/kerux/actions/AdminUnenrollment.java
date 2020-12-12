package com.kerux.actions;

import com.kerux.controllers.MainControllerDB;
import com.opensymphony.xwork2.ActionSupport;

public class AdminUnenrollment extends ActionSupport{
	private String adminid;
	private String admin;
	private String status;
	private String reason;
	
	public String unenroll(){
		return "success";
	}
	
	
	public void validate(){
		System.out.print("|"+adminid+"|"+admin+"|"+status+"|"+reason+"|");
		if(MainControllerDB.UnenrollAdmin(adminid, status, reason)){
			addActionMessage("Admin Unenrolled");
		}
		else{
			addActionError("Unenroll error");
		}
	}
	
	
	
	public String getAdminid() {
		return adminid;
	}


	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}


	public String getAdmin() {
		return admin;
	}


	public void setAdmin(String admin) {
		this.admin = admin;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
}
