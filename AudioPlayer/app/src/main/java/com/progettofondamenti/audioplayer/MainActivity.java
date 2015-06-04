package com.progettofondamenti.audioplayer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.progettofondamenti.audioplayer.buttons.ForwardButton;
import com.progettofondamenti.audioplayer.buttons.NextButton;
import com.progettofondamenti.audioplayer.buttons.PauseButton;
import com.progettofondamenti.audioplayer.buttons.PlayButton;
import com.progettofondamenti.audioplayer.buttons.PreviousButton;
import com.progettofondamenti.audioplayer.buttons.RewindButton;
import com.progettofondamenti.audioplayer.listeners.ForwardListener;
import com.progettofondamenti.audioplayer.listeners.NextListener;
import com.progettofondamenti.audioplayer.listeners.PauseListener;
import com.progettofondamenti.audioplayer.listeners.PlayListener;
import com.progettofondamenti.audioplayer.listeners.PreviousListener;
import com.progettofondamenti.audioplayer.listeners.RewindListener;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * This class is the main class in which the view is defined
 * according to the characteristics specified in the XML file.
 * It is also responsible for the use of various components described in other classes.
 *
 * @author team
 * @see android.support.v7.app.ActionBarActivity
 */
public class MainActivity extends ActionBarActivity {

    /* Specific code for activity results */
    private static final int RESULT_SETTINGS = 1;

	/* To be changed with local server IP address */
    private static final String DEFAULT_SERVER_SUFFIX = "http://192.168.1.169:8080/"; // casa Claudio

    /* Declarations */
	/* Initializes the player given the context of this activity */
    private IPlayer player;

    private static PlayButton playButton;
    private static PauseButton pauseButton;
    private static RewindButton rewButton;
    private static ForwardButton ffButton;
    private static PreviousButton previousButton;
    private static NextButton nextButton;

    private boolean backgroundPlaying;

    public void setBackgroundPlaying(boolean backgroundPlaying) {
        this.backgroundPlaying = backgroundPlaying;
    }

    private static LinearLayout layout;

    private PlayerView playerView;

    private String serverSuffix = DEFAULT_SERVER_SUFFIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default setup and initialization of activity_main.xml */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new PlayerModel(serverSuffix);

        // Create task
        TitlesListTask task = new TitlesListTask(player);
        // Execute it and wait for the results before going on
        try {
            task.execute(null, null, null).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("-------", e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e("-------", e.getMessage());
        }

        applySettings();

		/* Inizializza le componenti rimanenti rispetto a quelle già dichiarate in PlayerView */

		/*
		 * next() serves also as initialization
		 */
        try {
            player.next();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("-------", e.getMessage());
        }

        playerView = new PlayerView(player, this);
        playerView.run();

        initializeXmlComponents();

        setFragmentSettings();

        setListenersToButtons();

    }

    /*
     * Sets the specific listeners
     */
    private void setListenersToButtons() {
        playButton.setOnClickListener(new PlayListener(player));
        pauseButton.setOnClickListener(new PauseListener(player));
        rewButton.setOnClickListener(new RewindListener(player));
        ffButton.setOnClickListener(new ForwardListener(player));
        previousButton.setOnClickListener(new PreviousListener(player));
        nextButton.setOnClickListener(new NextListener(player));
    }

    /*
     * Initializes the application's XML components
     */
    private void initializeXmlComponents() {

        layout = (LinearLayout) findViewById(R.id.container);

        playButton = (PlayButton) findViewById(R.id.buttonPlay);
        pauseButton = (PauseButton) findViewById(R.id.buttonPause);
        rewButton = (RewindButton) findViewById(R.id.buttonRew);
        ffButton = (ForwardButton) findViewById(R.id.buttonFf);
        previousButton = (PreviousButton) findViewById(R.id.buttonPrevious);
        nextButton = (NextButton) findViewById(R.id.buttonNext);
    }

    /*
	 *  Sets the specific fragment in order to handle preferences
	 */
    private void setFragmentSettings() {
        FragmentManager fragmentManager = getFragmentManager();
        MyPreferences myFragment=(MyPreferences)fragmentManager.findFragmentByTag("MyPreferences");
        if(myFragment==null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //add a fragment
            myFragment = new MyPreferences();
            fragmentTransaction.add(R.id.container, myFragment, "MyPreferences");
            fragmentTransaction.commit();
        }
    }

    /**
     * Sets the color of the LinearLayout given a color string
     * @param color string that describes the color
     */
    public static void setBackgroundColor(String color){
        layout.setBackgroundColor(Color.parseColor(color));
    }

    /*
     *  creates the menu of the action bar activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
     * Hooks items and manages their actions
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // creates an Intent in order to call the SettingActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, RESULT_SETTINGS);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Sets the informations provided by the settings
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SETTINGS){
            applySettings();
        }
    }

    /*
     * This method retrieves information from the settings activity
     * It is called every time the activity is created or re-created
     *
     */
    private void applySettings(){
        if (player.isPlaying()){
            player.stop();
        }
        // player.reset();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        setBackgroundPlaying(sharedPrefs.getBoolean("pref_background", false));
    }

    @Override
    public void onPause() {
        super.onPause();

        if (!backgroundPlaying)
            player.pause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        player.die();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}