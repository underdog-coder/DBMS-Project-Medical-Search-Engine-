package com.example.rohit.dbmsproject;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by rohit on 21/3/18.
 */

public class SharedPreference {
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "dbms_welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public SharedPreference(Context context) {
        this.context = context;
        preference = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preference.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return preference.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
