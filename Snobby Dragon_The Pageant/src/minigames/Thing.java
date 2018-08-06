package minigames;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Thing {
	private Point position;
	
	public Thing(int x ,int y) {
		position = new Point(x, y);
	}
	
	public abstract void draw(Graphics2D window);
	
	public Point getPoint() {
		return position;
	}
	
	public int getXPos() {
		return position.x;
	}
	
	public int getYPos() {
		return position.y;
	}
	
	public void setX(int x) {
		// moves the x-position to x
		position.setLocation(x, position.y);
	}
	
	public void setY(int y) {
		// moves the y-position to y
		position.setLocation(position.x, y);
	}
	
}
