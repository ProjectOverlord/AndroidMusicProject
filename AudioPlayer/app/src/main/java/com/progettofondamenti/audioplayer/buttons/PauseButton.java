package com.progettofondamenti.audioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.audioplayer.R;

/**
 * This class should be the one representing the button that calls the pause()
 * method of the player.
 * Created by Saeval on 16/05/2015.
 */
public class PauseButton extends ImageButton{

	public PauseButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		findViewById(R.id.buttonPause);
	}

	public PauseButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		findViewById(R.id.buttonPause);
	}
}
