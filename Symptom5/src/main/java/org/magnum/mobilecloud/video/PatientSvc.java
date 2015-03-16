package org.magnum.mobilecloud.video;

import java.util.ArrayList;



import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.client.PatientSvcApi;
import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Alert;
import org.magnum.mobilecloud.video.repository.AlertRepository;
import org.magnum.mobilecloud.video.repository.CheckIn;
import org.magnum.mobilecloud.video.repository.CheckInRepository;
import org.magnum.mobilecloud.video.repository.Medication;
import org.magnum.mobilecloud.video.repository.MedicationRepository;
import org.magnum.mobilecloud.video.repository.Physician;
import org.magnum.mobilecloud.video.repository.PhysicianRepository;
import org.magnum.mobilecloud.video.repository.Patient;
import org.magnum.mobilecloud.video.repository.PatientRepository;
import org.magnum.mobilecloud.video.repository.User;
import org.magnum.mobilecloud.video.repository.UserRepository;
import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

/**
 * This simple VideoSvc allows clients to send HTTP POST requests with
 * videos that are stored in memory using a list. Clients can send HTTP GET
 * requests to receive a JSON listing of the videos that have been sent to
 * the controller so far. Stopping the controller will cause it to lose the history of
 * videos that have been sent to it because they are stored in memory.
 * 
 * Notice how much simpler this VideoSvc is than the original VideoServlet?
 * Spring allows us to dramatically simplify our service. Another important
 * aspect of this version is that we have defined a VideoSvcApi that provides
 * strong typing on both the client and service interface to ensure that we
 * don't send the wrong paraemters, etc.
 * 
 * This version of the VideoSvc uses @OneToMany for its Video/Category
 * operations.
 * 
 * @author jules
 *
 */

// Tell Spring that this class is a Controller that should 
// handle certain HTTP requests for the DispatcherServlet
@Controller
public class PatientSvc implements PatientSvcApi {
	
	// The VideoRepository that we are going to store our videos
	// in. We don't explicitly construct a VideoRepository, but
	// instead mark this object as a dependency that needs to be
	// injected by Spring. Our Application class has a method
	// annotated with @Bean that determines what object will end
	// up being injected into this member variable.
	//
	// Also notice that we don't even need a setter for Spring to
	// do the injection.
	//
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private PhysicianRepository physicians;
	
	@Autowired
	private CheckInRepository checkins;
	
	@Autowired
	private MedicationRepository medications;
	
	@Autowired
	private UserRepository users;
	
	@Autowired
	private AlertRepository alerts;

