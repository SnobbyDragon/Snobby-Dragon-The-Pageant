package minigames.insults;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.madgag.gif.fmsware.GifDecoder;


public class SnobbyBorb extends PhysicsThing {
	
	private static GifDecoder idle = new GifDecoder("Snobby Borb Idle.gif"); //TODO: create animation of borb
	private int isIdle;
	private static final int IDLE_RATE = 8, IDLE_FRAME = idle.getFrameCount();
	private BodyFixture circle;
	private static final int RADIUS = (int) (idle.getFrameSize().width/2*0.8);
	
	public SnobbyBorb(int x, int y) {
		super(x, y);
		
		circle = new BodyFixture(Geometry.createCircle(PixelToMeter(RADIUS)));
		circle.setRestitution(0.8); //very inelastic for bounciness :D
		circle.setFriction(0.1);
		circle.setDensity(730);
		body.addFixture(circle);
		body.setMass(MassType.NORMAL);
		isIdle = 0;
	}

	@Override
	public void draw(Graphics2D window) {
		AffineTransform transform = new AffineTransform();
		transform.translate(getXPos() - RADIUS, getYPos() - RADIUS); //accounts for top left corner vs center
		transform.scale(0.8, 0.8);
		int n = isIdle/IDLE_RATE%IDLE_FRAME;
		window.drawImage(idle.getFrame(n), transform, null);
		isIdle++;
		if (isIdle == IDLE_RATE*IDLE_FRAME) {
			isIdle = 0;
		}
		setX(MeterToPixel(body.getWorldCenter().x));
		setY(MeterToPixel(body.getWorldCenter().y));
		//System.out.println(body.getWorldCenter());
	}

}
