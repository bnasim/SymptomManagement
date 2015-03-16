package org.magnum.videoup.client;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CheckInNow extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkinnow);
		  RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
		final  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

	        rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch(checkedId){
	                    case R.id.radioWell:
	                        // do operations specific to this selection
	                    	 SavePreferences("Bad", "Well");
	                    break;

	                    case R.id.radioModerate:
	                        // do operations specific to this selection
	                    	 SavePreferences("Bad", "Moderate");
	                    break;

	                    case R.id.radioSevere:
	                        // do operations specific to this selection
	                    	 SavePreferences("Bad", "Severe");
	                    break;

	                }


	            }
	        });
	        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);


	        rg2.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch(checkedId){
	                    case R.id.radioYes:
	                        // do operations specific to this selection
	                    	 
	                    	prefs.edit().putBoolean("Medication", true).commit();
	                    	
	                    break;

	                    case R.id.radioNo:
	                        // do operations specific to this selection
	                    	prefs.edit().putBoolean("Medication", false).commit();
	                    break;

	                   

	                }


	            }
	        });
	        RadioGroup rg3 = (RadioGroup) findViewById(R.id.radioGroup3);


	        rg3.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch(checkedId){
	                   
	                   case R.id.radio1:
	                        // do operations specific to this selection
	                	   SavePreferences("Eating", "No");
	                    break;

	                    case R.id.radio2:
	                        // do operations specific to this selection
	                    	SavePreferences("Eating", "Some");
	                    break;
	                    
	                    case R.id.radio3:
	                        // do operations specific to this selection
	                    	SavePreferences("Eating", "I can't eat");
	                    break;

	                }


	            }
	        });
	        Button button1 = (Button) findViewById(R.id.nextButton);
		      button1.setOnClickListener(new View.OnClickListener() {
		              public void onClick(View view) {
		                  Intent myIntent = new Intent(view.getContext(), CheckInStep2.class);
		                  startActivityForResult(myIntent, 0);
		              }
		 
		     });   
		
	}
	  private void SavePreferences(String key, String value)
  	{  
  		SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE); 
  	SharedPreferences.Editor editor = sharedPreferences.edit(); 
  	editor.putString(key, value); 
  	//editor.putString("MY_NAME", "nass"); 
  	editor.commit();   }
}
