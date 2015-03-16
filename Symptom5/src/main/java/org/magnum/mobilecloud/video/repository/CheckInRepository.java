package org.magnum.mobilecloud.video.repository;
import java.util.Collection;


import org.magnum.mobilecloud.video.client.PatientSvcApi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface CheckInRepository extends CrudRepository<CheckIn, Long>{

	// Find all videos with a matching title (e.g., Video.name)
	//public Collection<Patient> findByFirstName(@Param(PatientSvcApi.FIRSTNAME_PARAMETER) String firstname);
	
	public Collection<CheckIn> findByUname(@Param(PatientSvcApi.USERNAME_PARAMETER) String uname);
	public Collection<CheckIn> findByCheckInDateTimeAndPainTypeAndUname(@Param(PatientSvcApi.CHECKINDATETIME_PARAMETER) long checkindatetime,@Param(PatientSvcApi.PAINTYPE_PARAMETER) String paintype,@Param(PatientSvcApi.USERNAME_PARAMETER) String uname);
}