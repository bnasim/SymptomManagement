package org.magnum.videoup.client;

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

public class DoctorProfile extends Activity {

	@InjectView(R.id.edit1)
	protected EditText edit1_;

	@InjectView(R.id.edit2)
	protected EditText edit2_;

	@InjectView(R.id.edit3)
	protected EditText edit3_;
	
	@InjectView(R.id.edit4)
	protected EditText edit4_;
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
		 SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
		 final   String cuser = sharedPreferences.getString("Currentuser", ""); 
		//final String duname="admin";
		if (svc != null) {
			CallableTask.invoke(new Callable<Physician>() {

				@Override
				public Physician call() throws Exception {
					return svc.findPhysicianByUname(cuser);
				}
			}, new TaskCallback<Physician>() {

				@Override
				public void success(Physician result) {
					
					
						
					edit1_.setText("Name: "+ result.getDname());
					edit2_.setText("Address: "+result.getAddress());
					btn_.setVisibility(0);
					
				
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							DoctorProfile.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

}
