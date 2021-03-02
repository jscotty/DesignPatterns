package loop;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import missile.Missile;
import missile.MissileFactory;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;

public class GameLoop  extends Loop {

	// missiles count
	// this data is used for our factory.
	// in this configuration our factory will make sure there
	// are always 10 normal, 5 fast, 15 slow and no random missiles on
	// screen.
	private static final int normalMissilesCount = 10;
	private static final int fastMissilesCount = 5;
	private static final int slowMissilesCount = 15;
	private static final int randomMissilesCount = 0;
	
	// enabling to loop recreating missiles after they reach the ground
	private static final boolean loopCreations = true;
	
	private BufferedImage background;

	private MissileFactory missileFactory;
	private Random random;

	// to keep track of our missiles
	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Missile> keepMissilesAlive = new ArrayList<Missile>();

	private float randomX() {
		return 10 + random.nextFloat() * (750);
	}
	
	private float randomY() {
		return random.nextFloat() * (-500);
	}
	
	
	public GameLoop(int width, int height) {
		super(width, height);
		
		// create factory instance
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
		// loop through all our indexes
		for (int i = 0; i < size; i++) {
			float x = randomX();
			float y = randomY();
			
			if(i < normalMissilesCount) {
				missiles.add(factory.GetMissile(x, y, MissileType.Normal));
			} else if (i < normalMissilesCount + fastMissilesCount) {
				missiles.add(factory.GetMissile(x, y, MissileType.Fast));
			} else if (i < normalMissilesCount + fastMissilesCount + slowMissilesCount) {
				missiles.add(factory.GetMissile(x, y, MissileType.Slow));
			} else {
				missiles.add(factory.GetMissileRandom(x, y));
			}
		}
	}
	
	@Override
	public void tick(double deltaTime) {
		// update all our missiles
		ArrayList<Missile> removeMissiles = new ArrayList<Missile>();
		
		for (TurretBase turret : turrets) {
			turret.update(deltaTime);
		}
		
		for (Missile missile : missiles) {
			missile.update(deltaTime);

			// add missile to removed list to not update, but keep rendering it
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
		
		// remove missiles when exceeding a size of 50
		// to prevent performance issues
		while (keepMissilesAlive.size() > 15) {
			// removing missile when it reached a limit for performance
			keepMissilesAlive.remove(0); 
		}
	}
	
	@Override
	public void render() {
		// render all our instances!
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
