package gamestate;

import java.awt.Graphics2D;

public abstract class GameState {

	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}

	public abstract void init();
	public abstract void update(double deltaTime);
	public abstract void render(Graphics2D g);
	public abstract void dispose();

}