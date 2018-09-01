package minigames.glare;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import general.SnobbyRunner;
import minigames.MiniGame;

public class Glare extends MiniGame {
	
	private static final String HELP = "";
	private static final Color DARK = new Color(0f, 0f, 0f, 0.75f);
	private int hits, misses;
	
	private Monocle monocle;
	private ArrayList<Table> tables;
	
	public Glare() {
		super();
		setHelpBox(HELP);
		
		hits = 0;
		misses = 0;
		monocle = new Monocle(100, 100);
		tables = new ArrayList<Table>();
		tables.add(new Table(150, 120));
		tables.add(new Table(400, 120));
		tables.add(new Table(650, 120));
		tables.add(new Table(150, 280));
		tables.add(new Table(400, 280));
		tables.add(new Table(650, 280));
		tables.add(new Table(150, 440));
		tables.add(new Table(400, 440));
		tables.add(new Table(650, 440));
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
		window.setColor(DARK);
		window.fillRect(0, 0, SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		monocle.draw(window);
	}
	
	@Override
	public void drawBackground(Graphics2D window) {
		// TODO Auto-generated method stub
		for (Table t : tables) {
			t.draw(window);
		}
	}
	
	public void glare() { //glares at the current position
		monocle.shoot();
		//check hitbox and see if it hit or missed and increment appropriately
	}
	
	public Monocle getMonocle() {
		return monocle;
	}
	
	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

}
