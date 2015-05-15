package com.progettofondamenti.audioplayer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Specific settings activity created to be able to select preferences
 *
 */
@SuppressWarnings("ALL")
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this method is deprecated, but it's necessary to provide support on older devices
        addPreferencesFromResource(R.xml.apppreferences);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // not sure this co two lines of code are usuful or not
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String color = sp.getString("pref_color", "");

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
//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        }
    }
}
