package general;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import minigames.MiniGame;
import minigames.glare.Glare;
import minigames.hmph.Hmph;
import minigames.insults.Insults;

/*
 * This panel contains all things mini games and transitions between them (scoring animations)
 */

//TODO: maybe rework this whole thing with Swing Timers and also put minigames on different panels??

public class SnobbyPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	public static boolean isRunning;
	public static final int FPS = 40;
	public static final long TIME_PER_FRAME = 1000/FPS; //in milliseconds
	public static final long MINIGAME_TIME = 1000*30; //in milliseconds. 30 seconds per minigame
	private static int[] scores = new int[3];
	private Keyboard keyboard;

	private class Keyboard extends KeyAdapter { //key bindings for keyboard input
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println(e.getKeyChar());
			if (game2.getPlaying()&&isRunning) { //if insults game and not paused
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					game2.adjustCannon(true);
					break;
				case KeyEvent.VK_LEFT:
					game2.adjustCannon(true);
					break;
				case KeyEvent.VK_DOWN:
					game2.adjustCannon(false);
					break;
				case KeyEvent.VK_RIGHT:
					game2.adjustCannon(false);
					break;
				case KeyEvent.VK_ENTER:
					if (game2.getShoot()) {
						game2.launchBorb();
					}
					break;
				}
				if (Character.isAlphabetic(e.getKeyChar()) || e.getKeyChar() == ' ') {
					System.out.println(e.getKeyChar());
					game2.writeLetter(Character.toUpperCase(e.getKeyChar()));
				}
			}
		}
	}

	//mini game components
	private Hmph game1;
	private Insults game2;
	private Glare game3;

	public SnobbyPanel() {
		setSize(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		setVisible(false);
		setFocusable(true);
		setIgnoreRepaint(true);
		setDoubleBuffered(true);
		new Thread(this).start();
		
		isRunning = true; //not paused

		//interactive components
		addMouseListener(this);
		addMouseMotionListener(this);
		keyboard = new Keyboard();
		addKeyListener(keyboard);

		reset(); //begins the game anew
	}

	@Override
	//basically draws everything on the screen so the user can see stuff
	public void paint(Graphics g) { //TODO during transitions, help screen pops up over it, so need to fix this. MAKE A NEW TRANSPARENT PANEL OR SOMETHING
		Graphics2D window = (Graphics2D)g;
		//add graphics
		if (game1.getActive()) { //game1 game
			//System.out.println("Game 1!");
			game1.draw(window);
			if (game1.getPlaying()) {
				game1.setTime(game1.getTime() + TIME_PER_FRAME);
				//System.out.println("Time:" + game1.getTime());
				if (game1.getTime() >= 1) {//MINIGAME_TIME) { //1 minute per minigame TODO: DELETE COMMENT LATER WHEN DONE
					game1.getDragon().setMoving(true); //snobby walks out of screen when time is over
					if (game1.getDragon().getXPos() >= SnobbyRunner.WIDTH) { //when snobby is off screen, transition scenes and stop player interactions
						game1.setPlaying(false);
					}
				}
			}
			else if (!game1.getHelp()) { //if not playing and no help screen (which means end of game), then transition to next scene
				//TODO: REWORK TRANSITIONS??
				SnobbyRunner.transition();
				game1.setActive(false);
				game2.setActive(true);
				game2.setHelp(true);
			}
		}
		else if (game2.getActive()) { //insults game
			//System.out.println("Game 2!");
			game2.draw(window);
			if (game2.getPlaying()) {
				//System.out.println("game 2 playing");
				game2.setTime(game2.getTime() + TIME_PER_FRAME);
				if (game2.getTime() >= MINIGAME_TIME) { //TODO: DELETE COMMENT LATER WHEN DONE
					game2.setPlaying(false);
				}
			}
			else if (!game2.getHelp()) { //not playing and no help screen so it's over
				System.out.println("game 2 over");
				SnobbyRunner.transition();
				game2.setActive(false);
				game3.setActive(true);
				game3.setHelp(true);
				
			}
		}
		else if (game3.getActive()) { //glare game
			//System.out.println("Game 3!");
			game3.draw(window);
			if (game3.getPlaying()) {
				//System.out.println("game 3 playing");
				game3.setTime(game3.getTime() + TIME_PER_FRAME);
				if (game3.getTime() >= MINIGAME_TIME) {
					game3.setPlaying(false);
				}
			}
			else if (!game3.getHelp()) {
				System.out.println("game 3 over");
				SnobbyRunner.transition();
				game3.setActive(false);
			}
		} 
		else { //minigames are over so go to score scene and record score
			setScores();
			SnobbyRunner.setScene(2);
			System.out.println("game over!");
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
				if (isRunning) {
					repaint();
				}
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
				game1.getDragon().setIdle(-1);
				game1.getDragon().setWalking(0);
				game1.setPlaying(true);
				requestFocus(); //gets focus from help so can use keyboard
			}
		}
		else if (game2.getActive()) {
			if (game2.getHelp()) {
				game2.setHelp(false);
				game2.setPlaying(true);
				requestFocus(); //gets focus from help so can use keyboard
			}
		}
		else if (game3.getActive()) {
			if (game3.getHelp()) {
				game3.setHelp(false);
				game3.setPlaying(true);
				requestFocus(); //gets focus from help so can use keyboard
			}
			game3.glare();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getActive()) {
			if (game1.getPlaying()) {
				game1.charge();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getActive()) {
			if (game1.getPlaying()) {
				game1.hmph();
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game3.getActive()) {
			game3.getMonocle().setX(e.getX());
			game3.getMonocle().setY(e.getY());
		}
	}

}
