package minigames.hmph;

import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

public class WalkingPeasant extends Peasant {
	
	private GifDecoder walking = new GifDecoder(), offended = new GifDecoder();
	private int speed, isWalking;

	public WalkingPeasant(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		speed = 9;
		isWalking = 0; //peasant starts walking
		walking.read("Peasant Walk.gif");
		offended.read("Peasant Walk Offended.gif");
	}
	
	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		int n = isWalking/2%walking.getFrameCount();
		if (!getOffended()) { //if not offended then walk normally
			window.drawImage(walking.getFrame(n), getXPos(), getYPos(), null);
		}
		else { //if offended then be offended
			window.drawImage(offended.getFrame(n), getXPos(), getYPos(), null);
		}
		isWalking++; //increments frame number
		if (isWalking == 2*walking.getFrameCount()) {
			setWalking(0);
		}
		if (getSlow() != -1) { //is slowed down
			moveX(getXPos() - speed + 2);
			setSlow(getSlow() + 1);
			if (getSlow() == 24) { //3*8 = 24, which is the hmph animation time
				setSlow(-1);
			}
		}
		else { //not slowed down
			moveX(getXPos() - speed);
		}
	}

	public int getWalking() {
		return isWalking;
	}
	
	public void setWalking(int i) {
		isWalking = i;
	}
}
