package com.progettofondamenti.equalizeraudioplayer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.progettofondamenti.equalizeraudioplayer.buttons.ForwardButton;
import com.progettofondamenti.equalizeraudioplayer.buttons.NextButton;
import com.progettofondamenti.equalizeraudioplayer.buttons.PauseButton;
import com.progettofondamenti.equalizeraudioplayer.buttons.PlayButton;
import com.progettofondamenti.equalizeraudioplayer.buttons.PreviousButton;
import com.progettofondamenti.equalizeraudioplayer.buttons.RewindButton;
import com.progettofondamenti.equalizeraudioplayer.listeners.ForwardListener;
import com.progettofondamenti.equalizeraudioplayer.listeners.NextListener;
import com.progettofondamenti.equalizeraudioplayer.listeners.PauseListener;
import com.progettofondamenti.equalizeraudioplayer.listeners.PlayListener;
import com.progettofondamenti.equalizeraudioplayer.listeners.PreviousListener;
import com.progettofondamenti.equalizeraudioplayer.listeners.RewindListener;

/**
 * The main activity of the program.
 *
 *1) Default setup and initialization of activity_main.xml
 */
public class MainActivity extends ActionBarActivity {

    /* Declarations */
	/* Initializes the player given the context of this activity */
    private IPlayer player;

	private static PlayButton playButton;
    private static PauseButton pauseButton;
    private static RewindButton rewButton;
    private static ForwardButton ffButton;
    private static PreviousButton previousButton;
    private static NextButton nextButton;
    private static TextView songTitle;

    private static LinearLayout layout;

    private PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default setup and initialization of activity_main.xml */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		player = new PlayerModel(this.getApplicationContext());

		/* Inizializza le componenti rimanenti rispetto hha quelle già dichiarate in PlayerView */
        initializeXmlComponents();

		/* Creiamo la view con kdvhijds */
		playerView = new PlayerView(player, this);
		playerView.run();

        setFragmentSettings();

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

        songTitle = (TextView) findViewById(R.id.songTitle);
        songTitle.setText("W.A.Mozart - Concerto No.21 - Andante");

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
     * sets the color of the LinearLayout given a color string
     * @param color stringa che descrive il colore
     */
    public static void setBackgroundColor(String color){
        layout.setBackgroundColor(Color.parseColor(color));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // creates and Intent in order to call the SettingActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.equalizer_item1){

            // EQUALIZZATORE STANDARD
            Intent i = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
            i.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, player.getIdAudioSession());
            startActivityForResult(i, 11113);

            return true;
        }
        if (id == R.id.equalizer_item2){
            // EQUALIZZATORE PERSONALIZZATO
            Intent intentEq = new Intent(MainActivity.this, EqualizerActivity.class);
            intentEq.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, player.getIdAudioSession());
            startActivity(intentEq);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            return;
        }


    }

    @Override
    public void onPause() {
        super.onPause();

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

        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
