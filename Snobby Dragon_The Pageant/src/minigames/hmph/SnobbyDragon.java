package minigames.hmph;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class SnobbyDragon extends Thing {

	private GifDecoder hmph = new GifDecoder(), idle = new GifDecoder(), walking = new GifDecoder();
	int isHmph, isIdle, isWalking; //counts frames
	boolean isMoving;

	public SnobbyDragon(int x, int y) {
		super(x, y);	
		
		isHmph = -1; //frame number of hmph animation, -1 means not hmphing
		isIdle = -1; //frame number of idle animation, -1 means not idle
		isWalking = 0; //frame number of walking animation, -1 means not moving
		
		isMoving = false; //whether moving across screen or not
		
		hmph.read("Snobby Hmph.gif");
		idle.read("Snobby Idle.gif");
		walking.read("Snobby Walk.gif");
	}
	
	@Override
	public void draw(Graphics window) {
		if (isHmph != -1) { //hmphs
			int n = isHmph/3%hmph.getFrameCount(); //temporary frame number
			window.drawImage(hmph.getFrame(n), getXPos(), getYPos(), null);
			isHmph++;
			if (isHmph == 3*hmph.getFrameCount()) {
				setHmph(-1);
			}
		}
		else if (isIdle != -1) { //idle animation
			int n = isIdle/3%idle.getFrameCount();
			window.drawImage(idle.getFrame(n), getXPos(), getYPos(), null);
			if (isIdle == 3*idle.getFrameCount()) {
				setIdle(0);
			}
			else {
				isIdle++;
			}
		}
		else if (isWalking != -1) {
			int n = isWalking/2%walking.getFrameCount();
			window.drawImage(walking.getFrame(n), getXPos(), getYPos(), null);
			if (isWalking == 2*walking.getFrameCount()) {
				setWalking(0);
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

}
