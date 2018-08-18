package minigames.insults;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import general.SnobbyPanel;
import general.SnobbyRunner;
import minigames.MiniGame;
import minigames.insults.SnobbyBorb;

public class Insults extends MiniGame {
	
	World world; //physics world
	AxisAlignedBounds bounds; //prevents overflow
	private static final Vector2 GRAVITY = new Vector2(0, 9.8);
	private static final double UPDATE_TIME = (double)SnobbyPanel.TIME_PER_FRAME/1000;
	
	private static final String HELP = "Use the arrow keys to aim the cannon and press enter to shoot snobby borbs!"; //TODO: instructions on how to play
	private static final String[] INSULTS = {"PEASANT", "UNCULTURED SWINE", "FOPDOODLE", "DONUT", "DONKEY", "IDIOT", "DUNCE", "CRETIN", "STALE BAGEL", "DUMMY"}; //word bank
	private int destruction; //how much disorder the borbs caused
	private int borbsFired; //how many borbs fired
	private int typedChars; //how many characters were typed
	private String currentInsult;
	private char currentLetter;
	private int currentSpot;
	private boolean canShoot;
	
	private ArrayList<SnobbyBorb> borbs;
	private ArrayList<Wall> walls;
	private int buildingType;
	private Ground ground;
	private Sky sky;
	private Cannon cannon;
	private static final int cannonPower = 7500;
	private static final double cannonAngleAdjustment = 0.05;
	private ArrayList<LetterBox> letterBoxes;
	private static final int boxX = 18, boxY = 16; //first box will always be here
	private static final int boxMargin = 80;
	
	public Insults() {
		super();
		setHelpBox(HELP);
		
		destruction = 0;
		borbsFired = 0;
		typedChars = 0;
		
		borbs = new ArrayList<SnobbyBorb>();
		walls = new ArrayList<Wall>();
		ground = new Ground(SnobbyRunner.WIDTH/2, SnobbyRunner.HEIGHT - Ground.HEIGHT/2);
		sky = new Sky(0, 0);
		
		bounds = new AxisAlignedBounds(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT); //TODO check bounds
		world = new World(bounds);
		world.setGravity(GRAVITY); //b/c origin is set a top left corner instead of bottom left
		for (SnobbyBorb borb : borbs) {
			world.addBody(borb.body);
		}
		world.addBody(ground.body);
		
		cannon = new Cannon(100, 450);
		letterBoxes = new ArrayList<LetterBox>(); //screen can fit 11 letter boxes per row. vertical margin of 16, horizontal margin of 16 (16 + 64 = 80) or 18 at the two ends
		randomInsult(); //prepares insult
		makeBuilding(); //creates first building
	}

	@Override
	public void calculateScore() {
		// TODO Auto-generated method stub
		int score = 0;
		score = typedChars*borbsFired + destruction;
		setScore(score);
	}

	@Override
	public void drawForeground(Graphics2D window) {
		// TODO Auto-generated method stub
		for (SnobbyBorb borb : borbs) {
			borb.draw(window);
		}
		for (Wall wall : walls) {
			wall.draw(window);
		}
		cannon.draw(window);
		ground.draw(window);
		for (LetterBox box : letterBoxes) {
			box.draw(window);
		}
		if (getPlaying()) {
			checkBuilding();
			world.update(UPDATE_TIME);
		}
	}

	@Override
	public void drawBackground(Graphics2D window) {
		// TODO Auto-generated method stub
		sky.draw(window);
	}

	public void makeBuilding() { //spawns new building
		buildingType = (int)(Math.random()*3);
		buildingType = 0; //TODO delete later
		switch (buildingType) {
		case 0: //simple
			int distance = (int)(Math.random()*50);
			walls.add(new StoneWall(425 + distance, 200));
			walls.add(new StoneWall(575 + distance, 200));
			walls.add(new WoodWall(500 + distance, 50));
			walls.get(2).body.rotateAboutCenter(Math.PI/-2);
			
			break;
		case 1: //normal
			break;
		case 2: //complex
			break;
		}
		for (Wall wall : walls) {
			world.addBody(wall.body);
		}
	}
	
	public void checkBuilding() { //is the building destroyed?
		switch (buildingType) {
		case 0: //simple
			if (walls.get(2).body.isInContact(ground.body)) { //roof on ground
				destruction += 25; //gain destruction points
				walls.clear(); //reset
				ArrayList<Body> bodies = new ArrayList<Body>(world.getBodies());
				for (int i = bodies.size() - 1; i > 0; i--) {
					if (bodies.get(i).getUserData() != null) {
						world.removeBody(i);
					}
				}
				makeBuilding();
			}
			break;
		case 1: //normal
			break;
		case 2: //complex
			break;
		}
	}

	public void randomInsult() {
		currentSpot = 0;
		currentInsult = INSULTS[(int) (Math.random()*INSULTS.length)];
		currentLetter = currentInsult.charAt(currentSpot);
		canShoot = false;
		System.out.println(currentInsult);
		letterBoxes.clear(); //remove all letter boxes
		while (letterBoxes.size() < currentInsult.length()) {
			letterBoxes.add(new LetterBox(boxX + boxMargin*(letterBoxes.size()%11), boxY + boxMargin*(letterBoxes.size()/11), currentInsult.charAt(letterBoxes.size())));
		}
	}
	
	public void writeLetter(char letter) {
		if (!canShoot) {
			if (letter == currentLetter) {
				//correct!
				System.out.println("correct!");
				letterBoxes.get(currentSpot).bounce(); //turn the box green and boing
				currentSpot++; //go on to the next letter
				if (currentSpot == currentInsult.length()) {
					//if word is done
					canShoot = true;
				}
				else {
					//still need to type
					currentLetter = currentInsult.charAt(currentSpot);
				}
			}
			else {
				//wrong!
				System.out.println("wrong!");
				//TODO: maybe make a penalty?
			}
		}
	}
	
	public void adjustCannon(boolean isUp) { //adjust angle and power
		if (isUp && cannon.getTheta() >= Math.PI/2*-1) {
			cannon.setTheta(cannon.getTheta() - cannonAngleAdjustment);
		}
		else if (!isUp && cannon.getTheta() <= 0) {
			cannon.setTheta(cannon.getTheta() + cannonAngleAdjustment);
		}
	}
	
	public void launchBorb() { //launches borb based on angle and power
		cannon.setIdle(1);
		SnobbyBorb borb = new SnobbyBorb((int) (cannon.getXPos() + cannon.getBase().getFrameSize().getWidth()/3), cannon.getYPos());
		borbs.add(borb);
		//TODO: below
//		double distance = PhysicsThing.PixelToMeter((int) cannon.getBarrel().getFrameSize().getWidth()/2);
//		borb.body.translate(new Vector2(distance*Math.sin(cannon.getTheta() + Math.PI/2), distance*Math.cos(cannon.getTheta() + Math.PI/2)));
		world.addBody(borb.body);
		borb.body.applyImpulse(new Vector2(cannonPower*Math.sin(cannon.getTheta() + Math.PI/2), cannonPower*Math.cos(cannon.getTheta() + Math.PI/2)));
		//TODO: FIX ANGLE CALCULATIONS (a tiny bit off right now)
		randomInsult(); //prepares a new insult to type
	}

	public boolean getShoot() {
		return canShoot;
	}

}
