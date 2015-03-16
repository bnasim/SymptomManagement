package org.magnum.videoup.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

public class TestBC extends Activity {
	
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
	
	Date beginupd = new Date();
	final long millidate = beginupd.getTime(); 
	final long milli = 1416767230674L ; 
	final String pain="severe";
	final String uname="user1";
	 final Alert a = new Alert();
   	  a.setMessage("The following patient is in critical state");
   	  a.setDname("Paul");
   	  a.setPname("nasim");
      a.setAlertDate(millidate);
	CallableTask.invoke(new Callable<Collection<CheckIn>>() {

		@Override
		public Collection<CheckIn> call() throws Exception {
			
			return svc.findByCheckInDateTimeAndPainTypeAndUname(milli,pain,uname);
		}
	}, new TaskCallback<Collection<CheckIn>>() {

		@Override
		public void success(Collection<CheckIn> result) {
			if (result.size() >2)
			{
				CallableTask.invoke(new Callable<Boolean>() {

					@Override
					public Boolean call() throws Exception {
						
						return svc.addAlert(a);
					}
				}, new TaskCallback<Boolean>() {

					@Override
					public void success(Boolean result) {
						
						 Toast.makeText(TestBC.this, "Alert has been sent", Toast.LENGTH_SHORT).show();	
					}

					@Override
					public void error(Exception e) {
						
						
						
					}
				});
			}
			
		}

		@Override
		public void error(Exception e) {
			
		}
	});
}
}
