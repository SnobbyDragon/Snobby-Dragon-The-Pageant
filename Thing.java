import java.awt.Image;

public abstract class Thing {
	private int xPosition, yPosition; //position
	private int xSize, ySize;
	
	public Thing(int x ,int y) {
		xPosition = x;
		yPosition = y;
	}
	
	public Thing(int x, int y, int Xsize, int Ysize) {
		xPosition = x;
		yPosition = y;
		xSize = Xsize;
		ySize = Ysize;
	}
	
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
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}
	
}
