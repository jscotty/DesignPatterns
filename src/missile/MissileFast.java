package missile;

import sprite.Sprites;

public class MissileFast extends Missile {
	private static final float speed = 1.5f;

	public MissileFast(float x, float y) {
		super(x, y, speed, 3, Sprites.instance.getMissileSpeed());
		
		setScale(.75f);
	}

}
