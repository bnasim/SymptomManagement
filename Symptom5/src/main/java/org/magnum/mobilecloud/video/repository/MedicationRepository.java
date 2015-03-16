package org.magnum.mobilecloud.video.repository;

import java.util.Collection;


import org.magnum.mobilecloud.video.client.PatientSvcApi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long>{

	// Find all videos with a matching title (e.g., Video.name)
	//public Collection<Patient> findByFirstName(@Param(PatientSvcApi.FIRSTNAME_PARAMETER) String firstname);
	public Collection<Medication> findByUname(@Param(PatientSvcApi.USERNAME_PARAMETER) String uname);
	public Collection<Medication> findByPatientName(@Param(PatientSvcApi.PATIENTNAME_PARAMETER) String pname);
	public Medication findByPrescriptionDateAndPatientName(@Param(PatientSvcApi.PRESCRIPTIONDATE_PARAMETER) long prescriptionDate,@Param(PatientSvcApi.PATIENTNAME_PARAMETER) String patientName);
	
	
}