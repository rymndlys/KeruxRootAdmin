package com.kerux.SpinnerObjects;

public class Doctor {
	
	int doctor_id;
	int doctortype_id;
	int clinic_id;
	int reasonrevoke_id;
	int department_id;
	String firstName;
	String lastName;
	String roomNo;
	String schedule1;
	String schedule2;
	String days;
	String status;
	
	
	public Doctor(int doctor_id, int doctortype_id, int clinic_id, int reasonrevoke_id, int department_id,
			String firstName, String lastName, String roomNo, String schedule1, String schedule2, String days,
			String status) {
		super();
		this.doctor_id = doctor_id;
		this.doctortype_id = doctortype_id;
		this.clinic_id = clinic_id;
		this.reasonrevoke_id = reasonrevoke_id;
		this.department_id = department_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roomNo = roomNo;
		this.schedule1 = schedule1;
		this.schedule2 = schedule2;
		this.days = days;
		this.status = status;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getDoctortype_id() {
		return doctortype_id;
	}
	public void setDoctortype_id(int doctortype_id) {
		this.doctortype_id = doctortype_id;
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
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getSchedule1() {
		return schedule1;
	}
	public void setSchedule1(String schedule1) {
		this.schedule1 = schedule1;
	}
	public String getSchedule2() {
		return schedule2;
	}
	public void setSchedule2(String schedule2) {
		this.schedule2 = schedule2;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
