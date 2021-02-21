package turret;

import sprite.Sprites;

public class TurretCircle extends TurretBase {

	public TurretCircle(float x, float y) {
		super(x, y, Sprites.instance.getTurretCircle(), Sprites.instance.getTurretBarrelWhite());
		
		turretBarrel.setPivot(0.5f,  -.5f);
	}
}
