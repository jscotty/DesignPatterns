package missile;

import sprite.Sprites;

public class MissileSlow extends Missile {
	
	private static final float speed = .8f;
	
	public MissileSlow(float x, float y) {
		super(x, y, speed, 2, Sprites.instance.getMissileSlow());
		
		setScale(2);
	}
}
