package com.zxhy.xjl.people;

import java.util.Date;

/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:08:18
*/
public class IDInfo {
	private String idCode;
	private String idName;
	private String address;
	private String nation;
	private String birthday;
	private String sex;//性别
	private String  issuingOrganizations;
	private Date  issuingDate;
	private Date expireDate;
	private String photo;//图片
	
	
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIssuingOrganizations() {
		return issuingOrganizations;
	}
	public void setIssuingOrganizations(String issuingOrganizations) {
		this.issuingOrganizations = issuingOrganizations;
	}
	public Date getIssuingDate() {
		return issuingDate;
	}
	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
