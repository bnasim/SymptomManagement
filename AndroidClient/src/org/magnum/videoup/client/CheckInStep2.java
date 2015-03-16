package org.magnum.videoup.client;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CheckInStep2 extends Activity {

	@InjectView(R.id.textView1)
	protected TextView textView1_;
	
	@InjectView(R.id.textView2)
	protected TextView textView2_;
	@InjectView(R.id.textView3)
	protected TextView textView3_;
	@InjectView(R.id.textView4)
	protected TextView textView4_;
	int mYear1;
	int mMonth1;
	int mDay1;
	int mHour1;
	int mMinute1;
	int mYear2;
	int mMonth2;
	int mDay2;
	int mHour2;
	int mMinute2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkinstep2);

		ButterKnife.inject(this);
		 final   LinearLayout ep7 = (LinearLayout) findViewById(R.id.linearLayout1);
	       final   LinearLayout ep10 = (LinearLayout) findViewById(R.id.linearLayout2);
	       final   LinearLayout ep11 = (LinearLayout) findViewById(R.id.linearLayout3);
	       final   LinearLayout ep12 = (LinearLayout) findViewById(R.id.linearLayout4);
	       SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
	    final   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this); 
		  final   String strBad = sharedPreferences.getString("Bad", ""); 
		  final   Boolean Medication = prefs.getBoolean("Medication", true);
		  final   String strEating = sharedPreferences.getString("Eating", ""); 
		  final   String strMedicine1 = sharedPreferences.getString("Medicine1Name", ""); 
		  final   String strMedicine2 = sharedPreferences.getString("Medicine2Name", ""); 
		  final   Boolean Medicine1 = prefs.getBoolean("Medicine1Taken", false);
		  final   Boolean Medicine2 = prefs.getBoolean("Medicine2Taken", false);
		  final   long medicine1time = prefs.getLong("millistring", 0);
		  final   long medicine2time = prefs.getLong("millis2string", 0);
		  final   String cuser = sharedPreferences.getString("Currentuser", ""); 
	 
	    final  EditText  txtDate1 = (EditText) findViewById(R.id.txtDate1);
	   final   EditText  txtTime1 = (EditText) findViewById(R.id.txtTime1); 
	   final  EditText  txtDate2 = (EditText) findViewById(R.id.txtDate2);
	   final   EditText  txtTime2 = (EditText) findViewById(R.id.txtTime2);  
		 RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);

		
	        rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch(checkedId){
	                    case R.id.radio1:
	                        // do operations specific to this selection
	                    	// SavePreferences("Medicine1Taken", "Yes");
	                    	prefs.edit().putBoolean("Medicine1Taken", true).commit();
	                    	 ep7.setVisibility(View.VISIBLE);
	             	        ep10.setVisibility(View.VISIBLE);
	             	        ep11.setVisibility(View.GONE);
	             	        ep12.setVisibility(View.GONE);
	                    break;

	                    case R.id.radio2:
	                        // do operations specific to this selection
	                    	// SavePreferences("Medicine1Taken", "No");
	                    	prefs.edit().putBoolean("Medicine1Taken", false).commit();
	                    break;

	                    

	                }


	            }
	        });
	        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroup2);


	        rg2.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                switch(checkedId){
	                    case R.id.radio3:
	                        // do operations specific to this selection
	                    	// SavePreferences("Medicine2Taken", "Yes");
	                    	prefs.edit().putBoolean("Medicine2Taken", true).commit();
	                    	 ep11.setVisibility(View.VISIBLE);
	             	        ep12.setVisibility(View.VISIBLE);
	             	        ep7.setVisibility(View.GONE);
	             	        ep10.setVisibility(View.GONE);
	                    break;

	                    case R.id.radio4:
	                        // do operations specific to this selection
	                    	// SavePreferences("Medicine2Taken", "No");
	                    	prefs.edit().putBoolean("Medicine2Taken", false).commit();
	                    break;

	                    

	                }


	            }
	        });
	        Button backbutton = (Button) findViewById(R.id.backButton);
		      backbutton.setOnClickListener(new View.OnClickListener() {
		              public void onClick(View view) {
		                  Intent myIntent = new Intent(view.getContext(), PatientDashboard.class);
		                  startActivityForResult(myIntent, 0);
		              }
		 
		     });
	        Button button1 = (Button) findViewById(R.id.submitButton);
	        final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
		      button1.setOnClickListener(new View.OnClickListener() {
		              public void onClick(View view) {
		            	  final CheckIn c = new CheckIn();
		            	  c.setMedicineTaken(Medication);
		            	  c.setPainType(strBad);
		            	  Date beginupd = new Date();
		          		long milli = beginupd.getTime();
		            	  c.setCheckInDateTime(milli); 
		            	  c.setFirstmedicine(textView1_.getText().toString());
		            	  c.setSecondmedicine(textView2_.getText().toString());
		            	  c.setMedicine1Taken(Medicine1);
		            	  c.setMedicine2Taken(Medicine2);
		            	  c.setMedicine1TakenTime(Long.valueOf(textView3_.getText().toString()));
		            	  c.setMedicine2TakenTime(Long.valueOf(textView4_.getText().toString()));
		            	 c.setEatingState(strEating);
		            	 c.setUname(cuser);
		          		if (svc != null) {
		          			CallableTask.invoke(new Callable<Boolean>() {

		          				@Override
		          				public Boolean call() throws Exception {
		          					
		          					return svc.addCheckIn(c);
		          				}
		          			}, new TaskCallback<Boolean>() {

		          				@Override
		          				public void success(Boolean v) {
		          					// OAuth 2.0 grant was successful and we
		          					// can talk to the server, open up the video listing
		          					//textview_.setText(result);
		          					Toast.makeText(
		          							CheckInStep2.this,
		          							"CheckIn added successfully.",
		          							Toast.LENGTH_SHORT).show();
		          					
		          				}
		          				@Override
		          				public void error(Exception e) {
		          					Toast.makeText(
		          							CheckInStep2.this,
		          							"Unable to add checkin, please login again.",
		          							Toast.LENGTH_SHORT).show();

		          					
		          				}
		          			});
		          		}
		              }
		 
		     }); 
		  Button    btnCalendar1 = (Button) findViewById(R.id.btnCalendar1);
		      btnCalendar1.setOnClickListener(new OnClickListener(){
		    	    @Override public void onClick(    View v){
		    	     
		    	        Calendar c=new GregorianCalendar();
		    	        
		    	        mDay1=c.get(Calendar.DAY_OF_MONTH);
		    	        mYear1=c.get(Calendar.YEAR);
		    	        mMonth1=c.get(Calendar.MONTH);
		    	      final  long startTime = c.getTimeInMillis();
		    	      DatePickerDialog dia=new DatePickerDialog(CheckInStep2.this,new OnDateSetListener(){
		    	        @Override public void onDateSet(        DatePicker view,        int year,        int monthOfYear,        int dayOfMonth){
		    	        	 txtDate1.setText(dayOfMonth + "-"
	                                    + (monthOfYear + 1) + "-" + year);
		    	        	
		    	        	 textView3_.setText( String.valueOf(startTime));
		    	        }
		    	      }
		    	,mYear1,mMonth1,mDay1);
		    	      dia.show();
		    	    }
		    	  }
		    	);
		     
		    
		      Button  btnTimePicker1 = (Button) findViewById(R.id.btnTimePicker1);
		      btnTimePicker1.setOnClickListener(new OnClickListener(){
		    	    @Override public void onClick(    View v){
		    	     
		    	    	final Calendar c = Calendar.getInstance();
		                mHour1 = c.get(Calendar.HOUR_OF_DAY);
		                mMinute1 = c.get(Calendar.MINUTE);
		                TimePickerDialog tpd=new TimePickerDialog(CheckInStep2.this,new OnTimeSetListener(){
			    	        @Override public void onTimeSet( TimePicker view,int hourOfDay,int minute){
			    	        	txtTime1.setText(hourOfDay + ":" + minute);
			    	          
			    	        }
			    	      }
			    	,mHour1,mMinute1,false);
			    	      tpd.show();
		    	    
		    	        
		    	    }
		    	  }
		    	);
		      Button    btnCalendar2 = (Button) findViewById(R.id.btnCalendar2);
		      btnCalendar2.setOnClickListener(new OnClickListener(){
		    	    @Override public void onClick(    View v){
		    	     
		    	        Calendar c=new GregorianCalendar();
		    	        
		    	        mDay2=c.get(Calendar.DAY_OF_MONTH);
		    	        mYear2=c.get(Calendar.YEAR);
		    	        mMonth2=c.get(Calendar.MONTH);
		    	        final  long startTime2 = c.getTimeInMillis();
		    	      DatePickerDialog dia=new DatePickerDialog(CheckInStep2.this,new OnDateSetListener(){
		    	        @Override public void onDateSet(        DatePicker view,        int year,        int monthOfYear,        int dayOfMonth){
		    	        	 txtDate2.setText(dayOfMonth + "-"
	                                    + (monthOfYear + 1) + "-" + year);
		    	        	 textView4_.setText( String.valueOf(startTime2));
		    	        }
		    	      }
		    	,mYear2,mMonth2,mDay2);
		    	      dia.show();
		    	    }
		    	  }
		    	);
		     
		    
		      Button  btnTimePicker2 = (Button) findViewById(R.id.btnTimePicker2);
		      btnTimePicker2.setOnClickListener(new OnClickListener(){
		    	    @Override public void onClick(    View v){
		    	     
		    	    	final Calendar c = Calendar.getInstance();
		                mHour2 = c.get(Calendar.HOUR_OF_DAY);
		                mMinute2 = c.get(Calendar.MINUTE);
		                TimePickerDialog tpd=new TimePickerDialog(CheckInStep2.this,new OnTimeSetListener(){
			    	        @Override public void onTimeSet( TimePicker view,int hourOfDay,int minute){
			    	        	txtTime2.setText(hourOfDay + ":" + minute);
			    	          
			    	        }
			    	      }
			    	,mHour2,mMinute2,false);
			    	      tpd.show();
		    	    
		    	        
		    	    }
		    	  }
		    	);
		  
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}
	 private void SavePreferences(String key, String value)
	  	{  
	  		SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE); 
	  	SharedPreferences.Editor editor = sharedPreferences.edit(); 
	  	editor.putString(key, value); 
	  	//editor.putString("MY_NAME", "nass"); 
	  	editor.commit();   }
	private void refreshVideos() {
		final PatientSvcApi svc = PatientSvc.getOrShowLogin(this);
         final String pname="nasim";
        
         Date beginupd = new Date();
 		final long milli = 1416504276933L ;
       final  List<Medication> streamData = new ArrayList<Medication>();
       final MedicationAdapter mAdapter;
      mAdapter = new MedicationAdapter(this, 0, streamData);
		if (svc != null) {
			CallableTask.invoke(new Callable<Medication>() {

				@Override
				public Medication call() throws Exception {
					return svc.getMedicationByDateAndName(milli,pname);
				}
			}, new TaskCallback<Medication>() {

				@Override
				public void success(Medication result) {
					
					//List<String> names = new ArrayList<String>();
					//List<String> pains = new ArrayList<String>();
					//List<String> meds = new ArrayList<String>();
					textView1_.setText( result.getMedicine1Name());
					textView2_.setText(result.getMedicine2Name());
					// SavePreferences("Medicine1Name", result.getMedicine1Name());
					// SavePreferences("Medicine2Name", result.getMedicine2Name());
				 // final	String Medicine1Name= result.getMedicine1Name();
					//final String Medicine2Name= result.getMedicine2Name();
				//	}
				//	for (CheckIn c : result) {
				//		pains.add(c.getEatingState().toString());
				//	}
					//for (CheckIn c : result) {
				//		meds.add(c.getMedicineTaken().toString());
				//	}
					
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							CheckInStep2.this,
							"Unable to fetch the patient list, please login again." + milli,
							Toast.LENGTH_SHORT).show();

					
				}
			});
		}
	}

}
