package minigames.glare;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import general.SnobbyPanel;
import minigames.Thing;

public class PeasantCutout extends Thing implements Hittable {
	
	private static final String[] ANIMATIONS = new String[]{"Peasant Fly Cutout.gif", "Peasant Walk Cutout.gif", "Peasant Peasant Cutout.gif", "Peasant Hoverboard Cutout.gif"};
	private static final int[] RATES = new int[]{SnobbyPanel.FPS/5, SnobbyPanel.FPS/13, SnobbyPanel.FPS/18, SnobbyPanel.FPS/21};
	private GifDecoder fall = new GifDecoder(); //falling animation for when glared at
	private int isFall, rate; //counts the fall animation frames, rate at which the animation plays

	public PeasantCutout(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		randomPeasant();
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		if (isFall != -1) { //is falling!! because Snobby glared
			isFall++;
			if (isFall == rate*fall.getFrameCount()) { //cutout is utterly obliterated by the power of Snobby Dragon's glare, and thus resets
				
			}
		}
		int n = isFall/rate%fall.getFrameCount(); //frame counter
		window.drawImage(fall.getFrame(n), getXPos(), getYPos(), null);
	}

	public void randomPeasant() { //sets this cutout to a random peasant type
		isFall = -1; //not falling in the beginning because didn't get glared at
		
		int random = (int)(Math.random()*ANIMATIONS.length);
		fall.read(ANIMATIONS[random]); //gets the animation
		rate = RATES[random]; //gets the corresponding animation rate
	}

}
