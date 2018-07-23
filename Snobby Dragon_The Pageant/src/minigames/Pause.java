package minigames;

import javax.swing.JButton;

import general.SnobbyRunner;

public class Pause extends JButton {
	
	public Pause() {
		this.setVisible(false);
		this.setBounds(SnobbyRunner.WIDTH - 60, 10, 50, 50);
		this.setOpaque(true);
	}
}
