package com.progettofondamenti.equalizeraudioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.equalizeraudioplayer.R;

/**
 * This class should be the one representing the button that calls the forward()
 * method of the player.
 *
 * @author team
 */
public class ForwardButton extends ImageButton{

	public ForwardButton(Context context) {
		super(context);
		findViewById(R.id.buttonFf);
	}

	public ForwardButton(Context context, AttributeSet attrs){
        super(context,attrs);
        findViewById(R.id.buttonFf);
    }

    public ForwardButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        findViewById(R.id.buttonFf);
    }
}
