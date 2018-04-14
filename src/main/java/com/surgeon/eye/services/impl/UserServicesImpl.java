package com.surgeon.eye.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.surgeon.eye.Repositories.UserRepository;
import com.surgeon.eye.model.CaseDetails;
import com.surgeon.eye.model.User;
import com.surgeon.eye.services.UserServices;

import org.apache.commons.lang3.StringUtils;

@Service
public class UserServicesImpl implements UserServices{

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		
		if(user!=null && user.getCaseDetails()!=null && user.getCaseDetails().size()==1){
			
			String email= user.getEmail();
			if(StringUtils.isNotBlank(email)) {
			User savedUser= userRepository.findByEmail(email);
			if(savedUser==null) {
				// treat as a freah user and save it.
				user=	userRepository.save(user);
			}

			else {
				//save a new case in db
				CaseDetails caseDetails= user.getCaseDetails().get(0);
				boolean doesCaseExists=false;
				List<CaseDetails> caseDetailList= savedUser.getCaseDetails();
				for(CaseDetails savedCaseDetails: caseDetailList) {
					if(savedCaseDetails.getMrdNo().equalsIgnoreCase(caseDetails.getMrdNo())) {
						doesCaseExists=true;
					user=savedUser;
					break;
					}
				}
				if(!doesCaseExists) {
				caseDetailList.add(user.getCaseDetails().get(0));
				savedUser.setCaseDetails(caseDetailList);
				user= userRepository.save(savedUser);
				}
			}
		}
			else
				throw new IllegalArgumentException();
		}	
		else
			throw new IllegalArgumentException();
			

		return user;
	}

	@Override
	public User getUser(String email) throws NotFoundException {
		User savedUser=null;
		if(StringUtils.isNotBlank(email)) {
	      savedUser= userRepository.findByEmail(email);
		if(savedUser==null)
			throw new NotFoundException();
		}
		else
			throw new IllegalArgumentException();
			
		return savedUser;
	}
	
	
	public static void main() {
		String phoneNumber="0041446681800";
		PhoneNumberUtil phoneUtil= PhoneNumberUtil.getInstance();
		PhoneNumber number;
		try {
			number = phoneUtil.parse(phoneNumber, "IN");
			if(phoneUtil.isValidNumber(number))
				System.out.println(true);
		} catch (NumberParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
