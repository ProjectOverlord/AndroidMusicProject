package tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

public class PlayAndStop {

	public static void main(String[] args) {
		
		Format input1= new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
				new Format[]{input1, input2},
				new Format[]{output},
				PlugInManager.CODEC
		);
		
		
		URL url = null;
		try {
			url = new URL("ChiamamiAncoraAmore.mp3");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Player audio = Manager.createPlayer(url);
		} catch (NoPlayerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}
