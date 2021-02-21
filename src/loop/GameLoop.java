package loop;

import java.util.ArrayList;
import java.util.Random;

import main.GameWindow;
import missile.Missile;
import missile.MissileFactory;
import missile.MissileNormal;
import missile.MissileType;
import sprite.Sprites;

public class GameLoop  extends Loop {

	private static final int normalMissilesCount = 10;
	private static final int fastMissilesCount = 5;
	private static final int slowMissilesCount = 15;
	private static final int randomMissilesCount = 0;
	
	private static final boolean loopCreations = true;
	
	private GameWindow window;
	private MissileFactory factory;
	private Random random;

	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Missile> keepMissilesAlive = new ArrayList<Missile>();

	private float randomX() {
		return 10 + random.nextFloat() * (750);
	}
	
	private float randomY() {
		return random.nextFloat() * (-500);
	}
	
	
	public GameLoop(int width, int height, GameWindow window) {
		super(width, height);
		
		this.window = window;
		factory = new MissileFactory(normalMissilesCount, fastMissilesCount, slowMissilesCount);
		random = new Random();
	}
	
	@Override
	public void init() {
		super.init();
		
		Sprites sprites = new Sprites();
		sprites.Init(); // creates singleton instance and sprites
		
		int size = normalMissilesCount + fastMissilesCount + slowMissilesCount + randomMissilesCount;
		for (int i = 0; i < size; i++) {
			float x = randomX();
			float y = randomY();

			missiles.add(factory.getMissile(x, y, i));
		}
	}
	
	@Override
	public void tick(double deltaTime) {
		ArrayList<Missile> removeMissiles = new ArrayList<Missile>();
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
				missiles.add(factory.getMissile(randomX(), randomY(), removeMissiles.get(i).getCreationType()));
			}
		}
		
		while (keepMissilesAlive.size() > 50) {
			keepMissilesAlive.remove(0); // removing missile when it reached a limit for performance
		}
	}
	
	@Override
	public void render() {
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