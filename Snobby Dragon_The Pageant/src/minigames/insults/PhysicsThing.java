package minigames.insults;

import java.awt.Graphics2D;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import minigames.Thing;

public class PhysicsThing extends Thing {
	
	private static final int PIXELS_PER_METER = 100;
	protected Body body;
	
	public PhysicsThing(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		body = new Body();
		body.translate(new Vector2(PixelToMeter(x), PixelToMeter(y)));
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub

	}
	
	public static double PixelToMeter(int pixels) {
		return (double)pixels/PIXELS_PER_METER;
	}
	
	public static int MeterToPixel(double meters) {
		return (int) Math.round(meters*PIXELS_PER_METER);
	}

}
