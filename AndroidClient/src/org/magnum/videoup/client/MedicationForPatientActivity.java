package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MedicationForPatientActivity extends Activity {

	@InjectView(R.id.videoList)
	protected ListView videoList_;
	LayoutInflater lf;
    View headerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_list);

		ButterKnife.inject(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}

	private void refreshVideos() {
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
         final String pname="nasim";
       final  List<Medication> streamData = new ArrayList<Medication>();
       final MedicationAdapter mAdapter;
      mAdapter = new MedicationAdapter(this, 0, streamData);
      lf = this.getLayoutInflater();
		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<Medication>>() {

				@Override
				public Collection<Medication> call() throws Exception {
					return svc.getMedicationsToPatient(pname);
				}
			}, new TaskCallback<Collection<Medication>>() {

				@Override
				public void success(Collection<Medication> result) {
					
					//List<String> names = new ArrayList<String>();
					//List<String> pains = new ArrayList<String>();
					//List<String> meds = new ArrayList<String>();
					   headerView = (View)lf.inflate(R.layout.medicationhead, null, false);

				        videoList_.addHeaderView(headerView, null, false);
					streamData.addAll(result);
				
					videoList_.setAdapter(mAdapter);
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							MedicationForPatientActivity.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(MedicationForPatientActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
