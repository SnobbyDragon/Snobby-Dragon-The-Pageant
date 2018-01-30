package minigames.hmph;

import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

public class FlyingPeasant extends Peasant {
	
	private GifDecoder flying = new GifDecoder(), offended = new GifDecoder();
	private int isFlying, speed;

	public FlyingPeasant(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		speed = 12;
		isFlying = 0; //peasant starts flying
		flying.read("Peasant Fly.gif");
		offended.read("Peasant Fly Offended.gif");
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		int n = isFlying/4%flying.getFrameCount();
		if (!getOffended()) { //if not offended then fly normally
			window.drawImage(flying.getFrame(n), getXPos(), getYPos(), null);
		}
		else { //if offended then be offended
			window.drawImage(offended.getFrame(n), getXPos(), getYPos(), null);
		}
		isFlying++;
		if (isFlying == 4*flying.getFrameCount()) {
			setFlying(0);
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
	
	public int getFlying() {
		return isFlying;
	}
	
	public void setFlying(int i) {
		isFlying = i;
	}

}
