package components;

import java.awt.Graphics2D;

import ecs.Component;
import math.Vector2;

public class Transform extends Component {
	
	public Vector2 position;
	
	public float rotation;
	
	public Transform() {
		 position = new Vector2();
	}
	
	public Transform(float x, float y) {
		 position = new Vector2(x, y);
	}

	@Override
	public void init() {
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public void render(Graphics2D g) {
	}
}
