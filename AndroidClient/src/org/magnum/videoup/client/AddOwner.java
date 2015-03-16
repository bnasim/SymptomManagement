package org.magnum.videoup.client;

import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;



import org.magnum.videoup.client.unsafe.EasyHttpClient;
import org.magnum.videoup.client.oauth.SecuredRestBuilder;
import org.magnum.videoup.client.PatientSvcApi;
import org.magnum.videoup.client.Patient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;

public class AddOwner extends Activity {
	
	protected EditText userName_;

	
	protected EditText password_;

	
	protected EditText server_;
	private final String TEST_URL = "https://localhost:8443";

	private final String USERNAME1 = "admin";
	private final String USERNAME2 = "user0";
	private final String PASSWORD = "pass";
	private final String CLIENT_ID = "mobile";
	
	protected TextView textview_;
private EditText name;

private EditText condition;

private PatientSvcApi readWriteVideoSvcUser1;
private ListView videoList;
private Collection<Patient> patients;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_add);



 
name = (EditText) findViewById(R.id.getName);

//condition = (EditText) findViewById(R.id.getDuration);
userName_ = (EditText) findViewById(R.id.userName);
password_ = (EditText) findViewById(R.id.password);
server_ = (EditText) findViewById(R.id.server);
textview_ = (EditText) findViewById(R.id.textView1);

videoList = (ListView) findViewById(R.id.videoList);
}
public void send(View view){
String nameS = name.getText().toString();

//String mediS = name.getText().toString();
//String condiS = condition.getText().toString();
String user = userName_.getText().toString();
String pass = password_.getText().toString();
String server = server_.getText().toString();
final PatientSvcApi readWriteVideoSvcUser1 = new SecuredRestBuilder()
.setLoginEndpoint(server + PatientSvcApi.TOKEN_PATH)
.setUsername(user)
.setPassword(pass)
.setClientId(CLIENT_ID)
.setClient(
		new ApacheClient(new EasyHttpClient()))
.setEndpoint(server).setLogLevel(LogLevel.FULL).build()
.create(PatientSvcApi.class);


final Owner or = new Owner();
or.setFirstName("Manzar");
or.setLastName("Mir");
or.setCity("Dubai");
final Pet p = new Pet();
p.setName("teddy");


CallableTask.invoke(new Callable<Owner>() {

	@Override
	public Owner call() throws Exception {
		
		return readWriteVideoSvcUser1.addOwner(or);
	}
}, new TaskCallback<Owner>() {

	@Override
	public void success(Owner i) {
		// OAuth 2.0 grant was successful and we
		// can talk to the server, open up the video listing
		//textview_.setText(result);
		
	}

	@Override
	public void error(Exception e) {
		Log.e(AddOrder.class.getName(), "Error logging in via OAuth.", e);
		
		Toast.makeText(
				AddOwner.this,
				"Login failed, check your Internet connection and credentials.",
				Toast.LENGTH_SHORT).show();
	}
});

Toast.makeText(getApplicationContext(), "Item " + or.getFirstName()
+ " added", Toast.LENGTH_SHORT).show();


}
public void getVideoList(View view){
	Intent in = new Intent(getApplicationContext(),AddOrder.class);    
    startActivity(in);
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.video_list, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
int id = item.getItemId();
if (id == R.id.action_settings) {
return true;
}
return super.onOptionsItemSelected(item);
}
}