package org.magnum.videoup.client;

import java.util.Collection;

import java.util.Date;
import java.util.concurrent.Callable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class AlertDoctorBC extends BroadcastReceiver {
    static int noOfTimes = 0;
   
 
    // Method gets called when Broad Case is issued from MainActivity for every 10 seconds
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO Auto-generated method stub
        noOfTimes++;
        Toast.makeText(context, "BC Service Running for " + noOfTimes + " times", Toast.LENGTH_SHORT).show();
        final PatientSvcApi svc = PatientSvc.getOrShowLogin(context);
        Date beginupd = new Date();
		final long milli = beginupd.getTime(); 
		final String pain="severe";
		final String paintype="moderate";
		 SharedPreferences sharedPreferences = context.getSharedPreferences("MyPREFERENCES",Context.MODE_PRIVATE);
		final   String cuser = sharedPreferences.getString("Currentuser", ""); 
		//final String uname="user1";
		 final Alert a = new Alert();
	   	  a.setMessage("The following patient is in critical state");
	   	  a.setDname("Doctor");
	   	  a.setPname(cuser);
	     
		CallableTask.invoke(new Callable<Collection<CheckIn>>() {

			@Override
			public Collection<CheckIn> call() throws Exception {
				
				return svc.findByCheckInDateTimeAndPainTypeAndUname(milli,pain,cuser);
			}
		}, new TaskCallback<Collection<CheckIn>>() {

			@Override
			public void success(Collection<CheckIn> result) {
				if (result.size() >3)
				{
					CallableTask.invoke(new Callable<Boolean>() {

						@Override
						public Boolean call() throws Exception {
							
							return svc.addAlert(a);
						}
					}, new TaskCallback<Boolean>() {

						@Override
						public void success(Boolean result) {
							
							 Toast.makeText(context, "Alert has been sent", Toast.LENGTH_SHORT).show();	
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
		
		CallableTask.invoke(new Callable<Collection<CheckIn>>() {

			@Override
			public Collection<CheckIn> call() throws Exception {
				
				return svc.findByCheckInDateTimeAndPainTypeAndUname(milli,paintype,cuser);
			}
		}, new TaskCallback<Collection<CheckIn>>() {

			@Override
			public void success(Collection<CheckIn> result) {
				if (result.size() == 4)
				{
					CallableTask.invoke(new Callable<Boolean>() {

						@Override
						public Boolean call() throws Exception {
							
							return svc.addAlert(a);
						}
					}, new TaskCallback<Boolean>() {

						@Override
						public void success(Boolean result) {
							
							 Toast.makeText(context, "Alert has been sent", Toast.LENGTH_SHORT).show();	
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
