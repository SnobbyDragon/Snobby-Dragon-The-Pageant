package minigames;
import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Thing {
	
	private final Color SnobbyGreen = new Color(0, 131, 4);
	private final int size;
	private int height;

	public Block(int x, int y, int blockSize, int blockHeight) {
		super(x, y);
		size = blockSize;
		height = blockHeight;
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.setColor(SnobbyGreen);
		window.fillRect(getXPos(), getYPos(), size, height);
		//System.out.println("transition");
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int i) {
		height = i;
	}
	
}
