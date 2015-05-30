package com.progettofondamenti.equalizeraudioplayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

/**
 * Specific settings activity created to be able to select preferences
 *
 */
@SuppressWarnings("ALL")
public class SettingsActivity extends PreferenceActivity  {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.apppreferences);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

}
