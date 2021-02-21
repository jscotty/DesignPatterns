package components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ecs.Component;

public class Image extends Component {
	private static final float pivotX = 0.5f;
	private static final float pivotY = 0.5f;
	
	private BufferedImage sprite;
	private Transform transform;

	private int width;
	private int height;
	
	private int renderWidth;
	private int renderHeight;
	
	public Image(BufferedImage sprite) {
		this.sprite = sprite;

		width = this.sprite.getWidth();
		height = this.sprite.getHeight();
		
		renderWidth = width;
		renderHeight = height;
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

		int x = (int) (transform.position.x - (renderWidth * pivotX));
		int y = (int) (transform.position.y - (renderHeight * pivotY));
		g.drawImage(sprite, x, y, renderWidth, renderHeight, null);
	}

}
