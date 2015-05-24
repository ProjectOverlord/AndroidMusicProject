package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Specific settings activity created to be able to select preferences
 *
 * @author Francesco
 *
 */
@SuppressWarnings("ALL")
public class SettingsActivity extends PreferenceActivity  {

    private LinearLayout layout;
    private EditTextPreference path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.apppreferences);

        PreferenceManager.setDefaultValues(SettingsActivity.this, R.xml.apppreferences, false);

        path = (EditTextPreference) findPreference("pref_savepath");

        SharedPreferences getPrefSavepath = getSharedPreferences("pref_savepath", Context.MODE_PRIVATE);
        path.setText("http://192.168.1.5:8080/mp3.mp3");
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

}
