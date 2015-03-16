package org.magnum.videoup.client;

import java.util.Collection;




import java.util.concurrent.Callable;

import org.magnum.videoup.client.TestData;
import org.magnum.videoup.client.Patient;

import retrofit.client.Response;

import android.app.Activity;
import android.content.Intent;
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
public class AddPatient extends Activity {

	@InjectView(R.id.userName)
	protected EditText userName_;

	@InjectView(R.id.password)
	protected EditText password_;

	@InjectView(R.id.server)
	protected EditText server_;
	
	@InjectView(R.id.textView1)
	protected TextView textview_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);

		ButterKnife.inject(this);
	}

	@OnClick(R.id.loginButton)
	public void login() {
	 String user = userName_.getText().toString();
		String pass = password_.getText().toString();
		String server = server_.getText().toString();

		final PatientSvcApi svc = PatientSvc.init(server, user, pass);
	//	final Patient patient = TestData.randomVideo();
	//	CallableTask.invoke(new Callable<Patient>() {

		//	@Override
		//	public Patient call() throws Exception {
				
		//		return svc.addPatient(patient);
			}
	//	}, new TaskCallback<Patient>() {

		//	@Override
		//	public void success(Patient result) {
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				//textview_.setText(result);
				
		//	}

		//	@Override
		//	public void error(Exception e) {
			//	Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);
				
		//		Toast.makeText(
		//				AddPatient.this,
		//				"Login failed, check your Internet connection and credentials.",
		//				Toast.LENGTH_SHORT).show();
		//	}
	//	});
//	}

		
	
}