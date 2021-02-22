package bullet;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import missile.MissileMediator;
import turret.TurretMediator;

public class BulletMediator {
	
	private List<Bullet> bullets = new ArrayList<>();
	
	private MissileMediator missileMediator;
	
	public BulletMediator(MissileMediator missileMediator) {
		this.missileMediator = missileMediator;
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void update(double deltaTime) {
		for (Bullet bullet : bullets) {
			bullet.update(deltaTime);
		}
	}
	
	public void render(Graphics2D g) {
		for (Bullet bullet : bullets) {
			bullet.render(g);
		}
	}
	
	public void dispose() {
		for (Bullet bullet : bullets) {
			bullet.dispose();
		}
		
		bullets.clear();
	}
}
