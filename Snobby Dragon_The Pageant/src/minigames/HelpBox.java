package minigames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


import javax.swing.SwingConstants;

public class HelpBox extends JPanel {
	
	private JLabel helpText, startText;
	private static final Color HELP_ORANGE = new Color(234, 166, 33); //color of the orange help frame
	private static final int HELP_X = 250, HELP_Y = 100, HELP_WIDTH = 400, HELP_HEIGHT = 300; //position of the help frame
	private static final String START = "Click Anywhere to Begin!";

	public HelpBox() {
		setLayout(null);
		setEnabled(false);
		setFocusable(false);
		setBounds(HELP_X, HELP_Y, HELP_WIDTH, HELP_HEIGHT);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(HELP_ORANGE, 5));
		setVisible(false);
		
		//instructions to play
		helpText = new JLabel();
		helpText.setHorizontalAlignment(SwingConstants.CENTER);
		helpText.setVerticalAlignment(SwingConstants.TOP);
		helpText.setLabelFor(this);
		helpText.setBounds(10, 5, HELP_WIDTH - 10, HELP_HEIGHT - 5);
		helpText.setFont(new Font("Goudy Old Style", Font.PLAIN, 25));
		helpText.setForeground(Color.BLACK);
		add(helpText);
		
		//start instructions
		startText = new JLabel(START);
		startText.setHorizontalAlignment(SwingConstants.CENTER);
		startText.setLabelFor(this);
		startText.setBounds(0, HELP_HEIGHT - 50, HELP_WIDTH, 50);
		startText.setFont(new Font("Goudy Old Style", Font.PLAIN, 25));
		startText.setForeground(HELP_ORANGE);
		add(startText);
	}
	
	public void setText(String text) {
		helpText.setText("<html>"+ text +"</html>");
	}
	
	public static int getHelpX() {
		return HELP_X;
	}
	
	public static int getHelpY() {
		return HELP_Y;
	}
	
	public static int getHelpWidth() {
		return HELP_WIDTH;
	}
	
	public static int getHelpHeight() {
		return HELP_HEIGHT;
	}

}
