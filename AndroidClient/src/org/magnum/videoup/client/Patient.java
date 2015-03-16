package org.magnum.videoup.client;



import com.google.common.base.Objects;


/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */

public class Patient {

	
	private String uname;

	private String name;
	private long dob;
	private String address;
	
	
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

}	

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
