package missile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import math.Vector2;

public abstract class Missile  {
	private static final float pivotX = 0.5f;
	private static final float pivotY = 0.5f;
	
	private Vector2 pos;
	private float speed;
	private int score;
	private BufferedImage image;

	private int width;
	private int height;
	
	private int renderWidth;
	private int renderHeight;
	
	private boolean stopped;

	private MissileType creationType; // used to recreate
	
	public float getSpeed() { return speed; }

	public Vector2 getPos() { return pos; }

	public int getScore() { return score; }

	public boolean isStopped() { return stopped; }
	
	public MissileType getCreationType() { return creationType; }
	
	public Missile(float x, float y, float speed, int score, BufferedImage image) {
		this.speed = speed;
		this.image = image;

		width = image.getWidth();
		height = image.getHeight();
		
		renderWidth = width;
		renderHeight = height;
		
		pos = new Vector2(x, y);
	}
	
	public void setCreationType(MissileType type) {
		creationType = type;
	}
	
	protected void setScale(float scale) {
		renderWidth = (int) (width * scale);
		renderHeight = (int) (height * scale);
	}
	
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
