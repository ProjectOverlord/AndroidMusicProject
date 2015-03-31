package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

/* Classe di prova con main */
public class PlayAndStop {
	// Commenti test 2 (Manuel)

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
			
		String urlString = "file://"+(new File("audioFiles/test_mp3_0001.mp3").getAbsolutePath());
		URL url=null;
		
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.out.println("The URL is not valid.");
		    System.out.println(e.getMessage());
			e.printStackTrace();
		}
			
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
				
			clip.loop(1);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
}
