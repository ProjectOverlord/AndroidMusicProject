package com.progettofondamenti.audioplayer.tests;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.progettofondamenti.audioplayer.MainActivity;
import com.progettofondamenti.audioplayer.R;
import com.progettofondamenti.audioplayer.login.view.LoginActivity;

/**
 * Junit Test Class
 * This class checks the correctness of components of the LogInActivity
 * @author team
 * @see android.test.ActivityInstrumentationTestCase2
 */

public class TestOnLoginActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity loginActivity;
    private Button loginButton;


    public TestOnLoginActivity(Class<LoginActivity> activityClass) {

        super(activityClass);
    }


    /*
     * non-javadoc sets up the activity for usage
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        loginActivity = getActivity();
        loginButton = (Button) loginActivity.findViewById(R.id.loginButton);

        getActivity(); // TODO non so se serve

    }

    /**
     *testLaunchingMainActivity()
     * method that simulates the clik on loginButton and verifies the correctness of this action
     */
    public void testLaunchingMainActivity(){
        Instrumentation.ActivityMonitor am = getInstrumentation().
                                             addMonitor(MainActivity.class.getName(), null, true);

        loginButton.performClick();

        //am.waitForActivitywithTimeout(timeout);
        assertEquals(1, am.getHits());
    }


}
