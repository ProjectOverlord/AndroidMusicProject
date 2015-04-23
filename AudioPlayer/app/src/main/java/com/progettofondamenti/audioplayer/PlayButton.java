package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * This class should be the one representing the button that calls the play()
 * method of the player.
 * It has yet to be used in the  MainActivity because of the problems with the initialization.
 *
 * TODO: How can we get an object of this class to represent an ImageButton in the XML?
 */
public class PlayButton extends ImageButton {

	public PlayButton(final Context context, final iPlayer player) {
		super(context);
		this.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				player.play();

				Toast.makeText(context, "Playing", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
