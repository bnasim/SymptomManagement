package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.magnum.videoup.client.TestData;
import org.magnum.videoup.client.PatientSvcApi;
import org.magnum.videoup.client.Video;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ObjectCollectionActivity extends Activity{
	private final String TEST_URL_WITH_ONE_TO_MANY = "http://10.0.3.2:8080";
	private ListView videoList;
	private Collection<Video> videos;
	
   private  PatientSvcApi videoServiceWithOneToMany;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_add);



	videoList = (ListView) findViewById(R.id.videoList);
	}
	public void send(View view){
	
		final PatientSvcApi videoServiceWithOneToMany = new RestAdapter.Builder()
		.setEndpoint(TEST_URL_WITH_ONE_TO_MANY).setLogLevel(LogLevel.FULL).build()
		.create(PatientSvcApi.class);
		final Video video = TestData.randomVideo();
		CallableTask.invoke(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				
				return videoServiceWithOneToMany.addCategory(video.getCategory());
			}
		}, new TaskCallback<Boolean>() {

			@Override
			public void success(Boolean v) {
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				//textview_.setText(result);
				
			}

			@Override
			public void error(Exception e) {
				Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						ObjectCollectionActivity.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
		CallableTask.invoke(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				
				return videoServiceWithOneToMany.addVideo(video);
			}
		}, new TaskCallback<Boolean>() {

			@Override
			public void success(Boolean v) {
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				//textview_.setText(result);
				
			}

			@Override
			public void error(Exception e) {
				Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						ObjectCollectionActivity.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	public void getVideoList(View view){
	Toast.makeText(getApplicationContext(), "Requesting for Video...", Toast.LENGTH_SHORT).show();
	videos = videoServiceWithOneToMany.getVideoList();
	List<String> videosNames = new ArrayList<String>();
	for (Video v : videos){
	videosNames.add(v.getName());
	}
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,videosNames);
	videoList.setAdapter(adapter);
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



