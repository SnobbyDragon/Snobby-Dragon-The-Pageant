package minigames;
import java.awt.Graphics;

public abstract class MiniGame {
	
	private boolean isPlaying;
	private int score;
	
	public MiniGame() {
		isPlaying = false;
		setScore(0);
	}
	
	public boolean getPlaying() {
		return isPlaying;
	}
	
	public void setPlaying(boolean b) {
		isPlaying = b;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int number) {
		score = number;
	}
	
	public abstract void calculateScore();
	public abstract void draw(Graphics window);
	public abstract void drawForeground(Graphics window);
	public abstract void drawBackground(Graphics window);
	
}
