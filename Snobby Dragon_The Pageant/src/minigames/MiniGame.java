package minigames;
import java.awt.Graphics;


public abstract class MiniGame {
	
	private HelpBox help;
	private long timeMs; //time the game has been active in milliseconds
	private boolean isPlaying, isActive, isHelp; //the player can play and interact, the game is visible and actively displaying, help screen is displaying
	private int score;
	
	public MiniGame() {
		help = new HelpBox();
		isPlaying = false;
		isActive = false;
		isHelp = false;
		score = 0;
		timeMs = 0;
	}
	
	public boolean getPlaying() {
		return isPlaying;
	}
	
	public void setPlaying(boolean b) {
		isPlaying = b;
	}
	
	public boolean getActive() {
		return isActive;
	}
	
	public void setActive(boolean b) {
		isActive = b;
	}
	
	public boolean getHelp() {
		return isHelp;
	}
	
	public void setHelp(boolean b) {
		isHelp = b;
	}
	
	public long getTime() {
		return timeMs;
	}
	
	public void setTime(long l) {
		timeMs = l;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int number) {
		score = number;
	}
	
	//calculates the score
	public abstract void calculateScore();
	
	//makes the graphics appear on the window
	public void draw(Graphics window) {
		drawBackground(window);
		drawForeground(window);
		if (isHelp) { //if help
			help.setVisible(true); //help box is displayed
		}
		else { //no help
			help.setVisible(false); //help box disappears
		}
	}

	//different layers of drawing
	public abstract void drawForeground(Graphics window);
	public abstract void drawBackground(Graphics window);
	
	public HelpBox getHelpBox() {
		return help;
	}
	
	public void setHelpBox(String text) {
		help.setText(text);
	}
	
}
