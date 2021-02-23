package loop;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import gamestate.GameStateManager;
import gamestate.states.MenuState;
import main.GameWindow;
import missile.Missile;
import missile.MissileFactory;
import missile.MissileNormal;
import missile.MissileType;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;

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
