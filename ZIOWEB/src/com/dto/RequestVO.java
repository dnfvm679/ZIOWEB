package com.dto;

import java.util.Date;

public class RequestVO {
	private int req_index;
	private String id;
	private String user_id;
	private String title;
	private String content;
	private Date request_date;
	private String etc;
	private String manager_id;
	private String priority_id;
	private String process_group_id;
	private String process_form_id;
	private String process_state_id;
	private String process_content;
	private String process_hour;
	private String complete_date;
	
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
	public String getProcess_form_id() {
		return process_form_id;
	}
	public void setProcess_form_id(String process_form_id) {
		this.process_form_id = process_form_id;
	}
	public String getProcess_state_id() {
		return process_state_id;
	}
	public void setProcess_state_id(String process_state_id) {
		this.process_state_id = process_state_id;
	}
	public String getProcess_content() {
		return process_content;
	}
	public void setProcess_content(String process_content) {
		this.process_content = process_content;
	}
	public String getProcess_hour() {
		return process_hour;
	}
	public void setProcess_hour(String process_hour) {
		this.process_hour = process_hour;
	}
	public String getComplete_date() {
		return complete_date;
	}
	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}
	public int getReq_index() {
		return req_index;
	}
	public void setReq_index(int req_index) {
		this.req_index = req_index;
	}
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
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
}
