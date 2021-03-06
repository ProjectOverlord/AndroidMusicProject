package com.progettofondamenti.equalizeraudioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.equalizeraudioplayer.R;

/**
 * This class should be the one representing the button that calls the play()
 * method of the player.
 *
 * @author team
 */
public class PlayButton extends ImageButton {

	public  PlayButton (Context context, AttributeSet attrs){
		super(context, attrs);
		findViewById(R.id.buttonPlay);
	}

	public  PlayButton (Context context, AttributeSet attrs, int defStyleAttr){
		super(context, attrs, defStyleAttr);
		findViewById(R.id.buttonPlay);
	}

}
