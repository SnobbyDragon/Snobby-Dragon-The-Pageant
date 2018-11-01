package minigames.glare;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import general.SnobbyRunner;
import minigames.MiniGame;

public class Glare extends MiniGame {
	
	private static final String HELP = "Click on the peasants to glare at them! The more peasants you glare at, the more points you get!";
	private static final Color DARK = new Color(0f, 0f, 0f, 0.5f);
	private int hits, misses;
	
	private Monocle monocle;
	private ArrayList<Table> tables;
	private ArrayList<PeasantCutout> cutouts;
	
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
		cutouts = new ArrayList<PeasantCutout>();
		cutouts.add(new PeasantCutout(150, 120));
		cutouts.add(new PeasantCutout(400, 120));
		cutouts.add(new PeasantCutout(650, 120));
		cutouts.add(new PeasantCutout(150, 280));
		cutouts.add(new PeasantCutout(400, 280));
		cutouts.add(new PeasantCutout(650, 280));
		cutouts.add(new PeasantCutout(150, 440));
		cutouts.add(new PeasantCutout(400, 440));
		cutouts.add(new PeasantCutout(650, 440));
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
		for (PeasantCutout cutout : cutouts) {
			cutout.draw(window);
		}
		for (Table t : tables) {
			t.draw(window);
		}
	}

	public void glare() { //glares at the current position
		if (monocle.canShoot()) {
			monocle.shoot();
			//check hitbox and see if it hit or missed and increment appropriately
			boolean didHit = false;
			for (PeasantCutout cutout: cutouts) {
				if (cutout.getHitBox().contains(monocle.getPoint())) { //hit so SHOOKETH!!
					cutout.becomeShooketh();
					didHit = true;
				}
			}
			if (didHit) {
				hits++;
			}
			else {
				misses++;
			}
		}
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
