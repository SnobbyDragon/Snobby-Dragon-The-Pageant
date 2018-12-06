package general;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private static final String BACKGROUND_MUSIC = "";
	
	//hmph mini game sound effects
	private static final String HMPH_SOUND_EFFECT = "", PEASANT_SOUND_EFFECT = "";
	
	//insults mini game sound effects
	
	
	//glare mini game sound effects

	public Sound() {
		
	}

	public static synchronized void playSound(String sound) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(sound));
			clip.open(inputStream);
			clip.start(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
