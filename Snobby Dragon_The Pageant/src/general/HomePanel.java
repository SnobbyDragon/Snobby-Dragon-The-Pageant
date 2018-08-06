package general;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HomePanel extends JPanel {
	
	private JButton play; //TODO: make a reset scores data
	private JLabel background;
	private ImageIcon homeBackground = new ImageIcon("Snobby Homepage.jpg");
	
	public HomePanel() {
		setLayout(null);
		setSize(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		setVisible(false);
		
		//play button
		play = new JButton("Play!");
		play.setBounds(350, 500, 200, 50); //button placement
		play.setBackground(Color.ORANGE);
		play.setOpaque(true);
		play.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		play.setBorder(new BevelBorder(BevelBorder.RAISED));
		play.addActionListener(new ActionListener() { //when button clicked, do something
			@Override
			public void actionPerformed(ActionEvent e) {
				//Goes to next scene. Home --> Game
				SnobbyRunner.setScene(1);
				System.out.println("clicked play!");
			}
			
		});
		add(play);
		
		//background picture
		background = new JLabel("");
		background.setLabelFor(this);
		homeBackground.setImage(homeBackground.getImage().getScaledInstance(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT, java.awt.Image.SCALE_SMOOTH)); //resizes the icon so it fits
		background.setIcon(homeBackground);
		background.setBounds(0, 0, SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		add(background);
	}
}