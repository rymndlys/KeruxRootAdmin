package com.kerux.SpinnerObjects;

public class Clinic {
	int clinic_id;
	int reasonrevoke_id;
	String clinicName;
	String services;
	String contactNo;
	String address;
	String clinicHours;
	String clinicDays;
	String status;
	
	public Clinic(int clinic_id, int reasonrevoke_id, String clinicName, String services, String contactNo,
			String address, String clinicHours, String clinicDays, String status) {
		super();
		this.clinic_id = clinic_id;
		this.reasonrevoke_id = reasonrevoke_id;
		this.clinicName = clinicName;
		this.services = services;
		this.contactNo = contactNo;
		this.address = address;
		this.clinicHours = clinicHours;
		this.clinicDays = clinicDays;
		this.status = status;
	}
	public int getClinic_id() {
		return clinic_id;
	}
	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}
	public int getReasonrevoke_id() {
		return reasonrevoke_id;
	}
	public void setReasonrevoke_id(int reasonrevoke_id) {
		this.reasonrevoke_id = reasonrevoke_id;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClinicHours() {
		return clinicHours;
	}
	public void setClinicHours(String clinicHours) {
		this.clinicHours = clinicHours;
	}
	public String getClinicDays() {
		return clinicDays;
	}
	public void setClinicDays(String clinicDays) {
		this.clinicDays = clinicDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
