package minigames.hmph;
import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class SnobbyBar extends Thing {
	
	private GifDecoder chargeUp = new GifDecoder(), fullyCharged = new GifDecoder(), hmph = new GifDecoder();
	private int isChargeUp, isFullyCharged, isHmph; //frame counter
	private boolean isIdle; //if not charging
	
	public SnobbyBar(int x, int y) {
		super(x,y);
		
		isChargeUp = -1; //not charging up
		isFullyCharged = -1; //not fully charged
		isHmph = -1; //not hmphing
		isIdle = true; //idle because not doing other things
		
		chargeUp.read("Snobby Bar Charge Up.gif");
		fullyCharged.read("Snobby Bar Fully Charged.gif");
		hmph.read("Snobby Bar Hmph.gif");
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		if (isHmph != -1) {
			int n = isHmph/3%hmph.getFrameCount();
			window.drawImage(hmph.getFrame(n), getXPos(), getYPos(), null);
			isHmph++;
			if (isHmph == 3*hmph.getFrameCount()) {
				setHmph(-1);
			}
		}
		else if (isIdle) {
			window.drawImage(chargeUp.getFrame(0), getXPos(), getYPos(), null);
			setChargeUp(-1);
			setFullyCharged(-1);
		}
		if (isChargeUp != -1) {
			int n = isChargeUp/3%chargeUp.getFrameCount();
			window.drawImage(chargeUp.getFrame(n), getXPos(), getYPos(), null);
			if (isChargeUp == 3*chargeUp.getFrameCount()) {
				setChargeUp(-1);
				setFullyCharged(0);
			}
			else {
				isChargeUp++;
			}
		}
		if (isFullyCharged != -1) { // if fully charged
			int n = isFullyCharged/6%fullyCharged.getFrameCount();
			window.drawImage(fullyCharged.getFrame(n), getXPos(), getYPos(), null);
			if (isFullyCharged == 6*fullyCharged.getFrameCount()) {
				setFullyCharged(0);
			}
			else {
				isFullyCharged++;
			}
		}
	}
	
	public int getChargeUp() {
		return isChargeUp;
	}
	
	public int getFullyCharged() {
		return isFullyCharged;
	}
	
	public int getHmph() {
		return isHmph;
	}
	
	public boolean getIdle() {
		return isIdle;
	}
	
	public void setChargeUp(int i) {
		isChargeUp = i;
	}
	
	public void setFullyCharged(int i) {
		isFullyCharged = i;
	}
	
	public void setHmph(int i) {
		isHmph = i;
	}
	
	public void setIdle(boolean b) {
		isIdle = b;
	}
}
