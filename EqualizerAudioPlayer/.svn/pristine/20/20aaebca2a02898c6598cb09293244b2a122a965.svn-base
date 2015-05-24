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
    private TextView elapsedTime;
    private TextView remainingTime;


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
        elapsedTime= (TextView)mainActivity.findViewById(R.id.elapsedTime);
        remainingTime = (TextView)mainActivity.findViewById(R.id.remainingTime);

    }

    /**
     *
     * metodo che controlla che la stringa del titolo sia corretta
     */
    public void testMyFirstTestTextView_labelText() {
        final String expected = "W.A.Mozart - Concerto No.21 - Andante";

        final String actual = songText.getText().toString();
        assertEquals(expected, actual);
    }


    /**
     * metodo che controlla che la stringa della durata sia corretta
     */

    public void testTextViewElapsedTime() {

        final String expected = "0 min, 0 sec";

        final String actual = elapsedTime.getText().toString();

        assertEquals(expected, actual);
    }

    /**
     * metodo che controlla che la stringa del tempo totale sia giusta
     */
    public void testTextViewRemainingTime() {

        final String expected = "1 min, 32 sec";

        final String actual = remainingTime.getText().toString();

        assertEquals(expected, actual);
    }

}
