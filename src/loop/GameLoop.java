package loop;

import java.awt.image.BufferedImage;

import gamestate.GameStateManager;
import gamestate.states.MenuState;
import sprite.Sprites;

public class GameLoop  extends Loop {
	
	private BufferedImage background;
	
	private GameStateManager gsm;
	
	public GameLoop(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void init() {
		super.init();
		
		Sprites sprites = new Sprites();
		sprites.Init(); // creates singleton instance and sprites

		gsm = new GameStateManager();
		gsm.setState(new MenuState(gsm));
		
		background = Sprites.instance.getBG();
	}
	
	@Override
	public void tick(double deltaTime) {
		gsm.update(deltaTime);
	}
	
	@Override
	public void render() {
		graphics2D.drawImage(background, 0, 0, null);
		gsm.render(graphics2D);
		clear();
		super.render();
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
