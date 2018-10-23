package minigames.glare;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class PeasantCutout extends Thing implements Hittable {
	
	private static ArrayList<Entry<String, Integer>> animationToRate = new ArrayList<Entry<String, Integer>>(); //list of pairs
	private GifDecoder fall = new GifDecoder(); //falling animation for when glared at
	private int isFall;
	private int rate;

	public PeasantCutout(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isFall = -1; //not falling in the beginning because didn't get glared at
		randomPeasant(); //cutout becomes a random peasant
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		if (isFall != -1) { //is falling!! because Snobby glared
			isFall++;
		}
		int n = isFall/rate%fall.getFrameCount(); //frame counter
		window.drawImage(fall.getFrame(n), getXPos(), getYPos(), null);
	}
	
	public void randomPeasant() { //sets this cutout to a random peasant type
		int random = (int)(Math.random()*animationToRate.size()); //RNG
		fall.read(animationToRate.get(random).getKey()); //gets the animation
		rate = animationToRate.get(random).getValue(); //gets the corresponding frame rate
	}

}
