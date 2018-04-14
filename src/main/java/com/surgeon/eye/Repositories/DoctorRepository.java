package com.surgeon.eye.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.surgeon.eye.model.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
	
	
	List<Doctor> findBySpeciality(String speciality);

}
