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
import android.view.LayoutInflater;
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

public class PatientListActivity extends Activity {
	
	

	@InjectView(R.id.patientList)
	protected ListView patientList_;
	
	
	LayoutInflater lf;
    View headerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_list);

		ButterKnife.inject(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}
	
	
	private void refreshVideos() {
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
    //    final String duname="admin";
		 SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
		 final   String cuser = sharedPreferences.getString("Currentuser", ""); 
        final  List<Patient> streamData = new ArrayList<Patient>();
        final PatientAdapter mAdapter;
       mAdapter = new PatientAdapter(this, 0, streamData);
       lf = this.getLayoutInflater();
		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<Patient>>() {

				@Override
				public Collection<Patient> call() throws Exception {
					return svc.getPatientListForDoctor(cuser);
				}
			}, new TaskCallback<Collection<Patient>>() {

				@Override
				public void success(Collection<Patient> result) {
					streamData.addAll(result);
					headerView = (View)lf.inflate(R.layout.patienthead, null, false);

			        patientList_.addHeaderView(headerView, null, false);
						patientList_.setAdapter(mAdapter);
						patientList_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							String mid = streamData.get(position-1).getUname();
							Intent myIntent = new Intent(getApplicationContext(),PatientCheckIn.class);
							myIntent.putExtra("firstKey",mid);
							startActivity(myIntent);
							}
						});
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							PatientListActivity.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(PatientListActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
