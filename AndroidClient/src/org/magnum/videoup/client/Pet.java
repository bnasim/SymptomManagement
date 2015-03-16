package org.magnum.videoup.client;


public class Pet {
	private int id;
    private String name;
	private Owner o;
	
	public Pet() {
	}

	public Pet(String name) {
		super();
		this.name = name;
		
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Owner getOwner() {
		return o;
	}

	public void setOwner(Owner o) {
		this.o = o;
	}
	
	

}

