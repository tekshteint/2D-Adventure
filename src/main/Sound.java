package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30]; //store filePath of sound files
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/theme.wav");
		soundURL[1] = getClass().getResource("/sounds/sounds_token.wav");
		soundURL[2] = getClass().getResource("/sounds/sounds_powerup.wav");
		soundURL[3] = getClass().getResource("/sounds/sounds_door.wav");
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}