package general;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import minigames.HelpBox;
import minigames.MiniGame;
import minigames.glare.Glare;
import minigames.hmph.Hmph;
import minigames.insults.Insults;

/*
 * This panel contains all things mini games and transitions between them (scoring animations)
 */

public class SnobbyPanel extends JPanel implements Runnable, MouseListener, KeyListener {

	private static final int FPS = 40;
	private static final long TIME_PER_FRAME = 1000/FPS; //in milliseconds
	private static final long MINIGAME_TIME = 1000*30; //in milliseconds. 30 seconds per minigame
	private static final int BLOCK_NUMBER = 15, BLOCK_WIDTH = SnobbyRunner.WIDTH/BLOCK_NUMBER, BLOCK_SPEED = 15, BLOCK_GAP = 100; //blocks for wiping for transition
	private ArrayList<Block> blocks = new ArrayList<Block>(); //TODO: fix transitions between scenes to make it more global and also rework in general
	private boolean isBeginningScene; //is it transitioning from green screen to new scene
	private static int[] scores = new int[3];

	//mini game components
	private MiniGame game1;
	private MiniGame game2;
	private MiniGame game3;
	
	public SnobbyPanel() {
		setSize(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		setVisible(false);
		setIgnoreRepaint(true);
		setDoubleBuffered(true);
		new Thread(this).start();

		//interactive components
		addMouseListener(this);
		addKeyListener(this);

		//make blocks for the transition
		int blockStartPositionX = 0;
		int blockStartPositionY;
		while (blockStartPositionX + BLOCK_WIDTH <= SnobbyRunner.WIDTH) {
			if (blockStartPositionX/BLOCK_WIDTH%2 == 1) { //alternating blocks of different sizes
				blockStartPositionY = -1*BLOCK_GAP;
			}
			else {
				blockStartPositionY = -1*BLOCK_GAP/2;
			}
			blocks.add(new Block(blockStartPositionX, blockStartPositionY, BLOCK_WIDTH, 0));
			blockStartPositionX += BLOCK_WIDTH;
		}
		
		reset(); //begins the game anew
	}

	@Override
	//TODO: bug. the help box does not display when playing for the second time
	//basically draws everything on the screen so the user can see stuff
	public void paint(Graphics window) {
		//add graphics
		if (game1.getActive()) { //hmph game
			//System.out.println("Game 1!");
			game1.draw(window);
			if (game1.getPlaying()) {
				game1.setTime(game1.getTime() + TIME_PER_FRAME);
				//System.out.println("Time:" + game1.getTime());
				if (game1.getTime() >= MINIGAME_TIME) { //1 minute per minigame
					((Hmph) game1).getDragon().setMoving(true); //snobby walks out of screen when time is over
					if (((Hmph) game1).getDragon().getXPos() >= SnobbyRunner.WIDTH) { //when snobby is off screen, transition scenes and stop player interactions
						game1.setPlaying(false);
					}
				}
			}
			else if (!game1.getHelp()) { //if not playing and no help screen (which means end of game), then transition to next scene
				wipe(window, true); //scene is ending
				if (blocks.get(1).getHeight() >= SnobbyRunner.HEIGHT + BLOCK_GAP) { //when transitioning scene over, switch games
					isBeginningScene = true;
					game1.setActive(false);
					game2.setActive(true);
					game2.setHelp(true);
				}
			}
		}
/*commented out for now because more mini games will be implemented in the future
		else if (game2.getActive()) { //insults game
			//System.out.println("Game 2!");
			game2.draw(window);
			if (game2.getPlaying()) {
				System.out.println("game 2 playing");
				game2.setTime(game2.getTime() + TIME_PER_FRAME);
				if (game2.getTime() >= MINIGAME_TIME) {
					game2.setPlaying(false);
				}
			}
			else if (!game2.getHelp()) { //not playing and no help screen so it's over
				System.out.println("game 2 over");
				wipe(window, true);
				if (blocks.get(1).getHeight() >= SnobbyRunner.HEIGHT) { //when transitioning scene over, switch games
					game2.setActive(false);
					game3.setActive(true);
					game3.setHelp(true);
				}
			}
		}
		else if (game3.getActive()) { //glare game
			//System.out.println("Game 3!");
			game3.draw(window);
			if (game3.getPlaying()) {
				System.out.println("game 3 playing");
				game3.setTime(game3.getTime() + TIME_PER_FRAME);
				if (game3.getTime() >= MINIGAME_TIME) {
					game3.setPlaying(false);
				}
			}
			else if (!game3.getHelp()) {
				System.out.println("game 3 playing");
				wipe(window, true);
				if (blocks.get(1).getHeight() >= SnobbyRunner.HEIGHT) { //when transitioning scene over, game over
					game3.setActive(false);
				}
			}
		} */
		else { //minigames are over so go to score scene and record score
			setScores();
			SnobbyRunner.setScene(2);
			System.out.println("game over!");
		}
		if (isBeginningScene) { //transitioning from green wipe to new minigame
			wipe(window, false);
		}
	}

	public void wipe(Graphics window, boolean isEndingScene) { //transition between scenes. if ending scene, cover with green. if start (!end), uncover green.
		for (Block b : blocks) {
			b.draw(window);
			if (isEndingScene) { //ending scene so make it green	
				b.setHeight(b.getHeight() + BLOCK_SPEED);
			}
			else { //screen is green so drop from the top, starting next scene
				//System.out.println("ending");
				if (b.getYPos() >= SnobbyRunner.HEIGHT + BLOCK_GAP) { //done transitioning so reset
					b.setHeight(0);
					if (b.getYPos() == SnobbyRunner.HEIGHT + BLOCK_GAP) {
						b.moveY(-1*BLOCK_GAP);
					}
					else {
						b.moveY(-1*BLOCK_GAP/2);
					}
					isBeginningScene = false;
				}
				else { //not done
					b.moveY(b.getYPos() + BLOCK_SPEED);
				}
			}
		}
	}
	
	public void setScores() {
		scores[0] = game1.getScore();
		scores[1] = game2.getScore();
		scores[2] = game3.getScore();
	}
	
	public static int[] getScores() {
		return scores;
	}
	
	public MiniGame getGame(int i) {
		if (i == 1) {
			return game1;
		}
		if (i == 2) {
			return game2;
		}
		return game3;
	}
	
	public void reset() {
		game1 = new Hmph();
		game2 = new Insults();
		game3 = new Glare();
		
		//starts with game1
		game1.setActive(true);
		game1.setPlaying(false);
		game1.setHelp(true);
		
		//not transitioning between mini games
		isBeginningScene = false;

		//reset blocks
		for (Block b : blocks) {
			if (b.getYPos() >= SnobbyRunner.HEIGHT) {
				if (b.getYPos() == SnobbyRunner.HEIGHT + BLOCK_GAP) {
					b.moveY(-1*BLOCK_GAP);
				}
				else {
					b.moveY(-1*BLOCK_GAP/2);
				}
			}
		}
	}

	@Override
	public void update(Graphics window) {
		paint(window);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.currentThread();
				Thread.sleep(TIME_PER_FRAME);
				repaint();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getActive()) {
			if (game1.getHelp()) {
				game1.setHelp(false);
				((Hmph) game1).getDragon().setIdle(-1);
				((Hmph) game1).getDragon().setWalking(0);
				game1.setPlaying(true);
			}
		}
		else if (game2.getActive()) {
			if (game2.getHelp()) {
				game2.setHelp(false);
				game2.setPlaying(true);
			}
		}
		else if (game3.getActive()) {
			if (game3.getHelp()) {
				game3.setHelp(false);
				game3.setPlaying(true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getActive()) {
			if (game1.getPlaying()) {
				((Hmph) game1).charge();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getActive()) {
			if (game1.getPlaying()) {
				((Hmph) game1).hmph();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
