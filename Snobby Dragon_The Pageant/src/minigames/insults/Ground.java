package minigames.insults;

import java.awt.Graphics2D;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import com.madgag.gif.fmsware.GifDecoder;

public class Ground extends PhysicsThing {
	
	private BodyFixture rectangle;
	private static GifDecoder ground = new GifDecoder("Insults Ground.gif"); //TODO make some ambient animation
	public static final int WIDTH = ground.getFrameSize().width, HEIGHT = ground.getFrameSize().height;

	public Ground(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		rectangle = new BodyFixture(Geometry.createRectangle(PixelToMeter(WIDTH) + 200, PixelToMeter(HEIGHT)));
		rectangle.setFriction(0.7);
		body.addFixture(rectangle);
		body.setMass(MassType.INFINITE);
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.drawImage(ground.getImage(), getXPos() - WIDTH/2, getYPos() - HEIGHT/2, null);
	}

}
