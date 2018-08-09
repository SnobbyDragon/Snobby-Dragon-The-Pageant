package minigames.insults;

import org.dyn4j.geometry.MassType;

public class MetalWall extends Wall {

	public MetalWall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.getRectangle().setRestitution(0.2);
		this.getRectangle().setDensity(2000);
		this.getRectangle().setFriction(0.2);
		this.setTexture((int)(Math.random()*2 + 7));
		body.setMass(MassType.NORMAL);
	}

}
