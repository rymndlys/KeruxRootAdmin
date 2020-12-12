package com.kerux.SpinnerObjects;

public class ReasonRevoke {
	int reasonrevoke_id;
	String reason;
	String tableName;
	
	public ReasonRevoke(int reasonrevoke_id, String reason, String tableName) {
		super();
		this.reasonrevoke_id = reasonrevoke_id;
		this.reason = reason;
		this.tableName = tableName;
	}
	public int getReasonrevoke_id() {
		return reasonrevoke_id;
	}
	public void setReasonrevoke_id(int reasonrevoke_id) {
		this.reasonrevoke_id = reasonrevoke_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
