package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;


import java.util.List;

/**
 * Specific settings activity created to be able to select preferences
 */
public class SettingsActivity extends PreferenceActivity {

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
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        MainActivity.setBGColor(sp.getString("pref_color", ""));
    }
}
