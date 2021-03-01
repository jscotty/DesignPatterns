package missile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import math.Vector2;

public abstract class Missile  {
	// pivot for object drawing
	private static final float pivotX = 0.5f;
	private static final float pivotY = 0.5f;
	
	// entity data
	private Vector2 pos;
	private float speed;
	private int score;
	private BufferedImage image;

	private int width;
	private int height;
	
	// render size for scaling
	private int renderWidth;
	private int renderHeight;
	
	// when reached the ground
	private boolean stopped;

	// used to recreate
	private MissileType creationType;
	
	public float getSpeed() { return speed; }

	public Vector2 getPos() { return pos; }

	public int getScore() { return score; }

	public boolean isStopped() { return stopped; }
	
	public MissileType getCreationType() { return creationType; }
	
	// Constructor
	public Missile(float x, float y, float speed, int score, BufferedImage image) {
		this.speed = speed;
		this.image = image;

		width = image.getWidth();
		height = image.getHeight();
		
		renderWidth = width;
		renderHeight = height;
		
		pos = new Vector2(x, y);
	}
	
	// set creation type which is used to recreate this missile
	public void setCreationType(MissileType type) {
		creationType = type;
	}
	
	// scale missile
	protected void setScale(float scale) {
		renderWidth = (int) (width * scale);
		renderHeight = (int) (height * scale);
	}
	
	// update missile to move it to the ground
	public void update(double deltaTime) {
		pos.yPos += (float)(speed * deltaTime);
		if(pos.yPos >= 500) {
			pos.yPos = 500;
			
			stopped = true;
		}
	}
	
	public void render(Graphics2D g) {
		// pivoting
		int x = (int) (pos.xPos - (renderWidth * pivotX));
		int y = (int) (pos.yPos - (renderHeight * pivotY));
		g.drawImage(image, x, y, renderWidth, renderHeight, null);
	}
}
