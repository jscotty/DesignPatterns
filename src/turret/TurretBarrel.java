package turret;

import java.awt.image.BufferedImage;

import bullet.Bullet;
import components.Image;
import components.RotateToMouse;
import components.Transform;
import ecs.Entity;
import math.Vector2;

public class TurretBarrel extends Entity {
	
	public TurretBarrel(float x, float y, BufferedImage base) {
		addComponent(new Transform(x, y));
		addComponent(new Image(base));
		addComponent(new RotateToMouse());
		
		// base pivoting
		setPivot(0.5f, 0f);
	}
	
	public void setPivot(float x, float y) {
		getComponent(Image.class).setPivot(x, y);
	}
	
	public Bullet shoot(Vector2 target) {
		Vector2 position = getComponent(Transform.class).position;
		Vector2 direction = position.direction(target);
		float x = position.x + direction.x * 5;
		float y = position.y + direction.y * 5;
		
		return new Bullet(x, y, target);
	}
	
	@Override
	public void update(double deltaTime) {
		// update components
		super.update(deltaTime);
	}
	
}
