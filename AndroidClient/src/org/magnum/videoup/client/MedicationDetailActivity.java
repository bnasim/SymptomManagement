package org.magnum.videoup.client;

import java.util.ArrayList;
import android.view.View;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MedicationDetailActivity extends Activity {

	@InjectView(R.id.edit1)
	protected EditText edit1_;

	@InjectView(R.id.edit2)
	protected EditText edit2_;

	@InjectView(R.id.edit3)
	protected EditText edit3_;
	
	@InjectView(R.id.edit4)
	protected EditText edit4_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicationdetail);

		ButterKnife.inject(this);
		refreshVideos();
		   Button button = (Button) findViewById(R.id.updateBtn);
		      button.setOnClickListener(new View.OnClickListener() {
		              public void onClick(View view) {
		            	  final String ptname = edit1_.getText().toString();
		final String medicine1 = edit2_.getText().toString();
		final String medicine2 = edit3_.getText().toString();
		final String instruction = edit4_.getText().toString();
		Intent myIntent = getIntent();
		final long firstKey = myIntent.getLongExtra("firstKey",1);
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(getBaseContext());
		
	            	  final Medication m = new Medication();
	            	  Date beginupd = new Date();
	          		long milli = beginupd.getTime();
	            	  m.setId(firstKey);
	            	  m.setUname("admin");
	            	  m.setPatientName(ptname);
	            	  m.setMedicine1Name(medicine1);
	            	  m.setMedicine2Name(medicine2);
	            	  m.setInstruction(instruction);
	            	  m.setPrescriptionDate(milli);
	            	  CallableTask.invoke(new Callable<Medication>() {

	      				@Override
	      				public Medication call() throws Exception {
	      					return svc.updateMedication(firstKey,m);
	      				}
	      			}, new TaskCallback<Medication>() {

	      				@Override
	      				public void success(Medication result) {
	      					
	      					
	      					Toast.makeText(
	      							MedicationDetailActivity.this,
	      							"Successfully updated",
	      							Toast.LENGTH_SHORT).show();		
	      					
	      					
	      				
	      					
	      				}

	      				@Override
	      				public void error(Exception e) {
	      					Toast.makeText(
	      							MedicationDetailActivity.this,
	      							"Unable to fetch the patient list, please login again.",
	      							Toast.LENGTH_SHORT).show();

	      					
	      				}
	      			});
	              }
		      });
		      }
		    
	   


	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}

	private void refreshVideos() {
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
		Intent myIntent = getIntent();
		final long firstKey = myIntent.getLongExtra("firstKey",1);
       final  List<Medication> streamData = new ArrayList<Medication>();
       final PrescriptionAdapter mAdapter;
      mAdapter = new PrescriptionAdapter(this, 0, streamData);
		if (svc != null) {
			CallableTask.invoke(new Callable<Medication>() {

				@Override
				public Medication call() throws Exception {
					return svc.getMedicationById(firstKey);
				}
			}, new TaskCallback<Medication>() {

				@Override
				public void success(Medication result) {
					
					
					edit1_.setText(result.getPatientName());	
					edit2_.setText(result.getMedicine1Name());
					edit3_.setText(result.getMedicine2Name());
					edit4_.setText(result.getInstruction());
					
					
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							MedicationDetailActivity.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

}
