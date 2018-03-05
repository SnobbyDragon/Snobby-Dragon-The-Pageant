package minigames.glare;
import java.awt.Graphics;


import minigames.MiniGame;

public class Glare extends MiniGame {
	
	private static final String HELP = "";
	private int hits, misses;
	
	public Glare() {
		super();
		setHelpBox(HELP);
	}

	@Override
	public void calculateScore() {
		double percent = hits/(hits + misses)*100;
		int number = (int)Math.round(percent);
		setScore(number);
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
