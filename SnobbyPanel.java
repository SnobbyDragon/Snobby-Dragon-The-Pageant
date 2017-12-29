import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

public class SnobbyPanel extends JPanel implements Runnable {
	
	private MiniGame hmph = new Hmph();

	public SnobbyPanel() {
		setVisible(true);
		setIgnoreRepaint(true);
		setDoubleBuffered(true);
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics window) {
		//add graphics
		window.setColor(Color.WHITE);
		window.fillRect(0, 0, getWidth(), getHeight());
		
		hmph.draw(window);

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
				Thread.sleep(50); //a smidgen slower than 24 fps
				repaint();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
