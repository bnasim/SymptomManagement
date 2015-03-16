package org.magnum.videoup.client;

import java.util.Calendar;
import java.util.Collection;





import java.util.concurrent.Callable;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 
 * This application uses ButterKnife. AndroidStudio has better support for
 * ButterKnife than Eclipse, but Eclipse was used for consistency with the other
 * courses in the series. If you have trouble getting the login button to work,
 * please follow these directions to enable annotation processing for this
 * Eclipse project:
 * 
 * http://jakewharton.github.io/butterknife/ide-eclipse.html
 * 
 */
public class LoginScreenActivity extends Activity {

	@InjectView(R.id.userName)
	protected EditText userName_;

	@InjectView(R.id.password)
	protected EditText password_;

	@InjectView(R.id.server)
	protected EditText server_;
	
	@InjectView(R.id.textView1)
	protected TextView textview_;
	//final String user = userName_.getText().toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);

		ButterKnife.inject(this);
		 // BroadCase Receiver Intent Object
		Intent alarmIntent = new Intent(getApplicationContext(), AlertDoctorBC.class);
        // Pending Intent Object
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Alarm Manager Object
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        // Alarm Manager calls BroadCast for every hour (1 * 60 * 60 * 1000), BroadCase check if there is any patient with 12 hours of severe pain or 16 hours of moderate pain. 
        
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 1 * 60 * 60 * 1000, pendingIntent);

	}
	 private void SavePreferences(String key, String value)
	  	{  
	  		SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE); 
	  	SharedPreferences.Editor editor = sharedPreferences.edit(); 
	  	editor.putString(key, value); 
	  	//editor.putString("MY_NAME", "nass"); 
	  	editor.commit();   }
	 
	@OnClick(R.id.loginButton)
	public void login() {
	final String user = userName_.getText().toString();
		String pass = password_.getText().toString();
		String server = server_.getText().toString();
		 SavePreferences("Currentuser", user);
		final PatientSvcApi svc = PatientSvc.init(server, user, pass);
           	   
		CallableTask.invoke(new Callable<String>() {

			@Override
			public String call() throws Exception {
				
				return svc.findUserrole(user);
			}
		}, new TaskCallback<String>() {

			@Override
			public void success(String result) {
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				textview_.setText(result);
			 if (textview_.getText().toString().equals("doctor"))	
			 {
				 Intent in = new Intent(getApplicationContext(),DoctorDashboard.class);    
				    startActivity(in);
				    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
			 }
			 else {
				 Intent in2 = new Intent(getApplicationContext(),PatientDashboard.class);    
				    startActivity(in2);
				    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
			 }
			}

			@Override
			public void error(Exception e) {
				Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						LoginScreenActivity.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
