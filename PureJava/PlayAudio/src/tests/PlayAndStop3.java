package tests;

import java.applet.Applet;
import java.applet.AudioClip;

public class PlayAndStop3 extends Applet {
	
	public void init() {
		
		AudioClip music = getAudioClip(getDocumentBase(), "HeldenMusik.wav");
		
		music.play();
		music.stop();
		music.loop();
	}
	
}
