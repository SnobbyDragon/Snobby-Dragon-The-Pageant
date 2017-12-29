import java.awt.Graphics;

public abstract class MiniGame {
	private int score;
	
	public MiniGame() {
		setScore(0);
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
