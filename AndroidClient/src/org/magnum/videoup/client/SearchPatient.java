package org.magnum.videoup.client;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchPatient extends Activity {
	
	@InjectView(R.id.edittext1)
	protected EditText edittext1_;

	@InjectView(R.id.videoList)
	protected ListView patientList_;
	
	@InjectView(R.id.textView1)
	protected TextView textview1_;
	@InjectView(R.id.textView2)
	protected TextView textview2_;
	@InjectView(R.id.textView3)
	protected TextView textview3_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpatient);

		ButterKnife.inject(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		
	}
	@OnClick(R.id.findbutton)
	public void find() {
	final String pname = edittext1_.getText().toString();
		
	final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
	//final String puname="user1";
	
	if (svc != null) {
		CallableTask.invoke(new Callable<Patient>() {

			@Override
			public Patient call() throws Exception {
				return svc.findByName(pname);
			}
		}, new TaskCallback<Patient>() {

			@Override
			public void success(Patient result) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:mm a");
				String dateString = sdf.format(result.getDob());    
					
				textview1_.setText(result.getName());
				textview2_.setText(dateString);
				textview3_.setText(result.getAddress());
				
			
				
			}
			@Override
			public void error(Exception e) {
				
				
				Toast.makeText(
						SearchPatient.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	}
	
	

}
