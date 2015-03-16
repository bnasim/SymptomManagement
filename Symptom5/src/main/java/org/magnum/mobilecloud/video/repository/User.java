package org.magnum.mobilecloud.video.repository;

import java.util.HashSet;




import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * You probably need to, at a minimum, add some annotations to this
 * class.
 * 
 * You are free to add annotations, members, and methods to this
 * class. However, you probably should not change the existing
 * methods or member variables. If you do change them, you need
 * to make sure that they are serialized into JSON in a way that
 * matches what is expected by the auto-grader.
 * 
 * @author mitchell
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	

	private String username;
	private String role;
	
	
	
	public User() {
	}

	public User(String username,String role) {
		super();
		this.username = username;
		this.role=role;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getRole() {
	      return role;
	     }
	
	    

	    public void setRole(String role) {
	      this.role = role;
	    }    
	
	/**
	 * Two Videos will generate the same hashcode if they have exactly the same
	 * values for their name, url, and duration.
	 * 
	 */
	
	

}
