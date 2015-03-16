package org.magnum.videoup.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PatientDashboard extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.patient_dashboard);
	   //   tracker = GoogleAnalyticsTracker.getInstance();
	   //   tracker.startNewSession("UA-16562358-1",20, this);
	    //   AdView adView = new AdView(this, AdSize.BANNER, "a14f2814dce81d6");    // Lookup your LinearLayout assuming it’s been given    // the attribute android:id="@+id/mainLayout"   
	     //LinearLayout layout = (LinearLayout)findViewById(R.id.mainLayout);    // Add the adView to it  
	    // layout.addView(adView);    // Initiate a generic request to load it with an ad   
	     //adView.loadAd(new AdRequest());	
	      
	      Button button1 = (Button) findViewById(R.id.b1);
	      button1.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), CheckInNow.class);
	                  startActivityForResult(myIntent, 0);
	              }
	 
	     });
	      Button button2 = (Button) findViewById(R.id.b2);
	      button2.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), CheckInListActivity.class);
	                  startActivityForResult(myIntent, 0);
	              }
	 
	     }); 
	      
	     Button button3 = (Button) findViewById(R.id.b3);
	      button3.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), MedicationForPatientActivity.class);
	                  startActivityForResult(myIntent, 0);
	              }
	 
	     });
	      
	     Button button4 = (Button) findViewById(R.id.b4);
	      button4.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), AlarmListActivity.class);
	                  
	                //  tracker.trackEvent("Practice","Click","Practice", 0);   
	                  
	                  startActivityForResult(myIntent, 0);
	              }
	 
	     });
	      Button button5 = (Button) findViewById(R.id.b5);
	      button5.setOnClickListener(new View.OnClickListener() {
	              public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), PatientProfile.class);
	                  
	                 // tracker.trackEvent("Test","Click","Test", 0);  
	                  startActivityForResult(myIntent, 0);
	              }
	 
	     });
	      
	      
	   
	     
	}

}
