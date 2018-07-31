package minigames.glare;
import java.awt.Color;
import java.awt.Graphics2D;

import general.SnobbyRunner;
import minigames.MiniGame;

public class Glare extends MiniGame {
	
	private static final String HELP = "";
	private int hits, misses;
	private Monocle monocle;
	
	public Glare() {
		super();
		setHelpBox(HELP);
		
		hits = 0;
		misses = 0;
		monocle = new Monocle(0, 0);
	}

	@Override
	public void calculateScore() {
		double percent = hits/(hits + misses)*100;
		int number = (int)Math.round(percent);
		setScore(number);
		// TODO Auto-generated method stub

	}

	@Override
	public void drawForeground(Graphics2D window) {
		// TODO Auto-generated method stub
		monocle.draw(window);
	}
	
	@Override
	public void drawBackground(Graphics2D window) {
		// TODO Auto-generated method stub
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
	}
	
	public void glare() { //glares at the current position
		monocle.shoot();
	}
	
	public Monocle getMonocle() {
		return monocle;
	}

}
