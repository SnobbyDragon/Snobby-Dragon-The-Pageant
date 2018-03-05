package general;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;

public class ScorePanel extends JPanel {
	
	private JButton next;
	private int step; //which step is the button on
	/* Steps:
	 * 0 = saves name and displays leaderboard
	 * 1 = goes back to home screen
	 */
	private JTextField name;
	private JLabel background;
	private ImageIcon scoreBackground = new ImageIcon("Score Background.gif"); //animated score background TODO: make this
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private static TreeSet<Player> players = new TreeSet<Player>();
	private Scanner scoreFile;
	private JTable leaderboard;
	private static final String[] COLUMN_TITLES = {"Name", "Hmph", "Insults", "Glare", "Total"};
	private static final Color LEADERBOARD_BACKGROUND = new Color(0, 0, 0, 75);

	public ScorePanel() {
		setLayout(null);
		setSize(SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		setVisible(false);
		step = 0;
		
		//leaderboard display area. displays the top 10 scores
		leaderboard = new JTable(11, 5); //11 by 5, 10 by 5 for scores and first row for titles
		leaderboard.setRowMargin(0);
		leaderboard.setShowVerticalLines(false);
		leaderboard.setShowGrid(false);
		leaderboard.setOpaque(false);
		leaderboard.setBackground(LEADERBOARD_BACKGROUND);
		leaderboard.setShowHorizontalLines(false);
		leaderboard.setRequestFocusEnabled(false);
		leaderboard.setRowSelectionAllowed(false);
		leaderboard.setEnabled(false);
		leaderboard.setVisible(false);
		leaderboard.setBounds(50, 50, 800, 425);
		leaderboard.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		leaderboard.setForeground(Color.WHITE);
		leaderboard.setRowHeight(leaderboard.getHeight()/leaderboard.getRowCount());
		leaderboard.getColumnModel().getColumn(0).setPreferredWidth(leaderboard.getWidth()*2/5);
		for (int column = 0; column < leaderboard.getColumnCount(); column++) {
			leaderboard.getModel().setValueAt(COLUMN_TITLES[column], 0, column);
			if (column != 0) {
				leaderboard.getColumnModel().getColumn(column).setPreferredWidth(leaderboard.getWidth()*3/5/4);
			}
		}
		add(leaderboard);
		
		//play again button
		next = new JButton("Next");
		next.setBounds(350, 500, 200, 50); //button placement
		next.setBackground(Color.ORANGE);
		next.setOpaque(true);
		next.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		next.setBorder(new BevelBorder(BevelBorder.RAISED));
		next.addActionListener(new ActionListener() { //when button clicked, do something
			@Override
			public void actionPerformed(ActionEvent e) {
				if (step == 0) { //saves the scores and displays leaderboard
					try {
						saveScore(SnobbyPanel.getScores()[0], SnobbyPanel.getScores()[1], SnobbyPanel.getScores()[2], name.getText());
						System.out.println("score saved");
						setLeaderboard();
						System.out.println("got top scores");
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					name.setVisible(false); //makes name textfield go away
					next.setText("Home"); //sets button to home
					leaderboard.setVisible(true); //display leaderboard
					step = 1;
				}
				else if (step == 1) {
					//Goes to next scene. Game --> Home
					SnobbyRunner.setScene(0);
					step = 0;
					name.setVisible(true);
					name.setText("Your Name");
					next.setText("Next");
					leaderboard.setVisible(false);
					System.out.println("clicked home");
				}
			}
			
		});
		add(next);
		
		//TODO: not have autofilled white spaces
		name = new JTextField();
		name.setDocument(new JTextFieldLimit(10));
		name.setText("Your Name");
		name.setBorder(new LineBorder(new Color(0, 0, 0)));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		name.setBounds(250, 400, 400, 75);
		add(name);
		
		background = new JLabel("");
		background.setLabelFor(this);
		background.setIcon(scoreBackground);
		background.setBounds(0, 0, SnobbyRunner.WIDTH, SnobbyRunner.HEIGHT);
		add(background);
	}
	
	public void saveScore(int hmphScore, int insultsScore, int glareScore, String name) throws IOException { //writes the score to the file
//		System.out.println(hmphScore);
//		System.out.println(insultsScore);
//		System.out.println(glareScore);
		fileWriter = new FileWriter("Leaderboard", true);
		bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.append(hmphScore + " " + insultsScore + " " + glareScore + " "); //saves scores
		bufferedWriter.append(name + "\n"); //saves name
		bufferedWriter.close();
	}
	
	public void setLeaderboard() throws FileNotFoundException { //gets the top scores and puts them in the table
		scoreFile = new Scanner(new File("Leaderboard"));
		int id = 0;
		while (scoreFile.hasNextLine()) {
			int hmph = scoreFile.nextInt();
			int insults = scoreFile.nextInt();
			int glare = scoreFile.nextInt();
			String name = scoreFile.nextLine();
			players.add(new Player(id, hmph, insults, glare, name));
			id++;
		}
		System.out.println(players);
		Object[] ps = players.toArray();
		int spot = ps.length - 1; //starts with the highest scoring player
		for (int row = 1; row < Math.min(ps.length + 1, leaderboard.getRowCount()); row++) {
			Player p = (Player) ps[spot];
			for (int column = 0; column < 5; column++) {
				switch (column) {
				case 0:
					leaderboard.getModel().setValueAt(p.getName(), row, column);
					break;
				case 1:
					leaderboard.getModel().setValueAt(p.getHmph() + "", row, column);
					break;
				case 2:
					leaderboard.getModel().setValueAt(p.getInsults() + "", row, column);
					break;
				case 3:
					leaderboard.getModel().setValueAt(p.getGlare() + "", row, column);
					break;
				case 4:
					leaderboard.getModel().setValueAt(p.getTotal() + "", row, column);
					break;
				}
			}
			spot--;
		}
	}
}
