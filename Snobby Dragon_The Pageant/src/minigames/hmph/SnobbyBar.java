package minigames.hmph;
import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class SnobbyBar extends Thing {
	
	private static GifDecoder chargeUp = new GifDecoder("Snobby Bar Charge Up.gif"), fullyCharged = new GifDecoder("Snobby Bar Fully Charged.gif"), hmph = new GifDecoder("Snobby Bar Hmph.gif");
	private int isChargeUp, isFullyCharged, isHmph; //frame counter
	private static final int chargeRATE = 4, fullyChargedRATE = 8;
	private boolean isIdle; //if not charging
	
	public SnobbyBar(int x, int y) {
		super(x,y);
		
		isChargeUp = -1; //not charging up
		isFullyCharged = -1; //not fully charged
		isHmph = -1; //not hmphing
		isIdle = true; //idle because not doing other things
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		if (isHmph != -1) { //hmphs
			int n = isHmph/SnobbyDragon.getHmphRate()%hmph.getFrameCount(); //TODO: make hmph slower so make it a variable
			window.drawImage(hmph.getFrame(n), getXPos(), getYPos(), null);
			isHmph++;
			if (isHmph == SnobbyDragon.getHmphRate()*hmph.getFrameCount()) {
				isHmph = -1;
			}
		}
		else if (isIdle) {
			window.drawImage(chargeUp.getFrame(0), getXPos(), getYPos(), null);
			isChargeUp = -1;
			isFullyCharged = -1;
		}
		if (isChargeUp != -1) { //charging up
			int n = isChargeUp/chargeRATE%chargeUp.getFrameCount();
			window.drawImage(chargeUp.getFrame(n), getXPos(), getYPos(), null);
			if (isChargeUp == chargeRATE*chargeUp.getFrameCount()) {
				isChargeUp = -1;
				isFullyCharged = 0;
			}
			else {
				isChargeUp++;
			}
		}
		if (isFullyCharged != -1) { // if fully charged
			int n = isFullyCharged/fullyChargedRATE%fullyCharged.getFrameCount();
			window.drawImage(fullyCharged.getFrame(n), getXPos(), getYPos(), null);
			if (isFullyCharged == fullyChargedRATE*fullyCharged.getFrameCount()) {
				isFullyCharged = 0;;
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
	
	public int getChargeRate() {
		return chargeRATE;
	}
	
	public int getFullyChargedRate() {
		return fullyChargedRATE;
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
