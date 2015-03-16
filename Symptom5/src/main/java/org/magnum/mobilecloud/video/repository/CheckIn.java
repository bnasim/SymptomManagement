package org.magnum.mobilecloud.video.repository;
import java.io.Serializable;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;


import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

@Entity
public class CheckIn  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private  long id;
	
	private Boolean medicineTaken;
	 
	   /** Property painType */
	  private String painType;
	 
	   /** Property eatingState */
	  private String eatingState;
		
	
	private String uname;
	private String firstmedicine;
	private String secondmedicine;
	private Boolean medicine1Taken;
	private Boolean medicine2Taken;
	
	 private long checkInDateTime;
	 private long medicine1TakenTime;
		private long medicine2TakenTime;

	public CheckIn() {
	}

	public CheckIn(Boolean medicineTaken,String painType,String eatingState,String uname,String firstmedicine,String secondmedicine,Boolean medicine1Taken,Boolean medicine2Taken,long checkInDateTime,long medicine1TakenTime,long medicine2TakenTime) {
		super();
		this.medicineTaken = medicineTaken;
		this.painType = painType;
		this.eatingState = eatingState;
		this.uname = uname;
		this.firstmedicine = firstmedicine;
		this.secondmedicine = secondmedicine;
		this.medicine1Taken = medicine1Taken;
		this.medicine2Taken = medicine2Taken;
		this.checkInDateTime = checkInDateTime;
		this.medicine1TakenTime = medicine1TakenTime;
		this.medicine2TakenTime = medicine2TakenTime;
	}
		
	
	public boolean getMedicineTaken() {
	      return this.medicineTaken;
	   }
	 
	   /**
	    * Sets the medicineTaken
	    */
	   public void setMedicineTaken(Boolean medicineTaken) {
	      this.medicineTaken = medicineTaken;
	   }
	   
		   public boolean getMedicine1Taken() {
			      return this.medicine1Taken;
			   }
			 
			   /**
			    * Sets the medicineTaken
			    */
			   public void setMedicine1Taken(Boolean medicine1Taken) {
			      this.medicine1Taken = medicine1Taken;
			   }
			   public boolean getMedicine2Taken() {
				      return this.medicine2Taken;
				   }
				 
				   /**
				    * Sets the medicineTaken
				    */
				   public void setMedicine2Taken(Boolean medicine2Taken) {
				      this.medicine2Taken = medicine2Taken;
				   }
	   /**
	    * Gets the painType
	    */
	   public String getPainType() {
	      return this.painType;
	   }
	 
	   /**
	    * Sets the painType
	    */
	   public void setPainType(String painType) {
	      this.painType = painType;
	   }
	   public String getUname() {
		      return this.uname;
		   }
		 
		   /**
		    * Sets the painType
		    */
		   public void setUname(String uname) {
		      this.uname = uname;
		   }
	 
	   /**
	    * Gets the eatingState
	    */
	   public String getEatingState() {
	      return this.eatingState;
	   }
	 
	   /**
	    * Sets the eatingState
	    */
	   public void setEatingState(String eatingState) {
	      this.eatingState = eatingState;
	   }
	 
	   /**
	    * Gets the id
	    */
	   public long getId() {
	      return this.id;
	   }
	 
	   /**
	    * Sets the id
	    */
	   public void setId(long id) {
	      this.id = id;
	   }
	   public String getFirstmedicine() {
		      return this.firstmedicine;
		   }
		 
		   /**
		    * Sets the painType
		    */
		   public void setFirstmedicine(String firstmedicine) {
		      this.firstmedicine = firstmedicine;
		   }
		   public String getSecondmedicine() {
			      return this.secondmedicine;
			   }
			 
			   /**
			    * Sets the painType
			    */
			   public void setSecondmedicine(String secondmedicine) {
			      this.secondmedicine = secondmedicine;
			   }
	   public long getCheckInDateTime() {
		      return this.checkInDateTime;
		   }
		 
		   /**
		    * Sets the id
		    */
		   public void setCheckInDateTime(long checkInDateTime) {
		      this.checkInDateTime = checkInDateTime;
		   }
		
		   public long getMedicine1TakenTime() {
			      return this.medicine1TakenTime;
			   }
			 
			   /**
			    * Sets the id
			    */
			   public void setMedicine1TakenTime(long medicine1TakenTime) {
			      this.medicine1TakenTime = medicine1TakenTime;
			   }
		 
			   public long getMedicine2TakenTime() {
				      return this.medicine2TakenTime;
				   }
				 
				   /**
				    * Sets the id
				    */
			   public void setMedicine2TakenTime(long medicine2TakenTime) {
				      this.medicine2TakenTime = medicine2TakenTime;
				   }
		
}
/**
 * Generated on Wed Nov 12 19:10:50 UTC 2014 by ObjGen 3.0
 */
