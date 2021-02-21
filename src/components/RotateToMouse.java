package components;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ecs.Component;
import main.GameWindow;

public class RotateToMouse extends Component implements MouseMotionListener {

	private Transform transform;
	
	@Override
	public void init() {
		transform = entity.getComponent(Transform.class);
		
		GameWindow.instance.addMouseMotionListener(this);
	}

	@Override
	public void update(double deltaTime) {;
	}

	@Override
	public void render(Graphics2D g) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float dx = transform.position.x -  e.getX();
		float dy = transform.position.y - e.getY();
		transform.rotation = (float) Math.atan2(dy, dx);
		transform.rotation += Math.toRadians(90);
	}

	@Override
	public void dispose() {
		super.dispose();
		
		GameWindow.instance.removeMouseMotionListener(this);
	}
}
