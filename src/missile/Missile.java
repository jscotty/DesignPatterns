package missile;

import java.awt.image.BufferedImage;

import ecs.Entity;
import math.Vector2;
import components.Gravity;
import components.Image;
import components.RotateToMouse;
import components.Transform;

public abstract class Missile extends Entity {
	
	private int score;
	
	private MissileState state = MissileState.Falling;

	private MissileType creationType; // used to recreate

	public Vector2 getPos() { return getComponent(Transform.class).position; }

	public int getScore() { return score; }

	public boolean isStopped() { return state == MissileState.Ground; }
	
	public MissileType getCreationType() { return creationType; }
	
	// Constructor
	public Missile(float x, float y, float speed, int score, BufferedImage sprite) {
		// adding components
		// transform for positioning
		addComponent(new Transform(x, y));
		// image for rendering sprite
		addComponent(new Image(sprite));
		// gravity for falling down
		addComponent(new Gravity(speed));
	}
	
	// set creation type which is used to recreate this missile
	public void setCreationType(MissileType type) {
		creationType = type;
	}
	
	// scale missile
	protected void setScale(float scale) {
		getComponent(Image.class).setScale(scale);
	}
	
	public void destroy() {
		// TODO: destroy the missile somehow ;)
		state = MissileState.Destroyed;
	}
	
	public void update(double deltaTime) {
		// update all components
		super.update(deltaTime);
		
		// check if reached the ground
		Transform transform = getComponent(Transform.class);
		Vector2 pos = transform.position;
		if(pos.y >= 500) {
			pos.y = 500;
			
			state = MissileState.Ground;
		}
		
		transform.position = pos;
	}
}
