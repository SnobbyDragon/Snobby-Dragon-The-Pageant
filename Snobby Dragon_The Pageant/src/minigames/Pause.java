package minigames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import general.SnobbyPanel;
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
		this.addActionListener(new ActionListener() { //when button clicked, do something
			@Override
			public void actionPerformed(ActionEvent e) {
				if (SnobbyPanel.isRunning) {
					SnobbyPanel.isRunning = false;
				}
				else {
					SnobbyPanel.isRunning = true;
					Pause.this.transferFocus(); //gives the game focus instead of the button so interactive functions work again
				}
				System.out.println(SnobbyPanel.isRunning);
			}
		});
	}
}
