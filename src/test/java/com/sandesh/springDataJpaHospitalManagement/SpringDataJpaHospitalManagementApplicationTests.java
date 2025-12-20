package com.sandesh.springDataJpaHospitalManagement;

import com.sandesh.springDataJpaHospitalManagement.entity.Patient;
import com.sandesh.springDataJpaHospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.TimeZone;

@SpringBootTest
class SpringDataJpaHospitalManagementApplicationTests {

		@Autowired
		private PatientRepository patientRepository;
	
		@Test
		void testPatients(){
			TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
			List<Patient> patient = patientRepository.findAll();
			System.out.println(patient);
		}

}
