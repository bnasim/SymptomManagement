package org.magnum.mobilecloud.video.repository;

import java.util.Collection;


import org.magnum.mobilecloud.video.client.PatientSvcApi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 * An interface for a repository that can store Video
 * objects and allow them to be searched by title.
 * 
 * @author jules
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	// Find all videos with a matching title (e.g., Video.name)
	public User findByUsername(@Param(PatientSvcApi.USERNAME_PARAMETER) String username);
	
}