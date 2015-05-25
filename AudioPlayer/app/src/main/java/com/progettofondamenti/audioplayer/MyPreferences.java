package com.progettofondamenti.audioplayer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by francesco on 16/05/15.
 * @author Francesco
 */
public class MyPreferences extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        addPreferencesFromResource(R.xml.apppreferences);
        setRetainInstance(true);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sp, String key){
        if(key.equals("pref_color")){
            // Set background color of the activity
            ((MainActivity)getActivity()).setBackgroundColor(sp.getString(key, ""));
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        // Set background color of the activity
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(activity);
        ((MainActivity)getActivity()).setBackgroundColor(sp.getString("pref_color", ""));

        // Register OnSharedPreferenceChangeListener
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        // Unregister OnSharedPreferenceChangeListener
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
