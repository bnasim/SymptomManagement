package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CheckInListActivity extends Activity {

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
		 SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
		 final   String cuser = sharedPreferences.getString("Currentuser", ""); 
       //  final String uname="user1";
       final  List<CheckIn> streamData = new ArrayList<CheckIn>();
       final CheckInAdapter mAdapter;
      mAdapter = new CheckInAdapter(this, 0, streamData);
      lf = this.getLayoutInflater();
		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<CheckIn>>() {

				@Override
				public Collection<CheckIn> call() throws Exception {
					return svc.getCheckInsByPatient(cuser);
				}
			}, new TaskCallback<Collection<CheckIn>>() {

				@Override
				public void success(Collection<CheckIn> result) {
					
					
					
					
			        headerView = (View)lf.inflate(R.layout.headerview, null, false);

			        videoList_.addHeaderView(headerView, null, false);
					streamData.addAll(result);
				
					videoList_.setAdapter(mAdapter);
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							CheckInListActivity.this,
							"Unable to fetch the patient list, please login again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(CheckInListActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
