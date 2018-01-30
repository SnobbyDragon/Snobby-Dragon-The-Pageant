package minigames;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Thing {
	private int xPosition, yPosition; //position
	
	public Thing(int x ,int y) {
		xPosition = x;
		yPosition = y;
	}
	
	public abstract void draw(Graphics window);
	
	public int getXPos() {
		return xPosition;
	}
	
	public void moveX(int x) {
		// moves the x-position to x
		xPosition = x;
	}
	
	public int getYPos() {
		return yPosition;
	}
	
	public void moveY(int y) {
		// moves the y-position to y
		yPosition = y;
	}
	
}
