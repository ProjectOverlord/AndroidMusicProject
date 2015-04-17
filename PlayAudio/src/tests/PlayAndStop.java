package tests;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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

	private static AudioInputStream audio;
	private static AudioFormat format;
	private static DataLine.Info info;
	private static Clip clip;
	
	public static void main(String[] args) {
		
		JFrame frame= new JFrame();
		frame.setSize(200,200);
		frame.getContentPane().setLayout(new GridLayout(2,1));
		
		JButton button1=new JButton("Play");
		frame.add(button1);
		frame.setVisible(true);
		button1.addActionListener(new AL1());
		
		JButton button2=new JButton("Stop");
		frame.add(button2);
		button2.addActionListener(new AL2());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static class AL1 implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
	
			musicPlay();
		}
	}
	
	public static class AL2 implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
	
			musicStop();
		}
	}
	
	public static void musicPlay() {
		
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
				System.err.println("OS not supported");
				return;
			}
		}
		
		urlString = urlSuffix+(new File("audioFiles/cyborg.wav").getAbsolutePath());
		
		URL url = null;
		
		
		try {
			url = new URL(urlString);
		} catch (MalformedURLException mue) {
			System.out.println("The URL is not valid.");
		    System.out.println(mue.getMessage());
			mue.printStackTrace();
		}
			
		try {
			audio = AudioSystem.getAudioInputStream(url);
			
			format = audio.getFormat();
			info = new DataLine.Info(Clip.class, format);
			
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audio);
							
			clip.start();
		} catch (UnsupportedAudioFileException uafe) {
			uafe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (LineUnavailableException lue) {
			lue.printStackTrace();
		}
		
	}
	
	public static void musicStop() {
		
		clip.stop();
	}
	
}