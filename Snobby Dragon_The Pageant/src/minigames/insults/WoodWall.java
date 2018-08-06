package minigames.insults;

import java.awt.Graphics2D;

public class WoodWall extends Wall {

	public WoodWall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.getRectangle().setRestitution(0.5);
	}
}
