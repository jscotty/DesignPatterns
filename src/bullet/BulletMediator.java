package bullet;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import components.Image;
import missile.MissileMediator;
import observers.IDieObserver;
import observers.IScaleObserver;

// Listen to scaling for collision detection.
// Listen to died bullets to remove them from list.
public class BulletMediator implements IScaleObserver, IDieObserver {
	
	// all bullets stored
	private List<Bullet> bullets = new ArrayList<>();
	
	private MissileMediator missileMediator;
	
	public BulletMediator(MissileMediator missileMediator) {
		this.missileMediator = missileMediator;
	}
	
	// adding, updating, rendering and disposing
	// bullets. Must be very much self explaining :)
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
		// for registering
		bullet.addScaleObserver(this);
		bullet.addDieObserver(this);
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

	@Override
	public void onScale(Image image) {
		// check if we are colliding any of the missiles.
		missileMediator.checkCollision(image);
	}

	@Override
	public <T> void onDie(Class<T> c) {
		Bullet removeBullet = null;
		removeBullet =  (Bullet) c.cast(removeBullet);
		
		// check if for some reason we don't get a bullet returned.
		if(removeBullet == null) {
			return;
		}
		
		bullets.remove(removeBullet);
	}
}
