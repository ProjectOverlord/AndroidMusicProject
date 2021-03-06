package com.progettofondamenti.audioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.audioplayer.R;

/**
 * This class should be the one representing the button that calls the next()
 * method of the player.
 * @author team
 * @see android.widget.ImageButton
 */
public class NextButton extends ImageButton{


    public NextButton(Context context, AttributeSet attrs){
        super(context, attrs);
        findViewById(R.id.buttonNext);
    }


    public NextButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        findViewById(R.id.buttonNext);
    }
}
