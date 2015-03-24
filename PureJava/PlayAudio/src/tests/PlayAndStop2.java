package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PlayAndStop2 {

	public static void main(String[] args) {
		
		JFrame frame= new JFrame();
		frame.setSize(200,200);
		JButton button=new JButton("play");
		frame.add(button);
		frame.setVisible(true);
		button.addActionListener(new AL());
	}
	
	public static class AL implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
	
			music();
		}
	}
	
	public static void music() {
	
		/*AudioPlayer MGP= AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop= null;
	
		try {
			BGM= new AudioStream(new FileInputStream("midi.wav"));
			MD= BGM.getData();
			loop= new ContinuousAudioDataStream(MD);
		} catch(IOException error) {
			
		}
		
		MGP.start(loop);*/
		
		Clip clip;
		try {
			URL url=null;
			url = new URL("HeldenMusik.wav");
			
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			
			clip = AudioSystem.getClip();
			clip.open(audio);
			
			clip.loop(1);
		} catch (UnsupportedAudioFileException
				| IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
