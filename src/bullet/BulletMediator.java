package bullet;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import missile.MissileMediator;
import turret.TurretMediator;

public class BulletMediator {
	
	// all bullets stored
	private List<Bullet> bullets = new ArrayList<>();
	
	private MissileMediator missileMediator;
	
	public BulletMediator(MissileMediator missileMediator) {
		// caching missile mediator for later use ;)
		this.missileMediator = missileMediator;
	}
	
	// adding, updating, rendering and disposing
	// bullets. Must be very much self explaining :)
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void update(double deltaTime) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.update(deltaTime);
		}
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.render(g);
		}
	}
	
	public void dispose() {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.dispose();
		}
		
		bullets.clear();
	}
}
