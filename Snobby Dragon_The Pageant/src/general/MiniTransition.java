package general;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MiniTransition extends JPanel implements Runnable {
	
	private static final int BLOCK_NUMBER = 15, BLOCK_WIDTH = SnobbyRunner.WIDTH/BLOCK_NUMBER, BLOCK_SPEED = 15, BLOCK_GAP = 100; //blocks for wiping for transition
	private static ArrayList<Block> blocks = new ArrayList<Block>();

	public MiniTransition() {
		setSize(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		setVisible(false);
		setOpaque(false);
		setFocusable(false);
		setIgnoreRepaint(true);
		setDoubleBuffered(true);
		new Thread(this).start();
		
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
	}
	
	public void paint(Graphics g) { //transition minigames
		Graphics2D window = (Graphics2D)g;
		if (this.isVisible()) {
			wipe(window);
		}
	}
	
	public void go() {
		this.setVisible(true);
		System.out.println("transitioning");
	}
	
	public void reset() {
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).setHeight(0);
			if (i%2 == 1) {
				blocks.get(i).setY(-1*BLOCK_GAP);
			}
			else {
				blocks.get(i).setY(-1*BLOCK_GAP/2);
			}
		}
		setVisible(false);
	}
	
	public void wipe(Graphics2D window) {
		for (Block b : blocks) {
			b.draw(window);
			//System.out.println("HEIGHT: " + b.getHeight() + "\nY: " + b.getYPos());
			if (b.getHeight() < SnobbyRunner.HEIGHT + BLOCK_GAP) { //ending scene so make it green	
				b.setHeight(b.getHeight() + BLOCK_SPEED);
			}
			else { //screen is green so drop from the top, starting next scene
				//System.out.println("ending");
				if (b.getYPos() >= SnobbyRunner.HEIGHT) { //done transitioning so reset
					reset();
				}
				else { //not done
					b.setY(b.getYPos() + BLOCK_SPEED);
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
				Thread.sleep(SnobbyPanel.TIME_PER_FRAME);
				repaint();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
