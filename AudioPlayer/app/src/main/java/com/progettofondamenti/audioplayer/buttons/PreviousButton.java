package com.progettofondamenti.audioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.audioplayer.R;

/**
 * This class should be the one representing the button that calls the previous()
 * method of the player.
 * @author team
 * @see android.widget.ImageButton
 */
public class PreviousButton extends ImageButton{

    /**
     * PreviousButton()
     * @param context,attrs
     */
    public PreviousButton(Context context, AttributeSet attrs){
        super(context, attrs);
        findViewById(R.id.buttonPrevious);
    }

    /**
     * previous Button()
     * @param context,attrs,defStyleAttr
     */
    public PreviousButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        findViewById(R.id.buttonPrevious);
    }
}
