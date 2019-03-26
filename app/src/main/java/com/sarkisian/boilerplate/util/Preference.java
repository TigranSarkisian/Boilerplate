package com.sarkisian.boilerplate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {

    private static final String PREF_USER_TOKEN = "PREF_USER_TOKEN";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";

    private static Preference sInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private Preference(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mSharedPreferences.edit();
    }

    public static synchronized Preference getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Preference(context);
        }

        return sInstance;
    }

    public void setUserToken(String token) {
        mEditor.putString(PREF_USER_TOKEN, token);
        mEditor.apply();
    }

    public String getUserToken() {
        return mSharedPreferences.getString(PREF_USER_TOKEN, null);
    }

    public void setUserName(String userName) {
        mEditor.putString(PREF_USER_NAME, userName);
        mEditor.apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(PREF_USER_NAME, null);
    }

}
