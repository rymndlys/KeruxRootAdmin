package com.kerux.actions;

import com.kerux.controllers.MainControllerDB;
import com.opensymphony.xwork2.ActionSupport;

public class ClinicUnenrollment extends ActionSupport{
	
	private String clinic;
	private String clinicid;
	private String status;
	private String reason;

	public String unenroll(){
		return "success";
	}
	
	public void validate(){
		System.out.println(clinic+"' "+ clinicid+", "+status+", "+reason);
		if(MainControllerDB.UnenrollClinic(clinicid, status, reason)){
			addActionMessage("Clinic Unenrolled");
		}
		else{
			addActionError("Unenroll error");
		}
	}
	

	public String getClinic() {
		return clinic;
	}

	public void setClinic(String clinic) {
		this.clinic = clinic;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	

	public String getClinicid() {
		return clinicid;
	}

	public void setClinicid(String clinicid) {
		this.clinicid = clinicid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
