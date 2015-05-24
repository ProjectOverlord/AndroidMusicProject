package com.progettofondamenti.equalizeraudioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.equalizeraudioplayer.R;

/**
 * This class should be the one representing the button that calls the rewind()
 * method of the player.
 * Created by Saeval on 16/05/2015.
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
