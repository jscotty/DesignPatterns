package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GameWindow extends JFrame{
	
	// a simple game window extending from java native JFrame
	// showing a title and defining size
	public GameWindow(String title, int width, int height){
		setTitle(title);
		setSize(width, height);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
