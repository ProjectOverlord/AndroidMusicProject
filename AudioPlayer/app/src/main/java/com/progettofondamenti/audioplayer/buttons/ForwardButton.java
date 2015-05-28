package com.progettofondamenti.audioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.audioplayer.R;

/**
 * This class should be the one representing the button that calls the forward()
 * method of the player.
 * @author  team
 * @see android.widget.ImageButton
 */
public class ForwardButton extends ImageButton{

    /**
     * ForwardButton()
     * @param context
     */
	public ForwardButton(Context context) {
		super(context);
		findViewById(R.id.buttonFf);
	}

    /**
     * ForwardButton()
     * @param context,attrs
     */
	public ForwardButton(Context context, AttributeSet attrs){
        super(context,attrs);
        findViewById(R.id.buttonFf);
    }

    /**
     * ForwardButton()
     * @param context,attrs,defStyleAttr
     */
    public ForwardButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        findViewById(R.id.buttonFf);
    }
}
