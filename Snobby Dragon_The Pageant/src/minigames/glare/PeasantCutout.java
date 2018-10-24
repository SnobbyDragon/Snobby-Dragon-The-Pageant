package minigames.glare;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import general.SnobbyPanel;
import minigames.Thing;

public class PeasantCutout extends Thing implements Glareable {
	
	private static final String[] ANIMATIONS = new String[]{"Peasant Fly Cutout.gif", "Peasant Walk Cutout.gif", "Peasant Peasant Cutout.gif", "Peasant Hoverboard Cutout.gif"};
	private static final int[] RATES = new int[]{SnobbyPanel.FPS/5, SnobbyPanel.FPS/13, SnobbyPanel.FPS/18, SnobbyPanel.FPS/21};
	private GifDecoder cutout = new GifDecoder(); //fall and up animation for when glared at
	private int frame, rate; //counts the animation frames, rate at which the animation plays
	private boolean isShook, isIdle; //peasant glared at??, not visible/needs to rise from the ashes of shookness or already up

	public PeasantCutout(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		randomPeasant();
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		if (isShook) { //is falling!! SHOOK because Snobby glared
			frame++;
			if (frame == rate*cutout.getFrameCount()) { //cutout is utterly obliterated by the power of Snobby Dragon's glare, and thus resets
				randomPeasant();
			}
		}
		else if (!isIdle) { //is getting up
			frame--;
			if (frame == 0) { //cutout is completely up
				isIdle = true;
			}
		}
		int n = frame/rate%cutout.getFrameCount(); //frame counter
		window.drawImage(cutout.getFrame(n), getXPos(), getYPos(), null);
	}

	public void randomPeasant() { //sets this cutout to a random peasant type
		isIdle = true; //doing nothing
		isShook = false; //not shooketh till the Snobby glareth
		frame = rate*cutout.getFrameCount(); //ready for get up reverse animation
		
		int random = (int)(Math.random()*ANIMATIONS.length);
		cutout.read(ANIMATIONS[random]); //gets the animation
		rate = RATES[random]; //gets the corresponding animation rate
	}
	
	public void shouldGetUp() { //determines if the peasant should get up now
		if (Math.random() < 0.1) { //about 10% chance
			isIdle = false;
		}
	}
	
	public void becomeShooketh() { //got glared at so become shooketh
		isShook = true;
	}

}
