package com.progettofondamenti.equalizeraudioplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
 * the equalizer - the aim of this activity is just to show the possibility to
 * select specific frequencies parameters on a different song that starts at the
 * moment of the creation of the activity.
 *
 * NOTES:
 * - The user can select the parameters on the current media player using the default equalizer
 * selected from the main acitivity's setting button
 * - The comments inserted are related to the equalizer in general
 * @author francesco
 */
public class EqualizerActivity extends ActionBarActivity {

    private Equalizer mEqualizer;
    private MediaPlayer mp;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equalizer);

        mp = MediaPlayer.create(this, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
        mp.start();

        // set the device's volume control to control the audio stream we'll be playing
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // create the equalizer with default priority of 0 & attach to our media player
        mEqualizer = new Equalizer(0, mp.getAudioSessionId());
        mEqualizer.setEnabled(true);

        setupEqualizer();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
    }

    /*
     * shows a spinner with list of equalizer presets to choose from
     */
    private void equalizeSound() {

        ArrayList<String> equalizerPresetNames = new ArrayList<String>();
        ArrayAdapter<String> equalizerPresetSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                equalizerPresetNames);

        equalizerPresetSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner equalizerPresetSpinner = (Spinner) findViewById(R.id.spinner);

        // get list of the device's equalizer presets
        for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++) {
            equalizerPresetNames.add(mEqualizer.getPresetName(i));
        }

        equalizerPresetSpinner.setAdapter(equalizerPresetSpinnerAdapter);

        equalizerPresetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // the first list item is selected by default and sets the preset on consequence
                mEqualizer.usePreset((short) position);

                // get the number of frequency bands for this equalizer engine
                short numberFrequencyBands = mEqualizer.getNumberOfBands();

                // get the lower gain setting for this equalizer band
                final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];

                for (short i = 0; i < numberFrequencyBands; i++) {
                    short equalizerBandIndex = i;
                    SeekBar seekBar = (SeekBar) findViewById(equalizerBandIndex);

                    // get current gain setting for this equalizer band
                    //  set the progress indicator of this seekBar to indicate the current gain value
                    seekBar.setProgress(mEqualizer.getBandLevel(equalizerBandIndex) - lowerEqualizerBandLevel);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // no implementation needed
            }
        });
    }

    /*
     * displays the SeekBar sliders for the supported equalizer frequency bands
     * user can move sliders to change the frequency of the bands
     */
    private void setupEqualizer() {

        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutEqual);

        TextView equalizerHeading = new TextView(this);
        equalizerHeading.setText("Equalizer");
        equalizerHeading.setTextSize(20);
        equalizerHeading.setGravity(Gravity.CENTER_HORIZONTAL);
        mLinearLayout.addView(equalizerHeading);

        // get number frequency bands supported by the equalizer engine
        short numberFrequencyBands = mEqualizer.getNumberOfBands();

        // get the level ranges to be used in setting the band level
        // get lower limit of the range in milliBels
        final short lowerEqualizerBandLevel = mEqualizer.getBandLevelRange()[0];

        // get the upper limit of the range in millibels
        final short upperEqualizerBandLevel = mEqualizer.getBandLevelRange()[1];

        for (short i = 0; i < numberFrequencyBands; i++) {
            final short equalizerBandIndex = i;

            TextView frequencyHeaderTextview = new TextView(this);
            frequencyHeaderTextview.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            frequencyHeaderTextview.setGravity(Gravity.CENTER_HORIZONTAL);
            frequencyHeaderTextview.setText((mEqualizer.getCenterFreq(equalizerBandIndex) / 1000) + " Hz");
            mLinearLayout.addView(frequencyHeaderTextview);

            LinearLayout seekBarRowLayout = setUpLinearLayout();

            TextView lowerEqualizerBandLevelTextview = new TextView(this);
            setUpLowerLevelTextView(lowerEqualizerBandLevel, lowerEqualizerBandLevelTextview);

            TextView upperEqualizerBandLevelTextview = new TextView(this);
            setUpLowerLevelTextView(upperEqualizerBandLevel, upperEqualizerBandLevelTextview);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;

            SeekBar seekBar = new SeekBar(this);
            seekBar.setId(i);

            seekBar.setLayoutParams(layoutParams);
            seekBar.setMax(upperEqualizerBandLevel - lowerEqualizerBandLevel);
            seekBar.setProgress(mEqualizer.getBandLevel(equalizerBandIndex));

            // change progress as its changed by moving the sliders
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    mEqualizer.setBandLevel(equalizerBandIndex,
                            (short) (progress + lowerEqualizerBandLevel));
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // implementation not needed
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    // implementation not needed
                }
            });

            seekBarRowLayout.addView(lowerEqualizerBandLevelTextview);
            seekBarRowLayout.addView(seekBar);
            seekBarRowLayout.addView(upperEqualizerBandLevelTextview);

            mLinearLayout.addView(seekBarRowLayout);

            equalizeSound();
        }
    }

    /*
     * Sets up the lower level TextView
     */
    private void setUpLowerLevelTextView(short lowerEqualizerBandLevel, TextView lowerEqualizerBandLevelTextview) {
        lowerEqualizerBandLevelTextview.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        lowerEqualizerBandLevelTextview.setText((lowerEqualizerBandLevel / 100) + " dB");
    }

    /*
     * Sets up the linearLayout
     */
    private LinearLayout setUpLinearLayout() {
        LinearLayout seekBarRowLayout = new LinearLayout(this);
        seekBarRowLayout.setOrientation(LinearLayout.HORIZONTAL);
        return seekBarRowLayout;
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