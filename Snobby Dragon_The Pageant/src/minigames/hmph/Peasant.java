package minigames.hmph;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public abstract class Peasant extends Thing {
	
	private GifDecoder normal = new GifDecoder(), offended = new GifDecoder();
	private boolean isOffended;
	private int isSlow, frame;
	private final int SPEED, RATE;

	public Peasant(int x, int y, int s, int r, String normalAnimation, String offendedAnimation) {
		super(x, y);
		// TODO Auto-generated constructor stub
		frame = 0; //starts moving
		isOffended = false; //not offended
		isSlow = -1; //not slow
		SPEED = s; //sets the speed
		RATE = r; //sets the rate
		normal.read(normalAnimation); //reads in the animation
		offended.read(offendedAnimation); //reads in the animation
	}

	@Override
	public void draw(Graphics2D window) {
		int n = frame/RATE%normal.getFrameCount();
		if (!isOffended) { //if not offended then fly normally
			window.drawImage(normal.getFrame(n), getXPos(), getYPos(), null);
		}
		else { //if offended then be offended
			window.drawImage(offended.getFrame(n), getXPos(), getYPos(), null);
		}
		frame++;
		if (frame == RATE*normal.getFrameCount()) {
			frame = 0;
		}
		if (isSlow != -1) { //is slowed down
			setX(getXPos() - SPEED + 2);
			isSlow++;
			if (isSlow == SnobbyDragon.getHmphRate()*SnobbyDragon.getHmphFrame()) { //3*8 = 24, which is the hmph animation time
				isSlow = -1;
			}
		}
		else { //not slowed down
			setX(getXPos() - SPEED);
		}
	}
	
	public boolean getOffended() {
		return isOffended;
	}
	
	public int getSlow() {
		return isSlow;
	}
	
	public int getSpeed() {
		return SPEED;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public int getRate() { //the % (modular) number to adjust the fps speed
		return RATE;
	}
	
	public void setOffended(boolean b) {
		isOffended = b;
	}

	public void setSlow(int i) {
		isSlow = i;
	}
	
	public void setFrame(int i) {
		frame = i;
	}

}
