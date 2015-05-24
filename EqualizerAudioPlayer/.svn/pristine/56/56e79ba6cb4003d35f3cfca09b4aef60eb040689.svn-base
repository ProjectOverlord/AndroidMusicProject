package com.progettofondamenti.audioplayer.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.progettofondamenti.audioplayer.MainActivity;
import com.progettofondamenti.audioplayer.R;

/**
 * Junit Test Class
 * test che verifica la correttezza dei componenti
 * Created by Erica on 13/05/15.
 */
public class TestOnActivityComponents
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView songText;


    public TestOnActivityComponents() {
        super(MainActivity.class);
    }

    /*
     * non-javadoc sets up the activity for usage
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        songText = (TextView) mainActivity.findViewById(R.id.songTitle);
    }

    /**
     *
     * metodo che controlla che la stringa sia corretta
     */
    public void testMyFirstTestTextView_labelText() {
        final String expected = "W.A.Mozart - Concerto No.21 - Andante";

        final String actual = songText.getText().toString();
        assertEquals(expected, actual);
    }


}
