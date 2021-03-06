package org.magnum.mobilecloud.video.repository;

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
public interface PhysicianRepository extends CrudRepository<Physician, String>{

	public Physician findByUname(String username);
}
