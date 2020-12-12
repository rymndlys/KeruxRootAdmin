package com.kerux.bean;

public class StatBean {
	private int queueserved;
	private int queuescancelled;
	private String  highestdocqueues;
	private String highestdeptqueues;
	private String timestart;
	private String timeend;
	public int getQueueserved() {
		return queueserved;
	}
	public void setQueueserved(int queueserved) {
		this.queueserved = queueserved;
	}
	public int getQueuescancelled() {
		return queuescancelled;
	}
	public void setQueuescancelled(int queuescancelled) {
		this.queuescancelled = queuescancelled;
	}
	public String getHighestdocqueues() {
		return highestdocqueues;
	}
	public void setHighestdocqueues(String highestdocqueues) {
		this.highestdocqueues = highestdocqueues;
	}
	public String getHighestdeptqueues() {
		return highestdeptqueues;
	}
	public void setHighestdeptqueues(String highestdeptqueues) {
		this.highestdeptqueues = highestdeptqueues;
	}
	public String getTimestart() {
		return timestart;
	}
	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}
	public String getTimeend() {
		return timeend;
	}
	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}
	
	
}
