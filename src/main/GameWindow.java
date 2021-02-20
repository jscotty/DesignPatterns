package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GameWindow extends JFrame{
	
	boolean fullscreenEnabled = false;
	int fullscreenMode = 0;
	private boolean hasBar = false;
	JMenuBar bar = new JMenuBar();
	
	
	public GameWindow(String title, int width, int height){
		setTitle(title);
		setSize(width, height);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void setFullscreen(int fsm){
		fullscreenEnabled = true;
		if(fullscreenMode  <= 2){
			fullscreenMode = fsm;
		} else {
			System.err.println("Error " + fsm + " is not supported!");
		}
	}
	
	public void setupMenu(String menuName){
		hasBar = true;
		
		bar.setBounds(0,0,800,25);
		add(bar);

		JMenu menuItem = new JMenu(menuName);
		bar.add(menuItem);
	}
	
	public void addMenuItem(String menuName){
		if(hasBar){
			JMenu menuItem = new JMenu(menuName);
			bar.add(menuItem);
		} else {
			setupMenu(menuName);
		}
	}
}
