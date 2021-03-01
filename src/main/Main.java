package main;

import loop.GameLoop;

public class Main {

	// defining width and height of our window
	public static int width = 800;
	public static int height = 600;
		
	public static void main(String[] args) {
		// create game window with title and size
		GameWindow gameWindow = new GameWindow("Design patterns - Demo", width, height);
		// create runnable code to loop and render
		gameWindow.add(new GameLoop(width, height));
		// enable the window
		gameWindow.setVisible(true);
	}

}
