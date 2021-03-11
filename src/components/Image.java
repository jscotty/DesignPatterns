package components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import ecs.Component;
import math.Vector2;

public class Image extends Component {
	
	// sprite to render
	private BufferedImage sprite;
	// caching transform component
	private Transform transform;

	// render data
	private int width;
	private int height;
	private float scale = 1;
	
	private int renderWidth;
	private int renderHeight;
	
	// pivot to change render central position
	// default pivot is 0, 0 (top left corner)
	private Vector2 pivot = new Vector2(0.5f, 0.5f);
	
	public Image(BufferedImage sprite) {
        setSprite(sprite);
    }
    
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;

        // width and height from sprite
        width = this.sprite.getWidth();
        height = this.sprite.getHeight();
        
        renderWidth = (int) (width * scale);
        renderHeight = (int) (height * scale);
    }
    
    public int getPivotedXPosition() {
        return (int) (transform.position.x - (renderWidth * pivot.x));
    }
    
    public int getPivotedYPosition() {
        return (int) (transform.position.y - (renderHeight * pivot.y));
    }
    
    public Rectangle getRect() {
        return new Rectangle(getPivotedXPosition(), getPivotedYPosition(), renderWidth, renderHeight);
    }
	
	public void setPivot(float x, float y) {
		pivot.x = x;
		pivot.y = y;
	}

	@Override
	public void init() {
		// caching transform by getting component from entity
		transform = entity.getComponent(Transform.class);
	}
	
	public void setScale(float scale) {
		this.scale = scale;
		
		// changing size of image by scaling original size
		// and storing in local variable
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
		// cache original render transform
		AffineTransform originalTrans = g.getTransform();

		g.rotate(transform.rotation, transform.position.x , transform.position.y);
		
        g.drawImage(sprite, getPivotedXPosition(), getPivotedYPosition(), renderWidth, renderHeight, null);

		// set transform back so not all images are rotated.
		g.setTransform(originalTrans);
	}

}
