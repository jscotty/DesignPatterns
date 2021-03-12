package bullet;

import components.Image;
import components.MoveToPosition;
import components.Transform;
import ecs.Entity;
import math.Vector2;
import sprite.Sprites;

public class Bullet extends Entity {
	
	private static final float speed = 2f;

	public Bullet(float x, float y, Vector2 target) {
		addComponent(new Transform(x, y));
		addComponent(new Image(Sprites.instance.getBullet()));
		addComponent(new MoveToPosition(target, speed));
	}
}
