package turret;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import components.Image;
import components.Transform;
import ecs.Entity;

public abstract class TurretBase extends Entity {
	
	protected TurretBarrel turretBarrel;
	
	public TurretBase(float x, float y, BufferedImage base, BufferedImage barrel) {
		turretBarrel = new TurretBarrel(x, y, barrel);
		
		addComponent(new Transform(x, y));
		addComponent(new Image(base));
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
}
