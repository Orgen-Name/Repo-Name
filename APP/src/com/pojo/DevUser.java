package com.pojo;

import java.util.Date;

public class DevUser {
	private Integer id;//ä¸»é”®id
	private String devCode;//å¼?å‘è?…å¸å?(ç³»ç»Ÿç™»å½•è´¦å·)
	private String devName;//å¼?å‘è?…åç§?
	private String devPassword;//å¼?å‘è?…å¯†ç ?
	private String devEmail;//å¼?å‘è?…é‚®ç®?
	private String devInfo;	//å¼?å‘è?…ç®€ä»?
	private Integer createdBy;//åˆ›å»ºè€?
	private Date creationDate;//åˆ›å»ºæ—¶é—´
	private Integer modifyBy;//æ›´æ–°è€?
	private Date modifyDate;//æ›´æ–°æ—¶é—´
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDevCode() {
		return devCode;
	}
	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getDevPassword() {
		return devPassword;
	}
	public void setDevPassword(String devPassword) {
		this.devPassword = devPassword;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public String getDevInfo() {
		return devInfo;
	}
	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
