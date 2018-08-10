package minigames.glare;
import java.awt.Color;
import java.awt.Graphics2D;

import general.SnobbyRunner;
import minigames.MiniGame;
import minigames.hmph.PeasantPeasant;

public class Glare extends MiniGame {
	
	private static final String HELP = "";
	private static final Color DARK = new Color(0f, 0f, 0f, 0.75f);
	private int hits, misses;
	private Monocle monocle;
	private PeasantPeasant p;
	
	public Glare() {
		super();
		setHelpBox(HELP);
		
		hits = 0;
		misses = 0;
		monocle = new Monocle(100, 100);
		p = new PeasantPeasant(1500, 300);
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
		p.draw(window);
		window.setColor(DARK);
		window.fillRect(0, 0, SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		monocle.draw(window);
	}
	
	@Override
	public void drawBackground(Graphics2D window) {
		// TODO Auto-generated method stub
	}
	
	public void glare() { //glares at the current position
		monocle.shoot();
	}
	
	public Monocle getMonocle() {
		return monocle;
	}

}
