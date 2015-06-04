package com.progettofondamenti.equalizeraudioplayer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * @author francesco
 */
public class MyPreferences extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        Activity activity;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            activity=getActivity();
            addPreferencesFromResource(R.xml.apppreferences);
            setRetainInstance(true);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sp, String key){
            if(key.equals("pref_color")){

            ((MainActivity)getActivity()).setBackgroundColor(sp.getString(key, ""));
            }
        }

        @Override
        public void onResume(){
            super.onResume();

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

