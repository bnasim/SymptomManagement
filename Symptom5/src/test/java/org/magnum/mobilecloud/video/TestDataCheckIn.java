package org.magnum.mobilecloud.video;

import java.util.UUID;



import org.magnum.mobilecloud.video.repository.Category;
import org.magnum.mobilecloud.video.repository.CheckIn;
import org.magnum.mobilecloud.video.repository.Patient;
import org.magnum.mobilecloud.video.repository.Physician;
import org.magnum.mobilecloud.video.repository.Video;


import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is a utility class to aid in the construction of
 * Video objects with random names, urls, and durations.
 * The class also provides a facility to convert objects
 * into JSON using Jackson, which is the format that the
 * VideoSvc controller is going to expect data in for
 * integration testing.
 * 
 * @author jules
 *
 */
public class TestDataCheckIn {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Construct and return a Video object with a
	 * rnadom name, url, and duration.
	 * 
	 * @return
	 */
	public static Patient randomCheckIn() {
		// Information about the video
		// Construct a random identifier using Java's UUID class
		String uname = UUID.randomUUID().toString();
		Boolean medicineTaken = true;
		String painType = "Severe";
		Boolean eatingState = true;
		//String patient = "Patient-"+UUID.randomUUID().toString();
	Patient p = new Patient();
		p.setUname("nasim");
		p.setName("banu");
		p.setDob(123);
		
		
		//long dob = 60 * (int)Math.rint(Math.random() * 60) * 1000; // random time up to 1hr
	//	CheckIn c = new CheckIn(medicineTaken, painType, eatingState);
	//	c.setPatient(p);
		return p;
	}
	
	/**
	 * Construct and return a Video object with a
	 * rnadom name, url, and duration.
	 * 
	 * @return
	 */
	
	
	/**
	 *  Convert an object to JSON using Jackson's ObjectMapper
	 *  
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
