
package minigames.hmph;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class SnobbyDragon extends Thing {

	private static GifDecoder hmph = new GifDecoder("Snobby Hmph.gif"), idle = new GifDecoder("Snobby Idle.gif"), walking = new GifDecoder("Snobby Walk.gif");
	private int isHmph, isIdle, isWalking; //counts frames
	private final static int HMPH_RATE = 4, HMPH_FRAME = hmph.getFrameCount(), IDLE_RATE = 5, WALKING_RATE = 2;
	private boolean isMoving;

	public SnobbyDragon(int x, int y) {
		super(x, y);	
		
		isHmph = -1; //frame number of hmph animation, -1 means not hmphing
		isIdle = 0; //frame number of idle animation, -1 means not idle
		isWalking = -1; //frame number of walking animation, -1 means not moving
		isMoving = false; //whether moving across screen or not
	}
	
	@Override
	public void draw(Graphics window) {
		if (isHmph != -1) { //hmphs
			int n = isHmph/HMPH_RATE%HMPH_FRAME; //temporary frame number
			window.drawImage(hmph.getFrame(n), getXPos(), getYPos(), null);
			isHmph++;
			if (isHmph == HMPH_RATE*HMPH_FRAME) {
				isHmph = -1;
			}
		}
		else if (isIdle != -1) { //idle animation
			int n = isIdle/IDLE_RATE%idle.getFrameCount();
			window.drawImage(idle.getFrame(n), getXPos(), getYPos(), null);
			if (isIdle == IDLE_RATE*idle.getFrameCount()) {
				isIdle = 0;
			}
			else {
				isIdle++;
			}
		}
		else if (isWalking != -1) {
			int n = isWalking/WALKING_RATE%walking.getFrameCount();
			window.drawImage(walking.getFrame(n), getXPos(), getYPos(), null);
			if (isWalking == WALKING_RATE*walking.getFrameCount()) {
				isWalking = 0;
			}
			else {
				isWalking++;
			}
			if (isMoving) {
				moveX(getXPos() + 5);
			}
		}
	}
	
	public void setHmph(int i) {
		isHmph = i;
	}
	
	public void setIdle(int i) {
		isIdle = i;
	}
	
	public void setWalking(int i) {
		isWalking = i;
	}
	
	public void setMoving(boolean b) {
		isMoving = b;
	}
	
	public int getHmph() {
		return isHmph;
	}
	
	public int getIdle() {
		return isIdle;
	}
	
	public int getWalking() {
		return isWalking;
	}
	
	public boolean getMoving() {
		return isMoving;
	}
	
	public static int getIdleRate() {
		return IDLE_RATE;
	}
	
	public static int getWalkingRate() {
		return WALKING_RATE;
	}
	
	public static int getHmphRate() {
		return HMPH_RATE;
	}
	
	public static int getHmphFrame() {
		return HMPH_FRAME;
	}

}
