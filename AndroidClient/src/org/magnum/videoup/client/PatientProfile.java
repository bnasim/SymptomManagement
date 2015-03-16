package org.magnum.videoup.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.view.View;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class PatientProfile extends Activity {

	@InjectView(R.id.edit1)
	protected EditText edit1_;

	@InjectView(R.id.edit2)
	protected EditText edit2_;

	@InjectView(R.id.edit3)
	protected EditText edit3_;
	
	@InjectView(R.id.updateBtn)
	protected Button btn_;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicationdetail);

		ButterKnife.inject(this);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}

	private void refreshVideos() {
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
		//final String puname="user1";
		 SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
		 final   String cuser = sharedPreferences.getString("Currentuser", ""); 
		if (svc != null) {
			CallableTask.invoke(new Callable<Patient>() {

				@Override
				public Patient call() throws Exception {
					return svc.findByUname(cuser);
				}
			}, new TaskCallback<Patient>() {

				@Override
				public void success(Patient result) {
					
					SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:mm a");
					String dateString = sdf.format(result.getDob());    
						
					edit1_.setText("Name: " +result.getName());
					edit2_.setText("Date of Birth: " +dateString);
					edit3_.setText("Address: " +result.getAddress());
					btn_.setVisibility(0);
				
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							PatientProfile.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

}
