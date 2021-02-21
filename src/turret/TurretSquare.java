package turret;

import sprite.Sprites;

public class TurretSquare extends TurretBase {

	public TurretSquare(float x, float y) {
		super(x, y, Sprites.instance.getTurretSquare(), Sprites.instance.getTurretBarrelRed());
	}

}
