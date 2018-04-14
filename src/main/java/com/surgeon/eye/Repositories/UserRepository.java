package com.surgeon.eye.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.surgeon.eye.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	@Query(value = "{'email':?0 }")
	User findByEmail(String email);

}
