package org.magnum.mobilecloud.video.repository;
import java.util.Collection;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.base.Objects;

@Entity
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private  long id;
	
	private String message;
	 
	   /** Property date */
	   private long alertDate;
	   private String dname;
	   private String pname;
	
	public Alert() {
	}

	public Alert(String message,long alertDate,String dname,String pname) {
		super();
		this.message = message;
		this.alertDate = alertDate;
		this.dname= dname;
		this.pname=pname;
	}
	 public long getId() {
	      return this.id;
	   }
	 
	   /**
	    * Sets the id
	    */
	   public void setId(long id) {
	      this.id = id;
	   }
	 
	   /**
	    * Gets the medicineName
	    */
	   public String getMessage() {
		      return this.message;
		   }
		 
		   /**
		    * Sets the massage
		    */
		   public void setMessage(String message) {
		      this.message = message;
		   }
		 
		   /**
		    * Gets the date
		    */
		   public long getAlertDate() {
		      return this.alertDate;
		   }
		 
		   /**
		    * Sets the date
		    */
		   public void setAlertDate(long alertDate) {
		      this.alertDate = alertDate;
		   }
	   
		   public String getDname() {
			      return this.dname;
			   }
			 
			   /**
			    * Sets the massage
			    */
			   public void setDname(String dname) {
			      this.dname = dname;
			   }
			   public String getPname() {
				      return this.pname;
				   }
				 
				   /**
				    * Sets the massage
				    */
				   public void setPname(String pname) {
				      this.pname = pname;
				   }
		
		
		
}
/**
 * Generated on Wed Nov 12 19:10:50 UTC 2014 by ObjGen 3.0
 */
