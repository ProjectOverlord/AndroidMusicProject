package com.progettofondamenti.audioplayer;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.progettofondamenti.audioplayer.login.view.LoginActivity;

/**
 * Junit Test Class
 * This class checks the correctness of components of the LogInActivity
 * @author team
 * @see ActivityInstrumentationTestCase2
 */
public class TestOnLoginActivity extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity loginActivity;
    private Button loginButton;
    private static final long timeout = 10000;

    public TestOnLoginActivity() {
        super(LoginActivity.class);
    }

    /*
     * non-javadoc sets up the activity for usage
    */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        loginActivity = getActivity();
        loginButton = (Button) loginActivity.findViewById(R.id.loginButton);

        getActivity();
    }

    /**
     * This test method simulates the clik on loginButton and verifies the correctness of this action
     */
    public void testLaunchingMainActivity(){
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, true);

        loginButton.performClick();

        activityMonitor.waitForActivityWithTimeout(timeout);
        assertEquals(1, activityMonitor.getHits());
    }

}
