package minigames;
import java.awt.Graphics2D;


public abstract class MiniGame {
	
	private HelpBox help;
	private GameTimer time;
	private boolean isPlaying, isActive, isHelp; //the player can play and interact, the game is visible and actively displaying, help screen is displaying
	private int score;
	
	public MiniGame() {
		help = new HelpBox();
		isPlaying = false;
		isActive = false;
		isHelp = false;
		score = 0;
		time = new GameTimer();
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
		return time.getTimeMs();
	}
	
	public void setTime(long l) {
		time.setTimeMs(l);
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
	public void draw(Graphics2D window) {
		time.draw(window);
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
	public abstract void drawForeground(Graphics2D window);
	public abstract void drawBackground(Graphics2D window);
	
	public HelpBox getHelpBox() {
		return help;
	}
	
	public void setHelpBox(String text) {
		help.setText(text);
	}
	
}
