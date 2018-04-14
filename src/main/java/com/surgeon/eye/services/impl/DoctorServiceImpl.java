package com.surgeon.eye.services.impl;

import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surgeon.eye.Repositories.DoctorRepository;
import com.surgeon.eye.Repositories.PatientRepository;
import com.surgeon.eye.model.Doctor;
import com.surgeon.eye.model.DoctorAppointmentDetails;
import com.surgeon.eye.model.Patient;

@Service
public class DoctorServiceImpl {

	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;


	public Doctor saveAppointment(DoctorAppointmentDetails appointmentDetails, String doctorId) {
		Doctor doctor=null;
	
		if( doctorId!=null && appointmentDetails!=null) {
			doctor =	doctorRepository.findOne(doctorId);
			if(doctor==null)
				throw new RuntimeException("No doctor found");
			if(appointmentDetails.getPatientId()==null)
				throw new IllegalArgumentException("Invalid patient Id");
			else {
				Patient patient= patientRepository.findOne(appointmentDetails.getPatientId());
				if(patient==null)
					throw new RuntimeException("No patient found");

				if(CollectionUtils.isEmpty(doctor.getAppointments())) {
					appointmentDetails.setRecordId(UUID.randomUUID().toString().replaceAll("", "-"));
					doctor.getAppointments().add(appointmentDetails);
					doctor= doctorRepository.save(doctor);
				}
				else {
					// 15 mins. record id. offie hours check. 
					doctor.getAppointments().stream().forEach(appointment->{
						if(appointment.getDate().equals(appointmentDetails.getDate())) {
							throw new RuntimeException("Slot unavailable");
						}
					});
					appointmentDetails.setRecordId(UUID.randomUUID().toString().replaceAll("", "-"));
					doctor.getAppointments().add(appointmentDetails);
					doctor= doctorRepository.save(doctor);
				}
			}
		}
		else
			throw new IllegalArgumentException("Invalid Request");


		return doctor;
	}
}
