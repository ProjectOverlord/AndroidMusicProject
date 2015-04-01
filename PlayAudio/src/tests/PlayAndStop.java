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

import utils.os.OSDiscerner;

/**
 * Temporary class. It is the container for the main() and other functions the main calls.
 * The next commits will hopefully be attempts to create a better structure.
 * 
 * @author claudio
 *
 */
public class PlayAndStop {

	public static void main(String[] args) {
		
		JFrame frame= new JFrame();
		frame.setSize(200,200);
		JButton button=new JButton("play");
		frame.add(button);
		frame.setVisible(true);
		button.addActionListener(new AL());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static class AL implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
	
			music();
		}
	}
	
	public static void music() {
		
		String urlString = null;
		String urlSuffix = null;
		
		/*
		 * This if-construct evaluates the correct OS and builds the URL
		 * suffix consequentially.
		 */
		if (OSDiscerner.isUnix() || OSDiscerner.isMac()) {
			urlSuffix = "file://";
		} else {
			if (OSDiscerner.isWindows()) {
				urlSuffix = "file:\\";
			} else {
				System.err.println("OS non supportato");
				return;
			}
		}
		
		urlString = urlSuffix+(new File("audioFiles/test_mp3_0001.mp3").getAbsolutePath());
		
		URL url = null;
		
		
		try {
			url = new URL(urlString);
		} catch (MalformedURLException mue) {
			System.out.println("The URL is not valid.");
		    System.out.println(mue.getMessage());
			mue.printStackTrace();
		}
			
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
				
			clip.loop(1);
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}
		
	}
}
