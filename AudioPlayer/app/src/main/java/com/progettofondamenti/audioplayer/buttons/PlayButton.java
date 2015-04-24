package com.progettofondamenti.audioplayer.buttons;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.progettofondamenti.audioplayer.BarUpdater;
import com.progettofondamenti.audioplayer.R;
import com.progettofondamenti.audioplayer.iPlayer;

/**
 * This class should be the one representing the button that calls the play()
 * method of the player.
 * It has yet to be used in the  MainActivity because of the problems with the initialization.
 *
 * TODO: How can we get an object of this class to represent an ImageButton in the XML?
 */
public class PlayButton extends ImageButton {

	public PlayButton(Context context, iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
		super(context);
		findViewById(R.id.buttonPlay);
		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
	}

	public PlayButton(Context context, AttributeSet attrs, iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
		super(context, attrs);
		findViewById(R.id.buttonPlay);
		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
	}

	public PlayButton(Context context, AttributeSet attrs, int defStyleAttr, iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
		super(context, attrs, defStyleAttr);
		findViewById(R.id.buttonPlay);
		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PlayButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
		super(context, attrs, defStyleAttr, defStyleRes);
		findViewById(R.id.buttonPlay);
		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
	}
}
