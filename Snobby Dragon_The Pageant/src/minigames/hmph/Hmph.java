package minigames.hmph;
import java.awt.Graphics;
import java.lang.Math;
import java.util.ArrayList;

import general.SnobbyRunner;
import minigames.MiniGame;

public class Hmph extends MiniGame {
	
	private static final String HELP = "Hold down the left mouse button to charge up Snobby Dragon's hmph, and release the button to hmph at and offend nearby peasants! The more peasants offended, the higher your score!";
	private static final int HMPH_RANGE = 200;
	private int hmphHits, hmphTotal; //number of offended peasants (so 1 hmph can have 2 hmphHits), total number of hmphs
	private int rawScore; //each hmph that hits adds to this. adds the frame count of the hmph, so more charged = higher score!
	private HmphBackground background = new HmphBackground();
	private SnobbyDragon snobby = new SnobbyDragon(100, 100);
	private SnobbyBar snobMeter = new SnobbyBar(25, 25);
	private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	private final static int CLOUD_MAX = 20;
	private ArrayList<Tree> trees = new ArrayList<Tree>();
	private final static int TREE_MAX = 6;
	private ArrayList<Peasant> peasants = new ArrayList<Peasant>();
	
	public Hmph() {
		super();
		setHelpBox(HELP);
		hmphHits = 0;
		hmphTotal = 0;
		rawScore = 0;
		
		for (int a = 0; a < CLOUD_MAX; a++) {
			clouds.add(new Cloud(-420, (int) (Math.random()*150)));
		}
		int treeSpacing = 0; //trees spawn in clumps of #treeMAX, TODO: make tree spawning more random
		for (int a = 0; a < TREE_MAX; a++) {
			trees.add(new Tree(treeSpacing, 226));
			treeSpacing += SnobbyRunner.WIDTH/TREE_MAX; //width of screen divided by number of trees
		}
		peasants.add(new FlyingPeasant(-420, 100));
		peasants.add(new HoverboardPeasant(-420, 200));
		peasants.add(new WalkingPeasant(-420, 150));
		peasants.add(new PeasantPeasant(-420, 200));
	}
	
	@Override
	public void calculateScore() { //calculates score TODO: Update this later
		double accuracy = (double) (hmphHits)/hmphTotal; //hits to total hmphs ratio
		int score = (int) Math.round(accuracy*rawScore); //accuracy times raw score
		setScore(score);
	}

	@Override
	public void drawForeground(Graphics window) {
		// TODO Auto-generated method stub
		for (Peasant p : peasants) {
			if (p.getXPos() < -300 && Math.random() < 0.01) { //respawns if offscreen
				if (getPlaying()) { //only spawns peasants when game is active
					p.moveX(SnobbyRunner.WIDTH);
					p.setOffended(false);
				}
			}
			else {
				p.draw(window);
			}
		}
		snobby.draw(window); //snobby is in front of peasants
		snobMeter.draw(window);
	}
	
	@Override
	public void drawBackground(Graphics window) {
		// TODO Auto-generated method stub
		if (!getPlaying()) { //background doesn't move when not playing
			background.setStop(23); //frame 23 because at 24, will start moving again so only stops for 1 frame
		}
		background.draw(window);
		for (Cloud c : clouds) {
			if (c.getXPos() < -100 && Math.random() < 0.0015) { //respawns if offscreen
				c.moveX(SnobbyRunner.WIDTH);
				c.moveY((int) (Math.random()*150));
				c.setCloud();
			}
			else {
				c.draw(window);
			}
		}
		for (Tree t : trees) {
			if (!getPlaying()) { //trees doesn't move when not playing
				t.setStop(23); //frame 23 because at 24, will start moving again so only stops for 1 frame
			}
			if (t.getXPos() < -100) { //respawns if offscreen
				t.moveX(SnobbyRunner.WIDTH);
				t.setTree();
			}
			else {
				t.draw(window);
			}
		}
		//this is the range cutoff of hmph
		//window.drawLine(snobby.getXPos() + hmphRANGE, 0, snobby.getXPos() + hmphRANGE, 600);
		//window.drawLine(snobby.getXPos() - hmphRANGE/4, 0, snobby.getXPos() - hmphRANGE/4, 600);
	}
	
	public SnobbyDragon getDragon() {
		return snobby;
	}
	
	//mouse interactions
	public void charge() { //charges up snobby bar when mouse pressed
		if (snobMeter.getChargeUp() == -1 && snobMeter.getHmph() == -1) {
			snobMeter.setChargeUp(0);
			snobMeter.setIdle(false);
		}
	}
	
	public void hmph() { //hmphs when mouse released
		if (!snobMeter.getIdle()) { //can only hmph if snobby power is charging/charged
			snobMeter.setIdle(true);
			if (snobby.getHmph() == -1 && snobMeter.getHmph() == -1) { //if not currently hmphing, hmphs!
				snobby.setHmph(0); //snobby dragon hmphs
				hmphTotal++; //total hmphs increments
				for (Cloud c : clouds) {
					c.setSlow(0); //clouds don't move as fast because snobby dragon stops walking
				}
				for (Tree t : trees) {
					t.setStop(0); //trees don't move because snobby dragon stops walking
				}
				for (Peasant p : peasants) {
					if (snobby.getXPos() - HMPH_RANGE/4 < p.getXPos() && p.getXPos() < snobby.getXPos() + HMPH_RANGE) { //if in range, will be offended
						p.setSlow(0); //peasants don't move as fast because snobby dragon stopped
						p.setOffended(true);
						hmphHits++; //total hits increments
						if (snobMeter.getFullyCharged() != -1) { //if hit and fully charged
							rawScore += 7; //gain 7 points!
						}
						else { //not fully charged and hit
							rawScore += snobMeter.getChargeUp()/snobMeter.getChargeRate() + 1; //gain points equal to snobby bar's charge up level (+1 because frame/level 0)
						}
					}
				}
				background.setStop(0); //background stops moving because snobby dragon stops walking
				if (snobMeter.getFullyCharged() != -1) {
					snobMeter.setHmph(0); //if fully charged, snobby bar hmphs
				}
				snobMeter.setChargeUp(-1); //not charging
				snobMeter.setFullyCharged(-1); //not fully charged
			}
			calculateScore();
			//System.out.println(getScore());
		}
	}
}
