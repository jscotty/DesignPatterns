package components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ecs.Component;
import math.Vector2;

public class Image extends Component {
	
	private BufferedImage sprite;
	private Transform transform;

	private int width;
	private int height;
	
	private int renderWidth;
	private int renderHeight;
	
	private Vector2 pivot = new Vector2(0.5f, 0.5f);
	
	public Image(BufferedImage sprite) {
		this.sprite = sprite;

		width = this.sprite.getWidth();
		height = this.sprite.getHeight();
		
		renderWidth = width;
		renderHeight = height;
	}
	
	public void setPivot(float x, float y) {
		pivot.x = x;
		pivot.y = y;
	}

	@Override
	public void init() {
		transform = entity.getComponent(Transform.class);
	}
	
	public void setScale(float scale) {
		renderWidth = (int) (width * scale);
		renderHeight = (int) (height * scale);
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public void render(Graphics2D g) {
		if(sprite == null) {
			return;
		}
		AffineTransform originalTrans = g.getTransform();

		g.rotate(transform.rotation, transform.position.x , transform.position.y);
		
		int x = (int) (transform.position.x - (renderWidth * pivot.x));
		int y = (int) (transform.position.y - (renderHeight * pivot.y));
		g.drawImage(sprite, x, y, renderWidth, renderHeight, null);

		g.setTransform(originalTrans);
	}

}
