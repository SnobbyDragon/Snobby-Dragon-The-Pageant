package minigames.hmph;

import java.awt.Graphics;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Tree extends Thing {
	
	private GifDecoder trees = new GifDecoder();
	private int speed, isSlow, whichTree;

	public Tree(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		speed = 5;
		isSlow = -1; //not slow
		
		trees.read("Trees.gif");
		whichTree = (int) (Math.random()*(trees.getFrameCount() - 1));
	}

	@Override
	public void draw(Graphics window) {
		// TODO Auto-generated method stub
		window.drawImage(trees.getFrame(whichTree), getXPos(), getYPos(), null);
		if (isSlow != -1) {
			isSlow++;
			if (isSlow == 24) { //3*8 = 24, which is the hmph animation time
				setSlow(-1);
			}
		}
		else {
			moveX(getXPos() - speed);
		}
	}
	
	public void setTree() {
		if (Math.random() > 0.05) {
			whichTree = (int) (Math.random()*(trees.getFrameCount() - 2));
		}
		else {
			whichTree = trees.getFrameCount() - (int) (Math.random()*2) - 1;
			if (whichTree == 9) {
				System.out.println("swirly tree!");
			}
			else if (whichTree == 10) {
				System.out.println("snobby tree!");
			}
		}
	}
	
	public int getSlow() {
		return isSlow;
	}
	
	public void setSlow(int i) {
		isSlow = i;
	}

}
