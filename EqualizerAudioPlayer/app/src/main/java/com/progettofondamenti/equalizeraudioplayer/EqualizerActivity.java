package com.progettofondamenti.equalizeraudioplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This is the activity that provides the user the possibility to set up
 * the equalizer - the aim of this activity is t just to show the possibility to
 * select specific frequencies parameters on a different song that starts at the
 * moment of the creation of the activity.
 * @author francesco
 */
public class EqualizerActivity extends ActionBarActivity {

    private Equalizer mEqualizer;
    private PlayerModel playerModel;
    private MediaPlayer mp;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equalizer);

//        mp = playerModel.getMediaPlayer();
        mp = MediaPlayer.create(this, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
        mp.start();

        // set the device's volume control to control the audio stream we'll be playing
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // create the equalizer with default priority of 0 & attach to our media player
        mEqualizer = new Equalizer(0, mp.getAudioSessionId());
        mEqualizer.setEnabled(true);

        setupEqualizerFxAndUI();


        // listen for when the music stream ends playing
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                // disable the visualizer as it's no longer needed

            }
        });
    }

    /* shows spinner with list of equalizer presets to choose from
   - updates the seekBar progress and gain levels according
   to those of the selected preset*/
    private void equalizeSound() {
//        set up the spinner
        ArrayList<String> equalizerPresetNames = new ArrayList<String>();
        ArrayAdapter<String> equalizerPresetSpinnerAdapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                equalizerPresetNames);
        equalizerPresetSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner equalizerPresetSpinner = (Spinner) findViewById(R.id.spinner);

//        get list of the device's equalizer presets
        for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++) {
            equalizerPresetNames.add(mEqualizer.getPresetName(i));
        }

        equalizerPresetSpinner.setAdapter(equalizerPresetSpinnerAdapter);

//        handle the spinner item selections
        equalizerPresetSpinner.setOnItemSelectedListener(new AdapterView
                .OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                //first list item selected by default and sets the preset accordingly
                mEqualizer.usePreset((short) position);
//                get the number of frequency bands for this equalizer engine
                short numberFrequencyBands = mEqualizer.getNumberOfBands();
//                get the lower gain setting for this equalizer band
                final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];

//                set seekBar indicators according to selected preset
                for (short i = 0; i < numberFrequencyBands; i++) {
                    short equalizerBandIndex = i;
                    SeekBar seekBar = (SeekBar) findViewById(equalizerBandIndex);
//                    get current gain setting for this equalizer band
//                    set the progress indicator of this seekBar to indicate the current gain value
                    seekBar.setProgress(mEqualizer
                            .getBandLevel(equalizerBandIndex) - lowerEqualizerBandLevel);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                not used
            }
        });
    }

    /* displays the SeekBar sliders for the supported equalizer frequency bands
    user can move sliders to change the frequency of the bands*/
    private void setupEqualizerFxAndUI() {

//        get reference to linear layout for the seekBars
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutEqual);

//        equalizer heading
        TextView equalizerHeading = new TextView(this);
        equalizerHeading.setText("Equalizer");
        equalizerHeading.setTextSize(20);
        equalizerHeading.setGravity(Gravity.CENTER_HORIZONTAL);
        mLinearLayout.addView(equalizerHeading);

//        get number frequency bands supported by the equalizer engine
        short numberFrequencyBands = mEqualizer.getNumberOfBands();

//        get the level ranges to be used in setting the band level
//        get lower limit of the range in milliBels
        final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];
//        get the upper limit of the range in millibels
        final short upperEqualizerBandLevel = mEqualizer.getBandLevelRange()[1];

//        loop through all the equalizer bands to display the band headings, lower
//        & upper levels and the seek bars
        for (short i = 0; i < numberFrequencyBands; i++) {
            final short equalizerBandIndex = i;

//            frequency header for each seekBar
            TextView frequencyHeaderTextview = new TextView(this);
            frequencyHeaderTextview.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            frequencyHeaderTextview.setGravity(Gravity.CENTER_HORIZONTAL);
            frequencyHeaderTextview
                    .setText((mEqualizer.getCenterFreq(equalizerBandIndex) / 1000) + " Hz");
            mLinearLayout.addView(frequencyHeaderTextview);

//            set up linear layout to contain each seekBar
            LinearLayout seekBarRowLayout = new LinearLayout(this);
            seekBarRowLayout.setOrientation(LinearLayout.HORIZONTAL);

//            set up lower level textview for this seekBar
            TextView lowerEqualizerBandLevelTextview = new TextView(this);
            lowerEqualizerBandLevelTextview.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            lowerEqualizerBandLevelTextview.setText((lowerEqualizerBandLevel / 100) + " dB");
//            set up upper level textview for this seekBar
            TextView upperEqualizerBandLevelTextview = new TextView(this);
            upperEqualizerBandLevelTextview.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            upperEqualizerBandLevelTextview.setText((upperEqualizerBandLevel / 100) + " dB");

            //            **********  the seekBar  **************
//            set the layout parameters for the seekbar
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;

//            create a new seekBar
            SeekBar seekBar = new SeekBar(this);
//            give the seekBar an ID
            seekBar.setId(i);

            seekBar.setLayoutParams(layoutParams);
            seekBar.setMax(upperEqualizerBandLevel - lowerEqualizerBandLevel);
//            set the progress for this seekBar
            seekBar.setProgress(mEqualizer.getBandLevel(equalizerBandIndex));

//            change progress as its changed by moving the sliders
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    mEqualizer.setBandLevel(equalizerBandIndex,
                            (short) (progress + lowerEqualizerBandLevel));
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    //not used
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    //not used
                }
            });

//            add the lower and upper band level textviews and the seekBar to the row layout
            seekBarRowLayout.addView(lowerEqualizerBandLevelTextview);
            seekBarRowLayout.addView(seekBar);
            seekBarRowLayout.addView(upperEqualizerBandLevelTextview);

            mLinearLayout.addView(seekBarRowLayout);

            //        show the spinner
            equalizeSound();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing() && mp != null) {

            mEqualizer.release();
            mp.release();
            mp = null;
        }
    }

}