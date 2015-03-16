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
public class Medication  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private  long id;
	
	 
	  private String uname;
	  private String patientName;
	  private String medicine1Name;
	  private String medicine2Name;
	  private String instruction;
	 	
	 private long prescriptionDate;

	public Medication() {
	}

	public Medication(String uname,String patientName,String medicine1Name,String medicine2Name,String instruction,long prescriptionDate) {
		super();
		this.uname = uname;
		this.patientName = patientName;
		this.medicine1Name = medicine1Name;
		this.medicine2Name=medicine2Name;
		this.instruction=instruction;
		this.prescriptionDate=prescriptionDate;
		
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
		   public String getPatientName() {
			      return this.patientName;
			   }
			 
			   /**
			    * Sets the painType
			    */
			   public void setPatientName(String patientName) {
			      this.patientName = patientName;
			   }   
	 
			   public String getMedicine1Name() {
				      return this.medicine1Name;
				   }
				 
				   /**
				    * Sets the painType
				    */
				   public void setMedicine1Name(String medicine1Name) {
				      this.medicine1Name = medicine1Name;
				   }   
				   public String getMedicine2Name() {
					      return this.medicine2Name;
					   }
					 
					   /**
					    * Sets the painType
					    */
					   public void setMedicine2Name(String medicine2Name) {
					      this.medicine2Name = medicine2Name;
					   }    
					   public String getInstruction() {
						      return this.instruction;
						   }
						 
						   /**
						    * Sets the painType
						    */
						   public void setInstruction(String instruction) {
						      this.instruction = instruction;
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
	   public long getPrescriptionDate() {
		      return this.prescriptionDate;
		   }
		 
		   /**
		    * Sets the id
		    */
		   public void setPrescriptionDate(long prescriptionDate) {
		      this.prescriptionDate = prescriptionDate;
		   }
		
	 
		
}
/**
 * Generated on Wed Nov 12 19:10:50 UTC 2014 by ObjGen 3.0
 */
