package minigames.insults;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;

import com.madgag.gif.fmsware.GifDecoder;

public class Wall extends PhysicsThing {
	
	private static GifDecoder textures = new GifDecoder("Walls.gif");
	private BufferedImage texture;
	private BodyFixture rectangle;
	private double radius; //diagonal from center to farthest point
	private static final int WIDTH = textures.getFrameSize().width, HEIGHT = textures.getFrameSize().height;

	public Wall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		rectangle = new BodyFixture(Geometry.createRectangle(PixelToMeter(WIDTH), PixelToMeter(HEIGHT)));
		radius = rectangle.getShape().getRadius();
		body.addFixture(rectangle);
	}
	
	public BodyFixture getRectangle() {
		return rectangle;
	}

	public void setRectangle(BodyFixture rectangle) {
		this.rectangle = rectangle;
	}
	
	public void setTexture(int frameNumber) {
		texture = textures.getFrame(frameNumber);
	}
	
	@Override
	public void draw(Graphics2D window) {
		AffineTransform transform = new AffineTransform();
		double theta = body.getTransform().getRotation();
		transform.translate(getXPos() - MeterToPixel(radius)*Math.cos(theta + Math.atan(HEIGHT/WIDTH)), getYPos() - MeterToPixel(radius)*Math.sin(theta + Math.atan(HEIGHT/WIDTH))); //accounts for center to upper left
		transform.rotate(theta); //rotates about center
		window.drawImage(texture, transform, null);
		setX(MeterToPixel(body.getWorldCenter().x));
		setY(MeterToPixel(body.getWorldCenter().y));
		//System.out.println(theta);
	}

}
