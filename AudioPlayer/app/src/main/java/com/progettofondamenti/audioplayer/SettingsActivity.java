package com.progettofondamenti.audioplayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Specific settings activity created to be able to select preferences
 *
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this method is deprecated, but it's necessary to provide support on older devices
        addPreferencesFromResource(R.xml.apppreferences);


    }


    @Override
    protected void onResume() {
        super.onResume();
        // Set background color of the activity
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        MainActivity.setBGColor(sp.getString("pref_color", ""));

        // Register OnSharedPreferenceChangeListener
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onPause() {
        super.onPause();
        // Unregister OnSharedPreferenceChangeListener
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("pref_color")) {
            // Set background color of the activity
            MainActivity.setBGColor(sharedPreferences.getString(key, ""));
        }
    }
}
