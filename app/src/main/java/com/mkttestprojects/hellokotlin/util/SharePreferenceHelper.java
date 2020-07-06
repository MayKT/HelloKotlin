package com.mkttestprojects.hellokotlin.util;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharePreferenceHelper {

	private SharedPreferences sharedPreference;

	private static String SHARE_PREFRENCE = "showtimePref";

	private static String  DARK_STATUS = "DARK_STATUS";


	@Inject
	public SharePreferenceHelper(Context context)
	{
		sharedPreference = context.getSharedPreferences(SHARE_PREFRENCE, Context.MODE_PRIVATE);
	}

	public int getDarkStatus() {
		return sharedPreference.getInt(DARK_STATUS,0);
	}

	public void setDarkStatus(int darkStatus) {
		SharedPreferences.Editor editor = sharedPreference.edit();
		editor.putInt(DARK_STATUS, darkStatus);
		editor.commit();
	}
}
