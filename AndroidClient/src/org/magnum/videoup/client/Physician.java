package org.magnum.videoup.client;



import com.google.common.base.Objects;


/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */

public class Physician {

	
	private String uname;

	private String dname;
	
	private String address;
	
	
	public Physician() {
	}

	public Physician(String uname, String dname, String address) {
		super();
		this.uname = uname;
		this.dname = dname;
		this.address=address;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDname() {
		return dname;
	}

	public void setName(String dname) {
		this.dname = dname;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
