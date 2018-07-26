package com.dto;

import java.util.Date;

public class RequestVO {
	private String id;
	private String user_id;
	private String title;
	private String content;
	private Date request_date;
	private String manager_id;
	private String priority_id;
	private String process_group_id;
	private String etc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getPriority_id() {
		return priority_id;
	}
	public void setPriority_id(String priority_id) {
		this.priority_id = priority_id;
	}
	public String getProcess_group_id() {
		return process_group_id;
	}
	public void setProcess_group_id(String process_group_id) {
		this.process_group_id = process_group_id;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
}
