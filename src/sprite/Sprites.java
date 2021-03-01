package sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprites {
	
	public static Sprites instance; // singleton

	// missiles
	private static BufferedImage missile_normal;
	private static BufferedImage missile_speed;
	private static BufferedImage missile_slow;

	// properties
	public BufferedImage GetMissileNormal() { return missile_normal; }
	public BufferedImage GetMissileSpeed() { return missile_speed; }
	public BufferedImage GetMissileSlow() { return missile_slow; }
	
	public void Init() {
		instance = this;

		// loading images
		missile_normal = loadImageFrom("missile_normal");
		missile_speed = loadImageFrom("missile_speed");
		missile_slow = loadImageFrom("missile_slow");
	}
	
	private BufferedImage loadImageFrom(String path){
		// creating url from this class path position
		URL url = this.getClass().getResource(path + ".png");
		BufferedImage img = null;

		try{
			img = ImageIO.read(url);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		// return image if found
		return img;
	}
}
