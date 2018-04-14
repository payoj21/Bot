package com.surgeon.eye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surgeon.eye.model.User;
import com.surgeon.eye.services.UserServices;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices userServices;
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
	user=userServices.saveUser(user);
		return user;
	}
	
	
	@RequestMapping(value="getcasedetail", method=RequestMethod.GET)
	public User getUser(@RequestParam(value="emailid", required=true) String emailId) throws NotFoundException {
		User user= userServices.getUser(emailId);
			return user;
	}

	
	
}
