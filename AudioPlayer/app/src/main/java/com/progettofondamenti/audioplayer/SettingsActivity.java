package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
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
    private CheckBoxPreference playCheck;
    private boolean background;
    private SharedPreferences getPrefSavepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.apppreferences);

        PreferenceManager.setDefaultValues(SettingsActivity.this, R.xml.apppreferences, false);

        path = (EditTextPreference) findPreference("pref_savepath");

        getPrefSavepath = getSharedPreferences("pref_savepath", Context.MODE_MULTI_PROCESS);

        path.setText("http://10.87.136.158:8080/mp3.mp3");

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        background = getPrefSavepath.getBoolean("pref_background", false);
    }

    public CheckBoxPreference getPlayCheck() {
        return playCheck;
    }
}