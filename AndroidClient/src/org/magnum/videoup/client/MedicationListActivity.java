package org.magnum.videoup.client;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MedicationListActivity extends Activity {

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
         final String uname="admin";
       final  List<Medication> streamData = new ArrayList<Medication>();
       final PrescriptionAdapter mAdapter;
      mAdapter = new PrescriptionAdapter(this, 0, streamData);
      lf = this.getLayoutInflater();
		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<Medication>>() {

				@Override
				public Collection<Medication> call() throws Exception {
					return svc.getMedicationsByPhysician(uname);
				}
			}, new TaskCallback<Collection<Medication>>() {

				@Override
				public void success(Collection<Medication> result) {
					
					//List<String> names = new ArrayList<String>();
					//List<String> pains = new ArrayList<String>();
					//List<String> meds = new ArrayList<String>();
					streamData.addAll(result);
				//	}
				//	for (CheckIn c : result) {
				//		pains.add(c.getEatingState().toString());
				//	}
					//for (CheckIn c : result) {
				//		meds.add(c.getMedicineTaken().toString());
				//	}
					 headerView = (View)lf.inflate(R.layout.medicationhead, null, false);

				        videoList_.addHeaderView(headerView, null, false);
					videoList_.setAdapter(mAdapter);
					videoList_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						long mid = streamData.get(position).getId();
						Intent myIntent = new Intent(getApplicationContext(),MedicationDetailActivity.class);
						myIntent.putExtra("firstKey",mid);
						startActivity(myIntent);
						}
					});
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							MedicationListActivity.this,
							"Unable to fetch the medication list, please login again.",
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

}
