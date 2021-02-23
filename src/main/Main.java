package main;

import loop.GameLoop;

public class Main {

	public static int width = 800;
	public static int height = 600;
		
	public static void main(String[] args) {
		GameWindow gameWindow = new GameWindow("Design patterns - Demo", width, height);
		gameWindow.add(new GameLoop(width, height));
		gameWindow.setVisible(true);
	}

}
