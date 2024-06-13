package com.haideag.market.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper{
    private static final String PREFERENCE_NAME = "android_studio_nerd_help";
    private static final String KEY_FULL_NAME = "FULL_NAME";

    private SharedPreferences prefs;

    public PreferenceHelper(Context context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setName(String name) {
        prefs.edit().putString(KEY_FULL_NAME, name).apply();
    }

    public String getName() {
        return prefs.getString(KEY_FULL_NAME, null);
    }
    public void clearName() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_FULL_NAME);
        editor.apply();
    }
}