package minigames.hmph;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;
import java.util.ArrayList;

import minigames.MiniGame;

public class Hmph extends MiniGame {
	
	private final int hmphRANGE = 350;
	private int hmphHits, hmphTotal; //number of hmphs that hit, total number of hmphs
	private int rawScore; //each hmph that hits adds to this. adds the frame count of the hmph, so more charged = higher score!
	private HmphBackground background = new HmphBackground();
	private SnobbyDragon snobby = new SnobbyDragon(100, 100);
	private SnobbyBar snobMeter = new SnobbyBar(25, 25);
	private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	private final int cloudMAX = 20;
	private ArrayList<Tree> trees = new ArrayList<Tree>();
	private final int treeMAX = 6;
	private ArrayList<Peasant> peasants = new ArrayList<Peasant>();
	
	public Hmph() {
		super();
		hmphHits = 0;
		hmphTotal = 0;
		rawScore = 0;
		
		for (int a = 0; a < cloudMAX; a++) {
			clouds.add(new Cloud(-420, (int) (Math.random()*150)));
		}
		int treeSpacing = 0; //trees spawn in clumps of #treeMAX, maybe fix later
		for (int a = 0; a < treeMAX; a++) {
			trees.add(new Tree(treeSpacing, 225));
			treeSpacing += 900/treeMAX; //width of screen divided by number of trees
		}
		peasants.add(new FlyingPeasant(-420, 100));
		peasants.add(new WalkingPeasant(-420, 150));
	}
	
	@Override
	public void calculateScore() { //calculates score
		double percentAccuracy = hmphHits/hmphTotal*100; //hits to total hmphs ratio
		int score = (int) Math.round(percentAccuracy*rawScore); //accuracy times raw score
		setScore(score);
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		drawBackground(window);
		drawForeground(window);
	}

	@Override
	public void drawForeground(Graphics window) {
		// TODO Auto-generated method stub
		for (Peasant p : peasants) {
			if (p.getXPos() < -300) { //respawns if offscreen
				p.moveX(900);
				p.setOffended(false);
			}
			else {
				p.draw(window);
			}
		}
		snobby.draw(window); //snobby is in front of everything
		snobMeter.draw(window);
	}
	
	@Override
	public void drawBackground(Graphics window) {
		// TODO Auto-generated method stub
		background.draw(window);
		for (Cloud c : clouds) {
			if (c.getXPos() < -100 && Math.random() < 0.0015) { //respawns if offscreen
				c.moveX(900);
				c.moveY((int) (Math.random()*150));
				c.setCloud();
			}
			else {
				c.draw(window);
			}
		}
		for (Tree t : trees) {
			if (t.getXPos() < -100) { //respawns if offscreen
				t.moveX(900);
				t.setTree();
			}
			else {
				t.draw(window);
			}
		}
		//this is the range cutoff of hmph
		//window.drawLine(hmphRANGE, 0, hmphRANGE, 600);
	}
	
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
				for (Cloud c : clouds) {
					c.setSlow(0); //clouds don't move as fast because snobby dragon stops walking
				}
				for (Tree t : trees) {
					t.setSlow(0); //trees don't move as fast because snobby dragon stops walking
				}
				for (Peasant p : peasants) {
					if (0 < p.getXPos() && p.getXPos() < hmphRANGE) { //if in range, will be offended
						p.setSlow(0); //peasants don't move as fast because snobby dragon stopped
						p.setOffended(true);
						if (snobMeter.getFullyCharged() != -1) { //if hit and fully charged
							rawScore += 5; //gain 5 points
						}
						else { //not fully charged and hit
							rawScore += snobMeter.getChargeUp(); //gain points equal to snobby bar's charge up level
						}
						System.out.println(rawScore);
					}
				}
				background.setStop(0); //background stops moving because snobby dragon stops walking
				if (snobMeter.getFullyCharged() != -1) {
					snobMeter.setHmph(0); //if fully charged, snobby bar hmphs
				}
				snobMeter.setChargeUp(-1); //not charging
				snobMeter.setFullyCharged(-1); //not fully charged
			}
		}
	}
	
}
