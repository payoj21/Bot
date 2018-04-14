package com.surgeon.eye.model;

import java.util.Date;

import lombok.Data;

@Data
public class CaseDetails {
	
	private String mrdNo;
	private String userName;
	private String mobileNumber;
	private String gender;
	private Date dob;
}
