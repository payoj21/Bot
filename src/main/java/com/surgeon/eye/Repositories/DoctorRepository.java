package com.surgeon.eye.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.surgeon.eye.model.Doctor;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

}
