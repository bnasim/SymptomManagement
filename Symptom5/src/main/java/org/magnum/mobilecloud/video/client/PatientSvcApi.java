package org.magnum.mobilecloud.video.client;

import java.util.Collection;





import org.magnum.mobilecloud.video.repository.Alert;
import org.magnum.mobilecloud.video.repository.CheckIn;
import org.magnum.mobilecloud.video.repository.Medication;
import org.magnum.mobilecloud.video.repository.Patient;
import org.magnum.mobilecloud.video.repository.Physician;
import org.magnum.mobilecloud.video.repository.User;
import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * This interface defines an API for a PatientSvc that uses @OneToMany. The
 * interface is used to provide a contract for client/server
 * interactions. The interface is annotated with Retrofit
 * annotations so that clients can automatically convert the
 * 
 * 
 * @author Nasim
 *
 */
public interface PatientSvcApi {
	
	
	public static final String PRESCRIPTIONDATE_PARAMETER = "pdate";
	public static final String ID_PARAMETER = "id";
	public static final String NAME_PARAMETER = "name";
	public static final String USERNAME_PARAMETER = "username";
	public static final String PATIENTNAME_PARAMETER = "pname";
	public static final String CHECKINDATETIME_PARAMETER = "checkindatetime";
	public static final String PAINTYPE_PARAMETER = "paintype";
	public static final String TOKEN_PATH = "/oauth/token";
	// The path where we expect the VideoSvc to live
	public static final String PATIENT_SVC_PATH = "/patient";
	public static final String USER_SVC_PATH = "/user";
	public static final String CHECKIN_SVC_PATH = "/checkin";
	
	// The path were we expect Categories to live
	public static final String DOCTOR_SVC_PATH = "/physician";
	public static final String MEDICATION_SVC_PATH = "/physician/medication";
	public static final String ALERT_SVC_PATH = "/physician/alert";

	// The path to search videos by title
	public static final String PATIENT_TITLE_SEARCH_PATH = PATIENT_SVC_PATH + "/find";
	public static final String PATIENT_NAME_SEARCH_PATH = PATIENT_SVC_PATH + "/findbyname";
	public static final String PHYSICIAN_NAME_SEARCH_PATH = DOCTOR_SVC_PATH + "/find";
	public static final String USERNAME_SEARCH_PATH = USER_SVC_PATH + "/search/findUserrole";
	public static final String CHECKIN_EMERGENCY_PATH = CHECKIN_SVC_PATH + "/emergency";
	@GET(PATIENT_SVC_PATH)
	public Collection<Patient> getPatientList();
	@GET(CHECKIN_SVC_PATH)
	public Collection<CheckIn> getCheckInList();
	
	@POST(PATIENT_SVC_PATH)
	public boolean addPatient(@Body Patient p);
	
	@POST(ALERT_SVC_PATH)
	public boolean addAlert(@Body Alert a);
	
	@GET(USERNAME_SEARCH_PATH)
	public String findUserrole(@Query(USERNAME_PARAMETER) String username);
	
	@GET(PATIENT_TITLE_SEARCH_PATH)
	public Patient findByUname(@Query(USERNAME_PARAMETER) String username);
	
	@GET(PATIENT_NAME_SEARCH_PATH)
	public Patient findByName(@Query(NAME_PARAMETER) String name);
	
	@GET(PHYSICIAN_NAME_SEARCH_PATH)
	public Physician findPhysicianByUname(@Query(USERNAME_PARAMETER) String username);
	
	
	@GET(CHECKIN_EMERGENCY_PATH)
	public Collection<CheckIn> findByCheckInDateTimeAndPainTypeAndUname(@Query(CHECKINDATETIME_PARAMETER) long checkindatetime,@Query(PAINTYPE_PARAMETER) String paintype,@Query(USERNAME_PARAMETER) String uname);
	
	@GET(PATIENT_SVC_PATH+"/{doctor}")
	public Collection<Patient> getPatientListForDoctor(@Path("doctor") String uname);
	
	@POST(DOCTOR_SVC_PATH)
	public boolean addPhysician(@Body Physician p);
	
	@POST(CHECKIN_SVC_PATH)
	public boolean addCheckIn(@Body CheckIn c);
	
	@POST(MEDICATION_SVC_PATH)
	public boolean addMedication(@Body Medication m);
	
	@GET(MEDICATION_SVC_PATH + "/{id}")
	public Medication getMedicationById(@Path("id") long id);
	
	@POST(USER_SVC_PATH)
	public User addUser(@Body User u);
	
	@GET(PATIENT_SVC_PATH+"/{uname}/checkin")
	public Collection<CheckIn> getCheckInsByPatient(@Path("uname") String uname);
	
	@GET(DOCTOR_SVC_PATH+"/{dname}/alert")
	public Collection<Alert> getAlertsForDoctor(@Path("dname") String dname);
	
	@GET(DOCTOR_SVC_PATH+"/{uname}/medication")
	public Collection<Medication> getMedicationsByPhysician(@Path("uname") String uname);
	
	@GET(PATIENT_SVC_PATH+"/{pname}/medication")
	public Collection<Medication> getMedicationsToPatient(@Path("pname") String pname);
	
	@PATCH(MEDICATION_SVC_PATH + "/{id}")
	public Medication updateMedication(@Body Medication m,@Path("id") long id);
	
	@GET(MEDICATION_SVC_PATH + "/{pdate}/{pname}")
	public Medication getMedicationByDateAndName(@Path("date") long id,@Path("pname") String pname);
	
}
