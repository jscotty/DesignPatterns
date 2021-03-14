package components;

import java.awt.Graphics2D;

import ecs.Component;
import math.Vector2;

public class Transform extends Component {
	
	// x and y position
	public Vector2 position;
	
	// transform rotation
	public float rotation;

	// adding transform on position x=0 and y=0
	public Transform() {
		 position = new Vector2();
	}
	
	// defining position through constructor
	public Transform(float x, float y) {
		 position = new Vector2(x, y);
	}

	// will not use any of the super methods, so they'll stay empty
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
