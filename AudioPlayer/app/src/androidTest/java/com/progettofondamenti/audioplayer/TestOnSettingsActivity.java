package com.progettofondamenti.audioplayer;

import android.preference.CheckBoxPreference;
import android.test.ActivityInstrumentationTestCase2;

/**
 * JUnit Test Class
 * Specific class created to test the SettingsActivity
 * @author team
 */
public class TestOnSettingsActivity extends ActivityInstrumentationTestCase2<SettingsActivity> {

    private SettingsActivity settingsActivity;

   public TestOnSettingsActivity(){
       super(SettingsActivity.class);
   }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        settingsActivity = getActivity();

        getActivity();
    }

    public void testCheckboxPreferenceEnabled(){
        CheckBoxPreference pref = settingsActivity.getPlayCheck();

        // verifiche di base, senza compiere azioni
        assertNotNull(pref);
        assertFalse(pref.isChecked());
    }
}
