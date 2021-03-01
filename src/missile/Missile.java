package missile;

import java.awt.image.BufferedImage;

import ecs.Entity;
import math.Vector2;
import components.Image;
import components.ImageCollider;
import components.RotateToMouse;
import components.Transform;

public abstract class Missile extends Entity {
	
	// entity data
	private Vector2 pos;
	private float speed;
	private int score;
	
	private MissileState state = MissileState.Falling;

	private MissileType creationType; // used to recreate
	
	public float getSpeed() { return speed; }

	public Vector2 getPos() { return getComponent(Transform.class).position; }

	public int getScore() { return score; }

	public boolean isStopped() { return state == MissileState.Ground; }
	
	public MissileType getCreationType() { return creationType; }
	
	public Missile(float x, float y, float speed, int score, BufferedImage sprite) {
		this.score = score;
		this.speed = speed;
		
		addComponent(new Transform(x, y));
		addComponent(new Image(sprite));
		addComponent(new ImageCollider());
	}
	
	// set creation type which is used to recreate this missile
	public void setCreationType(MissileType type) {
		creationType = type;
	}
	
	// scale missile
	protected void setScale(float scale) {
		getComponent(Image.class).setScale(scale);
	}
	
	public boolean collisionDetection(Image target) {
		return getComponent(ImageCollider.class).collides(target);
	}
	
	public void destroy() {
		// TODO: destroy the missile somehow ;)
		state = MissileState.Destroyed;
	}
	
	// update missile to move it to the ground
	public void update(double deltaTime) {
		Transform transform = getComponent(Transform.class);
		Vector2 pos = transform.position;
		pos.y += (float)(speed * deltaTime);
		if(pos.y >= 500) {
			pos.y = 500;
			
			state = MissileState.Ground;
		}
		
		transform.position = pos;
	}
}
