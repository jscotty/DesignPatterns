package components;

import java.awt.Graphics2D;
import java.awt.Point;

import ecs.Component;

public class ImageCollider extends Component {

	private Image image;
	
	@Override
	public void init() {
		image = entity.getComponent(Image.class);
	}
	
	// check collision based on a point
	public boolean Collides(Point point) {
		return point.x <= image.getRect().getMaxX() && point.x >= image.getRect().getMinX() && 
				point.y <= image.getRect().getMaxY() && point.y >= image.getRect().getMinY();
	}
	
	// check if x/y are hitting out button
	public boolean Collides(float x, float y) {
		return x < image.getRect().getMaxX() && x > image.getRect().getMinX() && 
				y < image.getRect().getMaxY() && y > image.getRect().getMinY();
	}
	
	public boolean Collides(Image img) {
		return image.getRect().intersects(img.getRect());
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public void render(Graphics2D g) {
	}
	
}
