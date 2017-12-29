import java.awt.Graphics;

public class Glare extends MiniGame {
	
	private int hits, misses;
	
	public Glare() {
		super();
	}

	@Override
	public void calculateScore() {
		double percent = hits/(hits + misses)*100;
		int number = (int)Math.round(percent);
		setScore(number);
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		
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
