package org.magnum.mobilecloud.video.repository;
import java.util.Collection;

import org.magnum.mobilecloud.video.client.PatientSvcApi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends CrudRepository<Alert, Long>{

	// Find all videos with a matching title (e.g., Video.name)
	//public Collection<Patient> findByFirstName(@Param(PatientSvcApi.FIRSTNAME_PARAMETER) String firstname);
	public Collection<Alert> findByDname(String dname);
	
}