	// Receives POST requests to /video and converts the HTTP
	// request body, which should contain json, into a Video
	// object before adding it to the list. The @RequestBody
	// annotation on the Video parameter is what tells Spring
	// to interpret the HTTP request body as JSON and convert
	// it into a Video object to pass into the method. The
	// @ResponseBody annotation tells Spring to convert the
	// return value from the method back into JSON and put
	// it into the body of the HTTP response to the client.
	//
	// The Category for the Video must exist before this method is
	// called. You can use the addCategory() method and request path
	// to add a Category first.
	//
	// The VIDEO_SVC_PATH is set to "/video" in the VideoSvcApi
	// interface. We use this constant to ensure that the 
	// client and service paths for the VideoSvc are always
	// in synch.
	//
	@RequestMapping(value=PatientSvcApi.PATIENT_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addPatient(@RequestBody Patient p){
		
		 // It is important that the Video's Category already exist. If it
		 // doesn't, this call will throw an exception. 
		 patients.save(p);
		 return true;
	}
	@RequestMapping(value=PatientSvcApi.USER_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User u){
		
		users.save(u);
	return u;
	}
	@RequestMapping(value=PatientSvcApi.CHECKIN_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addCheckIn(@RequestBody CheckIn c){
		
		 // It is important that the Video's Category already exist. If it
		 // doesn't, this call will throw an exception. 
		 checkins.save(c);
		 return true;
	}
	@RequestMapping(value=PatientSvcApi.MEDICATION_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addMedication(@RequestBody Medication m){
		
		 // It is important that the Video's Category already exist. If it
		 // doesn't, this call will throw an exception. 
		 medications.save(m);
		 return true;
	}
	
	// Receives POST requests to /category and converts the HTTP
	// request body, which should contain json, into a Category
	// object before adding it to the list. The @RequestBody
	// annotation on the Category parameter is what tells Spring
	// to interpret the HTTP request body as JSON and convert
	// it into a Category object to pass into the method. The
	// @ResponseBody annotation tells Spring to convert the
	// return value from the method back into JSON and put
	// it into the body of the HTTP response to the client.
	//
	// The VIDEO_SVC_PATH is set to "/video" in the VideoSvcApi
	// interface. We use this constant to ensure that the 
	// client and service paths for the VideoSvc are always
	// in synch.
	//
	@RequestMapping(value=PatientSvcApi.DOCTOR_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addPhysician(@RequestBody Physician d){
		 physicians.save(d);
		 return true;
	}
	
	@RequestMapping(value=PatientSvcApi.ALERT_SVC_PATH, method=RequestMethod.POST)
	public @ResponseBody boolean addAlert(@RequestBody Alert a){
		 alerts.save(a);
		 return true;
	}
	
	// Receives GET requests to /video and returns the current
	// list of videos. Spring automatically converts
	// the list of videos to JSON because of the @ResponseBody
	// annotation.
	@RequestMapping(value=PatientSvcApi.PATIENT_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientList(){
		return Lists.newArrayList(patients.findAll());
	}
	@RequestMapping(value=PatientSvcApi.CHECKIN_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<CheckIn> getCheckInList(){
		return Lists.newArrayList(checkins.findAll());
	}
	@RequestMapping(value=PatientSvcApi.MEDICATION_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Medication> getMedicationList(){
		return Lists.newArrayList(medications.findAll());
	}
	@RequestMapping(value=PatientSvcApi.DOCTOR_SVC_PATH+"/{uname}/medication", method=RequestMethod.GET)

	public @ResponseBody Collection<Medication> getMedicationsByPhysician(@PathVariable("uname") String uname){
	// Tell Spring to use the "title" parameter in the HTTP request's query
	// string as the value for the title method parameter
	
		return medications.findByUname(uname);
	}
	
	@RequestMapping(value=PatientSvcApi.PATIENT_SVC_PATH+"/{uname}/checkin", method=RequestMethod.GET)

	public @ResponseBody Collection<CheckIn> getCheckInsByPatient(@PathVariable("uname") String uname){
	// Tell Spring to use the "title" parameter in the HTTP request's query
	// string as the value for the title method parameter
	
		return checkins.findByUname(uname);
	}
	
	@RequestMapping(value=PatientSvcApi.DOCTOR_SVC_PATH+"/{dname}/alert", method=RequestMethod.GET)

	public @ResponseBody Collection<Alert> getAlertsForDoctor(@PathVariable("dname") String dname){
	// Tell Spring to use the "title" parameter in the HTTP request's query
	// string as the value for the title method parameter
	
		return alerts.findByDname(dname);
	}
	
	@RequestMapping(value=PatientSvcApi.PATIENT_SVC_PATH+"/{pname}/medication", method=RequestMethod.GET)

	public @ResponseBody Collection<Medication> getMedicationsToPatient(@PathVariable("pname") String pname){
	// Tell Spring to use the "title" parameter in the HTTP request's query
	// string as the value for the title method parameter
	
		return medications.findByPatientName(pname);
	}
	@RequestMapping(value=PatientSvcApi.USERNAME_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody String findUserrole(
	// Tell Spring to use the "title" parameter in the HTTP request's query
	// string as the value for the title method parameter
	@RequestParam(PatientSvcApi.USERNAME_PARAMETER) String username
	){
User v = users.findByUsername(username);
		
        return  v.getRole();
	}
	
	// Receives GET requests to /video/{category} and returns the current
	// list of videos that are part of the specified category.
	@RequestMapping(value=PatientSvcApi.PATIENT_SVC_PATH+"/{doctor}", method=RequestMethod.GET)
	public @ResponseBody Collection<Patient> getPatientListForDoctor(@PathVariable("doctor") String uname){
		Physician p = physicians.findOne(uname);
		return (p != null) ? Lists.newArrayList(p.getPatients()) : new ArrayList<Patient>();
	}
	
	
	@RequestMapping(value = PatientSvcApi.MEDICATION_SVC_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Medication getMedicationById(@PathVariable(ID_PARAMETER) long id){
		
		return medications.findOne(id);
	}
	// Receives GET requests to /video/find and returns all Videos
	// that have a title (e.g., Video.name) matching the "title" request
	// parameter value that is passed by the client
	@RequestMapping(value=PatientSvcApi.PATIENT_TITLE_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Patient findByUname(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(USERNAME_PARAMETER) String username
	){
		return patients.findByUname(username);
	}
	
	@RequestMapping(value=PatientSvcApi.PATIENT_NAME_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Patient findByName(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(NAME_PARAMETER) String name
	){
		return patients.findByName(name);
	}
	
	@RequestMapping(value=PatientSvcApi.PHYSICIAN_NAME_SEARCH_PATH, method=RequestMethod.GET)
	public @ResponseBody Physician findPhysicianByUname(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(USERNAME_PARAMETER) String username
	){
		return physicians.findByUname(username);
	}
	
	@RequestMapping(value=PatientSvcApi.CHECKIN_EMERGENCY_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<CheckIn> findByCheckInDateTimeAndPainTypeAndUname(
			// Tell Spring to use the "title" parameter in the HTTP request's query
			// string as the value for the title method parameter
			@RequestParam(CHECKINDATETIME_PARAMETER) long checkindatetime,@RequestParam(PAINTYPE_PARAMETER) String paintype,@RequestParam(USERNAME_PARAMETER) String uname
	){
		return checkins.findByCheckInDateTimeAndPainTypeAndUname(checkindatetime,paintype,uname);
	}
	
	@RequestMapping(value=PatientSvcApi.MEDICATION_SVC_PATH+ "/{id}", method=RequestMethod.PATCH)
	public @ResponseBody Medication updateMedication(@RequestBody Medication m,@PathVariable(ID_PARAMETER) long id){
		 medications.save(m);
		return m;
	}
	@RequestMapping(value = PatientSvcApi.MEDICATION_SVC_PATH + "/{pdate}/{pname}", method = RequestMethod.GET)
	public @ResponseBody Medication getMedicationByDateAndName(@PathVariable(PRESCRIPTIONDATE_PARAMETER) long pdate,@PathVariable(PATIENTNAME_PARAMETER) String pname){
		
		return medications.findByPrescriptionDateAndPatientName(pdate,pname);
	}
	
}

