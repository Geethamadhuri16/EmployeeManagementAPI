package com.Global.Groupware.model;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class Employee {
    @Id
    private String e_ID;
    private String employeeName;
    private String phoneNumber;
    private String email;
    private String reportsTo;
    private String emp_ProfileImg;
    public Employee() {
    	String id = UUID.randomUUID().toString();
        this.sete_ID(id);
    }
	public String gete_ID() {
		return e_ID;
	}
	public void sete_ID(String e_ID) {
		this.e_ID = e_ID;
	}
	public String getemployeeName() {
		return employeeName;
	}
	public void setemployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getphoneNumber() {
		return phoneNumber;
	}
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getreportsTo() {
		return reportsTo;
	}
	public void setreportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}
	public String getemp_ProfileImg() {
		return emp_ProfileImg;
	}
	public void setemp_ProfileImg(String emp_ProfileImg) {
		this.emp_ProfileImg = emp_ProfileImg;
	}

}