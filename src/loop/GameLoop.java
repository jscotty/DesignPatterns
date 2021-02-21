package loop;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.GameWindow;
import missile.Missile;
import missile.MissileFactory;
import missile.MissileNormal;
import missile.MissileType;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;

public class GameLoop  extends Loop {

	private static final int normalMissilesCount = 10;
	private static final int fastMissilesCount = 5;
	private static final int slowMissilesCount = 15;
	private static final int randomMissilesCount = 0;
	
	private static final boolean loopCreations = true;
	
	private BufferedImage background;
	
	private GameWindow window;
	private MissileFactory missileFactory;
	private Random random;

	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Missile> keepMissilesAlive = new ArrayList<Missile>();
	
	private ArrayList<TurretBase> turrets = new ArrayList<TurretBase>();

	private float randomX() {
		return 10 + random.nextFloat() * (750);
	}
	
	private float randomY() {
		return random.nextFloat() * (-500);
	}
	
	
	public GameLoop(int width, int height, GameWindow window) {
		super(width, height);
		
		this.window = window;
		missileFactory = new MissileFactory(normalMissilesCount, fastMissilesCount, slowMissilesCount);
		random = new Random();
	}
	
	@Override
	public void init() {
		super.init();
		
		Sprites sprites = new Sprites();
		sprites.Init(); // creates singleton instance and sprites
		
		background = Sprites.instance.getBG();
		
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
	}
	
	@Override
	public void tick(double deltaTime) {
		ArrayList<Missile> removeMissiles = new ArrayList<Missile>();
		
		for (TurretBase turret : turrets) {
			turret.update(deltaTime);
		}
		
		for (Missile missile : missiles) {
			missile.update(deltaTime);

			if(missile.isStopped()) {
				removeMissiles.add(missile);
			}
		}
		
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
	public void render() {
		graphics2D.drawImage(background, 0, 0, null);
		
		for (TurretBase turret : turrets) {
			turret.render(graphics2D);
		}
		
		for (Missile missile : missiles) {
			missile.render(graphics2D);
		}
		
		for (Missile missile : keepMissilesAlive) {
			missile.render(graphics2D);
		}
		clear();
		super.render();
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
