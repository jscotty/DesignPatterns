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
	
	// passing both base image and barrel image, so both are flexible
	// changable for our subclasses
	public TurretBase(float x, float y, BufferedImage base, BufferedImage barrel) {
		// place barrel in middle of turret
		turretBarrel = new TurretBarrel(x, y, barrel);
		
		// add position for our base
		addComponent(new Transform(x, y));
		
		// render base sprite
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
		// render barrel first so it's behind the base
		turretBarrel.render(g);
		
		super.render(g);
	}
	
    @Override
    public void dispose() {
        turretBarrel.dispose();
    }
}