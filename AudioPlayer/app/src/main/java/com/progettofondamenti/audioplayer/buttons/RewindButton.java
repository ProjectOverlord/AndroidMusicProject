package com.progettofondamenti.audioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.audioplayer.R;

/**
 * This class should be the one representing the button that calls the rewind()
 * method of the player.
 * @author team
 * @see android.widget.ImageButton
 */
public class RewindButton extends ImageButton{

	public RewindButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		findViewById(R.id.buttonRew);
	}

	public RewindButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		findViewById(R.id.buttonRew);
	}

}
