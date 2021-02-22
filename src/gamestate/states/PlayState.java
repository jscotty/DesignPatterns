package gamestate.states;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;

import bullet.BulletMediator;
import gamestate.GameState;
import gamestate.GameStateManager;
import missile.Missile;
import missile.MissileFactory;
import missile.MissileMediator;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;
import turret.TurretMediator;
import ui.Button;

public class PlayState extends GameState {
	private Button backBtn;

	private TurretMediator turretMediator;
	private BulletMediator bulletMediator;
	private MissileMediator missileMediator;
	
	private boolean initialized = false;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		missileMediator = new MissileMediator();
		bulletMediator = new BulletMediator(missileMediator);
		turretMediator = new TurretMediator(bulletMediator);
		
		backBtn = new Button(100, 50,
				Sprites.instance.getBtnBackNormal(), Sprites.instance.getBtnBackHover(), Sprites.instance.getBtnBackPressed());
		backBtn.setScale(0.5f);
		
		backBtn.setOnClickAction(new OpenMenuState());
		
		initialized = true;
	}
	
	private class OpenMenuState extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			gsm.setState(new MenuState(gsm));
		}
	}

	@Override
	public void update(double deltaTime) {
		if(!initialized) {
			return;
		}
		
		turretMediator.update(deltaTime);
		missileMediator.update(deltaTime);
		bulletMediator.update(deltaTime);
		
		if(backBtn != null) {
			backBtn.update(deltaTime);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(!initialized) {
			return;
		}
		
		turretMediator.render(g);
		missileMediator.render(g);
		bulletMediator.render(g);

		if(backBtn != null) {
			backBtn.render(g);
		}
	}

	@Override
	public void dispose() {
		backBtn.dispose();
		
		turretMediator.dispose();
		missileMediator.dispose();
		bulletMediator.dispose();
	}

}
