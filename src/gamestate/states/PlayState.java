package gamestate.states;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;

import gamestate.GameState;
import gamestate.GameStateManager;
import missile.Missile;
import missile.MissileFactory;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;
import ui.Button;

public class PlayState extends GameState {

	private static final int normalMissilesCount = 10;
	private static final int fastMissilesCount = 5;
	private static final int slowMissilesCount = 15;
	private static final int randomMissilesCount = 0;
	
	private static final boolean loopCreations = true;
	
	private MissileFactory missileFactory;
	private Random random;
	private Button backBtn;

	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Missile> keepMissilesAlive = new ArrayList<Missile>();
	
	private ArrayList<TurretBase> turrets = new ArrayList<TurretBase>();

	private float randomX() {
		return 10 + random.nextFloat() * (750);
	}
	
	private float randomY() {
		return random.nextFloat() * (-500);
	}

	public PlayState(GameStateManager gsm) {
		super(gsm);

		missileFactory = new MissileFactory(normalMissilesCount, fastMissilesCount, slowMissilesCount);
		random = new Random();
	}

	@Override
	public void init() {
		
		int size = normalMissilesCount + fastMissilesCount + slowMissilesCount + randomMissilesCount;
		for (int i = 0; i < size; i++) {
			float x = randomX();
			float y = randomY();

			missiles.add(missileFactory.getMissile(x, y, i));
		}
		
		TurretFactory turretFactory = new TurretFactory();
		int turretCount = 6;
		for (int i = 0; i < turretCount; i++) {
			float x = ((800 / turretCount) / 2) + i * (800 / turretCount);
			turrets.add(turretFactory.getTurret(x, 500, i, turretCount));
		}
		
		backBtn = new Button(100, 50,
				Sprites.instance.getBtnBackNormal(), Sprites.instance.getBtnBackHover(), Sprites.instance.getBtnBackPressed());
		backBtn.setScale(0.5f);
		
		backBtn.setOnClickAction(new OpenMenuState());
	}
	
	private class OpenMenuState extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			gsm.setState(new MenuState(gsm));
		}
	}

	@Override
	public void update(double deltaTime) {
		List<Missile> removeMissiles = new ArrayList<Missile>();
		
		for (TurretBase turret : turrets) {
			turret.update(deltaTime);
		}
		
		for (Missile missile : missiles) {
			missile.update(deltaTime);

			if(missile.isStopped()) {
				removeMissiles.add(missile);
			}
		}
		
		loopCreations(removeMissiles);
		
		if(backBtn != null) {
			backBtn.update(deltaTime);
		}
	}
	
	private void loopCreations(List<Missile> removeMissiles) {
		if(!loopCreations) {
			return;
		}
		
		if(removeMissiles.size() > 0) {
			missiles.removeAll(removeMissiles);
			keepMissilesAlive.addAll(removeMissiles);
			for (int i = 0; i < removeMissiles.size(); i++) {
				missiles.add(missileFactory.getMissile(randomX(), randomY(), removeMissiles.get(i).getCreationType()));
			}
		}
		
		while (keepMissilesAlive.size() > 15) {
			keepMissilesAlive.remove(0); // removing missile when it reached a limit for performance
		}
	}

	@Override
	public void render(Graphics2D g) {
		for (TurretBase turret : turrets) {
			turret.render(g);
		}
		
		for (Missile missile : missiles) {
			missile.render(g);
		}
		
		for (Missile missile : keepMissilesAlive) {
			missile.render(g);
		}

		if(backBtn != null) {
			backBtn.render(g);
		}
	}

	@Override
	public void dispose() {
		backBtn.dispose();
		
		for (TurretBase turret : turrets) {
			turret.dispose();
		}
		
		missiles.clear();
		turrets.clear();
		keepMissilesAlive.clear();
	}

}
