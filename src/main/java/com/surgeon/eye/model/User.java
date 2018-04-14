package com.surgeon.eye.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="sharpsight_users")
public class User {
	
	@Id
	private String id;
	private String email;
	private List<CaseDetails> caseDetails ;
	

}
