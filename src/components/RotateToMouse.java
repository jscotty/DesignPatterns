package components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import ecs.Component;
import main.GameWindow;

public class RotateToMouse extends Component implements MouseMotionListener {

	// catching transform to change rotation
	private Transform transform;
	
	@Override
	public void init() {
		transform = entity.getComponent(Transform.class);
		transform.rotation = (float) Math.toRadians(180);
		
		// listen to mouse events
		GameWindow.instance.addMouseMotionListener(this);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// calculate position difference between me and mouse
		float dx = transform.position.x -  e.getX();
		float dy = transform.position.y - e.getY();
		
		// get radiant rotation by atan our y and x position differences
		transform.rotation = (float) Math.atan2(dy, dx);
		transform.rotation += Math.toRadians(90);
		
	}

	// ignoring these
	@Override
	public void update(double deltaTime) {;
	}

	@Override
	public void render(Graphics2D g) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}
}
