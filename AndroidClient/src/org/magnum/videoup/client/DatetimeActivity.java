package org.magnum.videoup.client;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DatetimeActivity extends Activity{
	
	// public static final String MyPREFERENCES = "MyPrefs" ;
	 public static final String Name1 = "nameKey"; 
	 public static final String Name2 = "phoneKey"; 
	 public static final String Name3 = "emailKey"; 
	 TextView name ;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.date);
	  name = (TextView) findViewById(R.id.textView1);
	  
	  //Generate list View from ArrayList
	  SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
	     String strSavedMem1 = sharedPreferences.getString("Name1", ""); 
	     String strSavedMem2 = sharedPreferences.getString("Name2", ""); 
	     String strSavedMem3 = sharedPreferences.getString("Name3", ""); 
	    
	       name.setText(strSavedMem1);
	       final   LinearLayout ep7 = (LinearLayout) findViewById(R.id.linearLayout1);
	       final   LinearLayout ep10 = (LinearLayout) findViewById(R.id.linearLayout2);
	       final   LinearLayout ep11 = (LinearLayout) findViewById(R.id.linearLayout3);
	       final   LinearLayout ep12 = (LinearLayout) findViewById(R.id.linearLayout4);
	 if (strSavedMem1=="Afghanistan")
	 {
		 ep7.setVisibility(View.VISIBLE);
	        ep10.setVisibility(View.VISIBLE);
	        ep11.setVisibility(View.GONE);
	        ep12.setVisibility(View.GONE);
	 }
	 else {
		 ep11.setVisibility(View.VISIBLE);
	        ep12.setVisibility(View.VISIBLE);
	        ep7.setVisibility(View.GONE);
	        ep10.setVisibility(View.GONE);
	 }
	 }

}
