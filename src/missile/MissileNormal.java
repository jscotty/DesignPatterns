package missile;

import sprite.Sprites;

public class MissileNormal extends Missile {

	private static final float speed = 1f;
	
	public MissileNormal(float x, float y) {
		super(x, y, speed, 2, Sprites.instance.GetMissileNormal());
	}
}
