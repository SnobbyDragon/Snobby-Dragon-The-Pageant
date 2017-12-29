import java.awt.Graphics;
import java.lang.Math;

public class Hmph extends MiniGame {
	
	private int hits, misses;
	private Dragon snobby = new Dragon(null, 100, 100);
	
	public Hmph() {
		super();
	}
	
	@Override
	public void calculateScore() {
		double percent = hits/(hits + misses)*100;
		int number = (int)Math.round(percent);
		setScore(number);
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		snobby.draw(window);
	}

	@Override
	public void drawForeground(Graphics window) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void drawBackground(Graphics window) {
		// TODO Auto-generated method stub
		
	}
	
}
