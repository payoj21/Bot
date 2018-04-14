package com.surgeon.eye.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="patients")
public class Patient {
	
	@Id
	private String id;
	private String name;
	private String phoneNumber;
	private String email;
	private int age;
	private String gender;
	

}
