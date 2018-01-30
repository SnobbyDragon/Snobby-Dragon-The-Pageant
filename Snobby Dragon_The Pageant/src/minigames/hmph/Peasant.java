package minigames.hmph;

import java.awt.Graphics;

import minigames.Thing;

public class Peasant extends Thing {
	
	private boolean isOffended;
	private int isSlow;
	

	public Peasant(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		setOffended(false); //not offended
		setSlow(-1); //not slow
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getOffended() {
		return isOffended;
	}
	
	public int getSlow() {
		return isSlow;
	}
	
	public void setOffended(boolean b) {
		isOffended = b;
	}

	public void setSlow(int i) {
		// TODO Auto-generated method stub
		isSlow = i;
	}

}
