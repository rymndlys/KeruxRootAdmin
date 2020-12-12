package com.kerux.SpinnerObjects;

public class Department {
	int department_id;
	int clinic_id;
	int reasonrevoke_id;
	String name;
	String status;
	
	public Department(int department_id, int clinic_id, int reasonrevoke_id, String name, String status) {
		super();
		this.department_id = department_id;
		this.clinic_id = clinic_id;
		this.reasonrevoke_id = reasonrevoke_id;
		this.name = name;
		this.status = status;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
