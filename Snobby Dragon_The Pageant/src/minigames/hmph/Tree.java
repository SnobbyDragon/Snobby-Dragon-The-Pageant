package minigames.hmph;

import java.awt.Graphics2D;

import com.madgag.gif.fmsware.GifDecoder;

import minigames.Thing;

public class Tree extends Thing {
	
	private GifDecoder trees = new GifDecoder("Trees.gif");
	private int speed, isStop, whichTree;

	public Tree(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		speed = 5;
		isStop = -1; //not slow
		whichTree = (int) (Math.random()*(trees.getFrameCount() - 1));
	}

	@Override
	public void draw(Graphics2D window) {
		// TODO Auto-generated method stub
		window.drawImage(trees.getFrame(whichTree), getXPos(), getYPos(), null);
		if (isStop != -1) {
			isStop++;
			if (isStop == SnobbyDragon.getHmphRate()*SnobbyDragon.getHmphFrame()) { //3*8 = 24, which is the hmph animation time (rate*frames)
				isStop = -1;
			}
		}
		else {
			setX(getXPos() - speed);
		}
	}

	public void setTree() {
		if (Math.random() > 0.05) {
			whichTree = (int) (Math.random()*(trees.getFrameCount() - 2));
		}
		else {
			whichTree = trees.getFrameCount() - (int) (Math.random()*2) - 1;
			switch (whichTree) {
			case 9:
				System.out.println("swirly tree!");
				break;
			case 10:
			System.out.println("snobby tree!");
			break;
			}
		}
	}
	
	public int getStop() {
		return isStop;
	}
	
	public void setStop(int i) {
		isStop = i;
	}

}
