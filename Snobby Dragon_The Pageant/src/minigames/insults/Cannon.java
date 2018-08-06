package minigames.insults;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Cannon extends Thing {
	
	private static GifDecoder barrel = new GifDecoder("Cannon Fire.gif"), base = new GifDecoder("Cannon Base.gif"); //TODO: make cannon animations
	private int isIdle; //is it idle or firing (0 for idle)
	private double theta; //rotation

	public Cannon(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		isIdle = 0;
		setTheta(0);
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		AffineTransform transform = new AffineTransform();
		transform.translate(getXPos(), getYPos() - barrel.getFrameSize().getHeight()/2);
		transform.rotate(theta, barrel.getFrameSize().getWidth()/5, barrel.getFrameSize().getHeight()/2);
		int n = isIdle;
		window.drawImage(barrel.getFrame(n), transform, null);
		window.drawImage(base.getImage(), getXPos(), getYPos(), null); //draws base platform
		if (isIdle >= 1) {
			isIdle++;
			if (isIdle == barrel.getFrameCount()) {
				isIdle = 0;
			}
		}
	}
	
	public int getIdle() {
		return isIdle;
	}
	
	public void setIdle(int i) {
		isIdle = i;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public GifDecoder getBarrel() {
		return barrel;
	}
	
	public GifDecoder getBase() {
		return base;
	}

}
