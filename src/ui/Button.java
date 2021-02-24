package ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.event.MouseInputListener;

import components.Image;
import components.ImageCollider;
import components.Transform;
import ecs.Entity;
import main.GameWindow;

public class Button extends Entity implements MouseInputListener, MouseMotionListener {

	private Transform transform;
	private Image image;
	private ImageCollider collider;

	private BufferedImage normal;
	private BufferedImage hover;
	private BufferedImage pressed;
	
	private ButtonState state;
	private boolean mousePressed;
	
	private Action action;
	
	public Button(float x, float y, BufferedImage normal, BufferedImage hover, BufferedImage pressed) {
		this.normal = normal;
		this.hover = hover;
		this.pressed = pressed;
		
		transform = new Transform(x, y);
		addComponent(transform);
		
		image = new Image(normal);
		addComponent(image);
		
		collider = new ImageCollider();
		addComponent(collider);
		
		GameWindow.instance.addMouseListener(this);
		GameWindow.instance.addMouseMotionListener(this);
	}
	
	public void setOnClickAction(Action action) {
		this.action = action;
	}
	
	public void setScale(float scale) {
		image.setScale(scale);
	}
	
	private BufferedImage getStateImage() {
		if(state == ButtonState.Pressed) {
			return pressed;
		}
		
		return state == ButtonState.Hover ? hover : normal;
	}
	
	private void checkState(MouseEvent e) {
		ButtonState cachedState = state;
		
		if(collider.collides(e.getX() - 8, e.getY() - 32)) {
			// hit!
			if(mousePressed) {
				state = ButtonState.Pressed;
			} else {
				state = ButtonState.Hover;
			}
		} else {
			state = ButtonState.Normal;
		}
		
		if(cachedState != state) {
			image.setSprite(getStateImage());
		}
	}
	
	private void onClick() {
		if(state == ButtonState.Pressed) {
			action.actionPerformed(null);
		}
	}
	
	public void dispose() {
		GameWindow.instance.removeMouseMotionListener(this);
		GameWindow.instance.removeMouseListener(this);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		checkState(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		checkState(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		onClick();
		mousePressed = false;
		checkState(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
