package components;

import java.awt.Graphics2D;

import ecs.Component;
import math.Vector2;

public class MoveToPosition extends Component {

	private Transform transform;
	private Vector2 target;
	private float speed;
	
	public MoveToPosition(float x, float y, float speed) {
		target = new Vector2(x, y);
		this.speed = speed;
	}
	
	public MoveToPosition(Vector2 target, float speed) {
		this.target = target;
		this.speed = speed;
	}
	
	@Override
	public void init() {
		transform = entity.getComponent(Transform.class);
	}

	@Override
	public void update(double deltaTime) {
		Vector2 direction = transform.position.direction(target);
		transform.position.x += direction.x * speed * deltaTime;
		transform.position.y += direction.y * speed * deltaTime;
	}

	@Override
	public void render(Graphics2D g) {
	}
	
}
