package org.magnum.videoup.client;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Date;
import java.util.List;




import java.util.concurrent.Callable;

import org.magnum.videoup.client.TestData;
import org.magnum.videoup.client.Patient;

import retrofit.client.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 
 * This application uses ButterKnife. AndroidStudio has better support for
 * ButterKnife than Eclipse, but Eclipse was used for consistency with the other
 * courses in the series. If you have trouble getting the login button to work,
 * please follow these directions to enable annotation processing for this
 * Eclipse project:
 * 
 * http://jakewharton.github.io/butterknife/ide-eclipse.html
 * 
 */
public class Add_Medication extends Activity {

	@InjectView(R.id.patientName)
	protected EditText patientName_;

	@InjectView(R.id.med1Name)
	protected EditText med1Name_;

	@InjectView(R.id.med2Name)
	protected EditText med2Name_;
	
	@InjectView(R.id.rule)
	protected EditText rule_;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medication);

		ButterKnife.inject(this);
	}

	@OnClick(R.id.addbutton)
	public void login() {
	 String patient = patientName_.getText().toString();
		String med1 = med1Name_.getText().toString();
		String med2 = med2Name_.getText().toString();
		String rule = rule_.getText().toString();
		Date beginupd = new Date();
		long milli = beginupd.getTime();
		 final Medication m = new Medication();
		 m.setUname("admin");
		 m.setPatientName(patient);
   	  m.setMedicine1Name(med1);
   	  m.setMedicine2Name(med2);
   	  m.setInstruction(rule);
     m.setPrescriptionDate(milli);
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
		if (svc != null) {
			CallableTask.invoke(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					
					return svc.addMedication(m);
				}
			}, new TaskCallback<Boolean>() {

				@Override
				public void success(Boolean v) {
					// OAuth 2.0 grant was successful and we
					// can talk to the server, open up the video listing
					//textview_.setText(result);
					Toast.makeText(
							Add_Medication.this,
							"Medication added successfully.",
							Toast.LENGTH_SHORT).show();
					
				}
				@Override
				public void error(Exception e) {
					Toast.makeText(
							Add_Medication.this,
							"Unable to add medication, please login again.",
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

		
	
}