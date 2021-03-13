package missile;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.Image;

public class MissileMediator {

	private static final int normalMissilesCount = 0;
	private static final int fastMissilesCount = 0;
	private static final int slowMissilesCount = 0;
	private static final int randomMissilesCount = 0;
	
	private static final boolean loopCreations = true;

	private List<Missile> missiles = new ArrayList<>();
	private List<Missile> keepMissilesAlive = new ArrayList<>();
	
	private MissileFactory missileFactory;
	private Random random;
	
	private float timeToAddRandomMissile = 500f; // 5 seconds
	private float time = 0;

	private float randomX() {
		return 10 + random.nextFloat() * (750);
	}
	
	private float randomY() {
		return random.nextFloat() * (-500);
	}
	
	public MissileMediator() {
		missileFactory = new MissileFactory(normalMissilesCount, fastMissilesCount, slowMissilesCount);
		random = new Random();
		
		int size = normalMissilesCount + fastMissilesCount + slowMissilesCount + randomMissilesCount;
		for (int i = 0; i < size; i++) {
			missiles.add(missileFactory.getMissile(randomX(), randomY(), i));
		}
	}
	
	public void update(double deltaTime) {
		time += deltaTime;
		
		if(time > timeToAddRandomMissile) {
			time = 0;
			missiles.add(missileFactory.getMissileRandom(randomX(), randomY()));
		}
		
		List<Missile> removeMissiles = new ArrayList<Missile>();
		
		for (Missile missile : missiles) {
			missile.update(deltaTime);

			if(missile.isStopped()) {
				removeMissiles.add(missile);
			}
		}
		
		loopCreations(removeMissiles);
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
	
	public void render(Graphics2D g) {
		for (Missile missile : missiles) {
			missile.render(g);
		}
		
		for (Missile missile : keepMissilesAlive) {
			missile.render(g);
		}
	}
	
	public void dispose() {
		for (Missile missile : missiles) {
			missile.dispose();
		}

		for (Missile missile : keepMissilesAlive) {
			missile.dispose();
		}
		
		missiles.clear();
		keepMissilesAlive.clear();
	}

	public void checkCollision(Image image) {
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			if(missile.collisionDetection(image)) {
				// missile is hit! DIE!
				System.out.println("MISSILE HIT");
				
				// removing missile because we don't need it anymore. It's destroyed!
				missiles.remove(missile);
				i--;
			}
		}
	}
}
