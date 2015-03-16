package org.magnum.videoup.client;

import java.util.List;





/**
 * A simple object to represent a video and its URL for viewing.
 * 
 * @author jules
 * 
 */
public class Owner {
	 private int id;
	    private String firstName;
	    private String lastName;
	    private String city;
	    private List<Pet> pets;
	
	
	public Owner() {
	}

	public Owner(String firstName,String lastName,String city) {
		super();
		this.firstName = firstName;
		this.lastName = firstName;
		this.city = city;
		
		
		
	}

	public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }


	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 public List<Pet> getPets() {
	        return pets;
	    }
	    public void setPets(List<Pet> pets) {
	        this.pets = pets;
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
