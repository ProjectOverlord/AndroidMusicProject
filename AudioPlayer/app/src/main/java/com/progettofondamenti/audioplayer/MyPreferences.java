package com.progettofondamenti.audioplayer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * This class allows the user to interact with the activity
 * using what it receives from the settings (object SharedPreferences) to set something.
 *
 * @author team
 * @see android.content.SharedPreferences.OnSharedPreferenceChangeListener
 * @see PreferenceFragment
 *
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
