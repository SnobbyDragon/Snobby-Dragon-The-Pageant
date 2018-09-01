package minigames.glare;

import java.awt.Graphics2D;

import minigames.Thing;

public class Table extends Thing implements Hittable {
	
	private static final int WIDTH = 100, HEIGHT = 40;

	public Table(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.drawRect(getXPos(), getYPos(), WIDTH, HEIGHT);
	}

}
