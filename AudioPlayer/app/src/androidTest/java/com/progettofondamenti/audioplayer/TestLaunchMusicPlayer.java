package com.progettofondamenti.audioplayer;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Junit Test Class
 * This class checks the correctness of components
 * @author team
 * @see ActivityInstrumentationTestCase2
 */
public class TestLaunchMusicPlayer extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView songText;
    private TextView elapsedTime;
    private TextView remainingTime;
    private static int PLAY_TIME = 30000;



    public TestLaunchMusicPlayer() {
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
        getActivity();

    }

    /**
     *testTextViewElapsedTime()
     *method that controls the length of the string is correct
     */

    /*public void testTextViewElapsedTime() {

        final String expected = "0 min, 0 sec";

        final String actual = elapsedTime.getText().toString();

        assertEquals(expected, actual);
    }*/

    /**
     * testTextViewRemainingTime()
     * Method that controls the string of the total time is correct
     *
     * TODO: this test was useful when we were able to know the duration of the song before
     * playing it - now that the song comes from the server we need to change it
     */
   /* public void testTextViewRemainingTime() {

        final String expected = "1 min, 32 sec";

        final String actual = remainingTime.getText().toString();

        assertEquals(expected, actual);
    }*/

    /**
     * Launches music player and sleep for 30 seconds to capture
     * the music player power usage base line
     *
     * @throws Exception
     */
    public void testLaunchMusicPlayer() throws Exception {

        try {
            Thread.sleep(PLAY_TIME);
        } catch (Exception e) {
            assertTrue("MusicPlayer Do Nothing", false);
        }
        assertTrue("MusicPlayer Do Nothing", true);
    }


}
