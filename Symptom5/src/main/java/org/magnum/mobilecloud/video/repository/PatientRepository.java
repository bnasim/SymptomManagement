package org.magnum.mobilecloud.video.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface for a repository that can store Category
 * objects.
 * 
 * @author jules
 *
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, String>{

	public Patient findByUname(String username);
	public Patient findByName(String name);
}
