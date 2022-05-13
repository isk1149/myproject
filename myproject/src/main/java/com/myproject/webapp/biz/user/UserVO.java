package com.myproject.webapp.biz.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USER_TB")
public class UserVO {
	@Id
	private String user_id;
	private String user_nm;
	private String pwd;
	//@Temporal(TemporalType.DATE)
	private Date create_dt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	@Override
	public String toString() {
		return "[UserVO] {user_id : " + user_id + ", user_nm : " + user_nm + ", pwd : " + pwd + 
			   ", create_dt : " + create_dt + "}";
	}
}
