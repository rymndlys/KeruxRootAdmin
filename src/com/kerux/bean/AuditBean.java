package com.kerux.bean;

public class AuditBean {
	private int auditid;
	private String tablename;
	private String eventtype;
	private String sqlcommand;
	private String olddata;
	private String newdata;
	private String timestamp;
	private String loginname;
	
	
	public int getAuditid() {
		return auditid;
	}
	public void setAuditid(int auditid) {
		this.auditid = auditid;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public String getSqlcommand() {
		return sqlcommand;
	}
	public void setSqlcommand(String sqlcommand) {
		this.sqlcommand = sqlcommand;
	}
	public String getOlddata() {
		return olddata;
	}
	public void setOlddata(String olddata) {
		this.olddata = olddata;
	}
	public String getNewdata() {
		return newdata;
	}
	public void setNewdata(String newdata) {
		this.newdata = newdata;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	
}
