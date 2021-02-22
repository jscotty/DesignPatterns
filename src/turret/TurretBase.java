package turret;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import bullet.Bullet;
import components.Image;
import components.Transform;
import ecs.Entity;
import math.Vector2;

public abstract class TurretBase extends Entity {
	
	protected TurretBarrel turretBarrel;
	
	public TurretBase(float x, float y, BufferedImage base, BufferedImage barrel) {
		turretBarrel = new TurretBarrel(x, y, barrel);
		
		addComponent(new Transform(x, y));
		addComponent(new Image(base));
	}
	
	public Bullet shoot(Vector2 target) {
		return turretBarrel.shoot(target);
	}
	
	@Override
	public void update(double deltaTime) {
		super.update(deltaTime);
		turretBarrel.update(deltaTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		turretBarrel.render(g);
		
		super.render(g);
	}
	
	@Override
	public void dispose() {
		turretBarrel.dispose();
	}
}
