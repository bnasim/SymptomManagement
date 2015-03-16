package org.magnum.mobilecloud.video.repository;

import java.io.Serializable;
import java.util.Collection;



import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;



import com.google.common.base.Objects;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
@Entity
public class Patient {

	@Id
	private String uname;

	private String name;
	private long dob;
	private String address;
	
	@ManyToOne
	private Physician physician;

	public Patient() {
	}

	public Patient(String uname, String name, long dob,String address) {
		super();
		this.uname = uname;
		this.name = name;
		this.dob = dob;
		this.address=address;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDob() {
		return dob;
	}

	public void setDob(long dob) {
		this.dob = dob;
	}

	

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	

	/**
	 * Two Videos will generate the same hashcode if they have exactly the same
	 * values for their name, url, and duration.
	 * 
	 */
	@Override
	public int hashCode() {
		// Google Guava provides great utilities for hashing
		return Objects.hashCode(uname, name, dob,address);
	}

	/**
	 * Two Videos are considered equal if they have exactly the same values for
	 * their name, url, and duration.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Patient) {
			Patient other = (Patient) obj;
			// Google Guava provides great utilities for equals too!
			return Objects.equal(uname, other.uname)
					&& Objects.equal(name, other.name)
					&& dob == other.dob
			        && address ==other.address;
		} else {
			return false;
		}
	}

}
