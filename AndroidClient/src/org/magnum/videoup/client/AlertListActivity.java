package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class AlertListActivity extends Activity {

	@InjectView(R.id.videoList)
	protected ListView alertList_;

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
         final String dname="nasim";
       final  List<Alert> streamData = new ArrayList<Alert>();
       final AlertAdapter mAdapter;
      mAdapter = new AlertAdapter(this, 0, streamData);
		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<Alert>>() {

				@Override
				public Collection<Alert> call() throws Exception {
					return svc.getAlertsForDoctor(dname);
				}
			}, new TaskCallback<Collection<Alert>>() {

				@Override
				public void success(Collection<Alert> result) {
					
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
					alertList_.setAdapter(mAdapter);
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							AlertListActivity.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(AlertListActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
