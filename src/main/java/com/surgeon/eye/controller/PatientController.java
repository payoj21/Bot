package com.surgeon.eye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surgeon.eye.model.Patient;
import com.surgeon.eye.model.Prescription;
import com.surgeon.eye.services.impl.PatientServiceImpl;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientServiceImpl patientServiceImpl;

	@RequestMapping(value="/saveprescription", method=RequestMethod.POST)
	public Patient bookAppointment(@RequestBody Prescription prescription,
			@RequestParam("patientId") String patientId) {
		Patient patient =patientServiceImpl.savePrescription(prescription, patientId);
			return patient;
		}
}
