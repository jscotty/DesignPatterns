package gamestate;

import java.awt.Graphics2D;
import java.util.Stack;

import gamestate.states.MenuState;

public class GameStateManager {
	
	public Stack<GameState> states = new Stack<GameState>();

	public void init() {
		states.peek().init();
	}
	
	public void update(double deltaTime){
		states.peek().update(deltaTime);
	}
	
	public void render(Graphics2D g){
		states.peek().render(g);
	}

	public void setState(GameState state) {
		GameState matchState = null;
		for (GameState gameState : states) {
			if(gameState.getClass() == state.getClass()) {
				matchState = gameState;
			}
		}
		
		// size check because states can be empty on first call
		if(states.size() != 0) {
			System.out.println("dispose!");
			states.peek().dispose();
		}
		
		if(matchState != null) {
			// match found
			states.push(matchState);
			System.out.println("push back old state");
		} else {
			// no match found, add parameter state
			states.push(state);
			System.out.println("push state");
		}
		init();
	}
}
