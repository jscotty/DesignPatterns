package components;

import java.awt.Graphics2D;

import ecs.Component;
import math.Vector2;
import observers.IMoveToPositionObserver;

public class MoveToPosition extends Component {

	// cached component
	private Transform transform;
	// target to move to
	private Vector2 target;
	// speed till reached target
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
		// moving towards the direction we calculate in Vector class
		Vector2 direction = transform.position.direction(target);
		// adding position
		transform.position.x += direction.x * speed * deltaTime;
		transform.position.y += direction.y * speed * deltaTime;
		
		if(Vector2.getDistanceD(transform.position, target) <= 1.5f) {
			// checking distance to clamp our position
			// otherwise it will keep shaking on it's position.
			// the amount of shake will vary on the speed you choose!
			transform.position = target;
			
			// firing on position to notify all our observers.
			onPosition();
		}
	}

	@Override
	public void render(Graphics2D g) {
	}
	
	private void onPosition() {
		// notifying all our observers!
		for (IMoveToPositionObserver observer : getObservers(IMoveToPositionObserver.class)) {
			observer.onPosition();
		}
	}
}
