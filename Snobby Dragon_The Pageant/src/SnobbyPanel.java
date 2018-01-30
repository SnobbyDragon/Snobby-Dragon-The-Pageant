import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import minigames.glare.Glare;
import minigames.hmph.Hmph;
import minigames.insults.Insults;

public class SnobbyPanel extends JPanel implements Runnable, MouseListener, KeyListener {
	
	//mini game components
	private Hmph game1 = new Hmph();
	private Insults game2 = new Insults();
	private Glare game3 = new Glare();

	public SnobbyPanel() {
		setVisible(true);
		setIgnoreRepaint(true);
		setDoubleBuffered(true);
		new Thread(this).start();
		
		//interactive components
		addMouseListener(this);
		addKeyListener(this);
		
		//not playing in the beginning because intro scene
		game1.setPlaying(true); //change this to false later
		game2.setPlaying(false);
		game3.setPlaying(false);
	}

	@Override
	public void paint(Graphics window) {
		//add graphics
		window.setColor(Color.WHITE);
		window.fillRect(0, 0, getWidth(), getHeight());
		
		if (game1.getPlaying()) { //hmph game
			game1.draw(window);
		}
		else if (game2.getPlaying()) { //insults game
			game2.draw(window);
		}
		else if (game3.getPlaying()) { //glare game
			game3.draw(window);
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
				Thread.sleep(40); //a smidgen slower than 24 fps
				repaint();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stu
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getPlaying()) {
			game1.charge();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (game1.getPlaying()) {
			game1.hmph();
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
