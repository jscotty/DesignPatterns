package turret;

import java.awt.image.BufferedImage;

import components.Image;
import components.RotateToMouse;
import components.Transform;
import ecs.Entity;

public class TurretBarrel  extends Entity {
	
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
	
	@Override
	public void update(double deltaTime) {
		// update components
		super.update(deltaTime);
	}
	
}
