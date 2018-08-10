package minigames.insults;

import org.dyn4j.geometry.MassType;

public class StoneWall extends Wall {

	public StoneWall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.getRectangle().setRestitution(0.05);
		this.getRectangle().setDensity(1500);
		this.getRectangle().setFriction(0.6);
		this.setTexture((int)(Math.random()*2 + 3));
		body.setMass(MassType.NORMAL);
	}

}
