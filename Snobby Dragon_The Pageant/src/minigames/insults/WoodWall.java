package minigames.insults;

import org.dyn4j.geometry.MassType;

public class WoodWall extends Wall {

	public WoodWall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.getRectangle().setRestitution(0.5);
		this.getRectangle().setDensity(800);
		this.getRectangle().setFriction(0.8);
		this.setTexture((int)(Math.random()*3));
		body.setMass(MassType.NORMAL);
	}
}
