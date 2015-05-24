package com.progettofondamenti.equalizeraudioplayer.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.progettofondamenti.equalizeraudioplayer.R;

/**
 * This class should be the one representing the button that calls the play()
 * method of the player.
 *
 * TODO: Is it possible to use a custom constructor in the XML?
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

//	public PlayButton(Context context, IPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
//		super(context);
//		findViewById(R.id.buttonPlay);
//		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
//	}
//
//	public PlayButton(Context context, AttributeSet attrs, IPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
//		super(context, attrs);
//		findViewById(R.id.buttonPlay);
//		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
//	}
//
//	public PlayButton(Context context, AttributeSet attrs, int defStyleAttr, IPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
//		super(context, attrs, defStyleAttr);
//		findViewById(R.id.buttonPlay);
//		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
//	}
//
//	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
//	public PlayButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//		findViewById(R.id.buttonPlay);
//		this.setOnClickListener(new PlayListener(player, seekBar, handler, barUpdater));
//	}
}
