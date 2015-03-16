/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package org.magnum.videoup.client;

import org.magnum.videoup.client.oauth.SecuredRestBuilder;


import org.magnum.videoup.client.unsafe.EasyHttpClient;

import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;
import android.content.Context;
import android.content.Intent;

public class PatientSvc {

	public static final String CLIENT_ID = "mobile";

	private static PatientSvcApi patientSvc_;

	public static synchronized PatientSvcApi getOrShowLogin(Context ctx) {
		if (patientSvc_ != null) {
			return patientSvc_;
		} else {
			Intent i = new Intent(ctx, LoginScreenActivity.class);
			ctx.startActivity(i);
			return null;
		}
	}

	public static synchronized PatientSvcApi init(String server, String user,
			String pass) {

		patientSvc_ = new SecuredRestBuilder()
				.setLoginEndpoint(server + PatientSvcApi.TOKEN_PATH)
				.setUsername(user)
				.setPassword(pass)
				.setClientId(CLIENT_ID)
				.setClient(
						new ApacheClient(new EasyHttpClient()))
				.setEndpoint(server).setLogLevel(LogLevel.FULL).build()
				.create(PatientSvcApi.class);

		return patientSvc_;
	}
}
