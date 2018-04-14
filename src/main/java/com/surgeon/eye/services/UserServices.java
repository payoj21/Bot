package com.surgeon.eye.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.surgeon.eye.model.User;


public interface UserServices {
	
	public User saveUser(User user);
	
	public User getUser(String email) throws NotFoundException;

}
