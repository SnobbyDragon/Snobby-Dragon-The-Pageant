package general;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import minigames.HelpBox;

public class SnobbyRunner extends JFrame{
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	//private ImageIcon icon = new ImageIcon("Icon.png"); //TODO: Make a custom icon and figure out how to implement it
	
	private static int scene;
	/* Scenes:
	 * -1 = splash screen (LoadingPanel)
	 * 0 = home screen (HomePanel)
	 * 1 = minigames (SnobbyPanel)
	 * 2 = final score screen (ScorePanel)
	 */
	
	private static JLayeredPane layers;
	private static HomePanel home;
	private static SnobbyPanel minigames;
	private static ScorePanel score;
	private static HelpBox help1, help2, help3;

	public SnobbyRunner() {
		super("Snobby Dragon: The Pageant");
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		//setIconImage(icon.getImage());
		scene = 0;
		
		layers = new JLayeredPane();
		layers.setBounds(0, 0, WIDTH, HEIGHT);
		add(layers);
		
		//adds home page graphics
		home = new HomePanel();
		layers.add(home);
		
		//adds game graphics
		minigames = new SnobbyPanel();
		layers.add(minigames);
		
		//adds help boxes
		setHelpBox();
		
		//adds score graphics
		score = new ScorePanel();
		layers.add(score);
	}

	public static void main(String[] args) {
		SnobbyRunner game = new SnobbyRunner();
		changeScene(); //goes to home screen in the beginning
	}
	
	public static void setHelpBox() {
		if (help1 != null && help2 != null && help3 != null) {
			layers.remove(help1);
			layers.remove(help2);
			layers.remove(help3);
		}
		help1 = minigames.getGame(1).getHelpBox();
		help2 = minigames.getGame(2).getHelpBox();
		help3 = minigames.getGame(3).getHelpBox();
		layers.add(help1);
		layers.add(help2);
		layers.add(help3);
		layers.moveToBack(minigames);
	}
	
	//TODO: Integrate scenes together better
	public static int getScene() {
		return scene;
	}
	
	public static void setScene(int i) { //changes the scene
		scene = i;
		changeScene();
	}
	
	public static void changeScene() {
		if (scene == 0) { //home
			home.setVisible(true);
			minigames.setVisible(false);
			score.setVisible(false);
		}
		else if (scene == 1) { //games
			home.setVisible(false);
			minigames.setVisible(true);
			score.setVisible(false);
		}
		else if (scene == 2) { //score
			minigames.reset(); //sets up in case player wants to play again
			setHelpBox();
			home.setVisible(false);
			minigames.setVisible(false);
			score.setVisible(true);
		}
	}
}