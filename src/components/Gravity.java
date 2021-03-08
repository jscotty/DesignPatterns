package components;

import java.awt.Graphics2D;

import ecs.Component;
import math.Vector2;

public class Gravity extends Component {

	private Transform transform;
	private float speed;
	
	// defining gravity speed in constructor
	public Gravity(float speed) {
		this.speed = speed;
	}
	
	@Override
	public void init() {
		transform = entity.getComponent(Transform.class);
	}

	@Override
	public void update(double deltaTime) {
		// modifying transform position to fall down
		Vector2 pos = transform.position;
		pos.y += (float)(speed * deltaTime);
		
		transform.position = pos;
	}

	@Override
	public void render(Graphics2D g) {
	}

}
