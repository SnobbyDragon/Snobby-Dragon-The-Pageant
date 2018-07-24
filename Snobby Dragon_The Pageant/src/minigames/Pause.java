package minigames;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import general.SnobbyRunner;

public class Pause extends JButton {
	
	private ImageIcon defaultIcon = new ImageIcon("Pause Button.gif"), rolloverIcon = new ImageIcon("Pause Button.gif"), pressedIcon = new ImageIcon("Pause Button.gif");
	//TODO animate???
	
	public Pause() {
		this.setVisible(false);
		this.setBounds(SnobbyRunner.WIDTH - 60, 10, 50, 50);
		this.setOpaque(true);
		this.setIcon(defaultIcon);
		this.setRolloverIcon(rolloverIcon);
		this.setPressedIcon(pressedIcon);
	}
}
