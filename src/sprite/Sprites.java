package sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprites {
	
	public static Sprites instance; // singleton

	// background
	private static BufferedImage bg;
	
	public BufferedImage getBG() { return bg; }
	
	// missiles
	private static BufferedImage missile_normal;
	private static BufferedImage missile_speed;
	private static BufferedImage missile_slow;

	public BufferedImage getMissileNormal() { return missile_normal; }
	public BufferedImage getMissileSpeed() { return missile_speed; }
	public BufferedImage getMissileSlow() { return missile_slow; }
	
	// turret
	private static BufferedImage turret_circle;
	private static BufferedImage turret_square;
	private static BufferedImage turret_barrel_white;
	private static BufferedImage turret_barrel_red;

	public BufferedImage getTurretCircle() { return turret_circle; }
	public BufferedImage getTurretSquare() { return turret_square; }
	public BufferedImage getTurretBarrelWhite() { return turret_barrel_white; }
	public BufferedImage getTurretBarrelRed() { return turret_barrel_red; }
	
	// buttons
	private static BufferedImage btn_start_normal;
	private static BufferedImage btn_start_hover;
	private static BufferedImage btn_start_pressed;
	
	private static BufferedImage btn_back_normal;
	private static BufferedImage btn_back_hover;
	private static BufferedImage btn_back_pressed;

	public BufferedImage getBtnStartNormal() { return btn_start_normal; }
	public BufferedImage getBtnStartHover() { return btn_start_hover; }
	public BufferedImage getBtnStartPressed() { return btn_start_pressed; }

	public BufferedImage getBtnBackNormal() { return btn_back_normal; }
	public BufferedImage getBtnBackHover() { return btn_back_hover; }
	public BufferedImage getBtnBackPressed() { return btn_back_pressed; }
	
	// bullet
	private static BufferedImage bullet;
	public BufferedImage getBullet() { return bullet; }
	
	public void Init() {
		instance = this;
		bg = loadImageFrom("bg");

		missile_normal = loadImageFrom("missile_normal");
		missile_speed = loadImageFrom("missile_speed");
		missile_slow = loadImageFrom("missile_slow");

		turret_circle = loadImageFrom("turret_base_circ");
		turret_square = loadImageFrom("turret_base_sq");
		turret_barrel_white = loadImageFrom("turret_barrel_white");
		turret_barrel_red = loadImageFrom("turret_barrel_red");

		btn_start_normal = loadImageFrom("btn_start");
		btn_start_hover = loadImageFrom("btn_start_hover");
		btn_start_pressed = loadImageFrom("btn_start_pressed");

		btn_back_normal = loadImageFrom("btn_back");
		btn_back_hover = loadImageFrom("btn_back_hover");
		btn_back_pressed = loadImageFrom("btn_back_pressed");

		bullet = loadImageFrom("bullet");
	}
	
	private BufferedImage loadImageFrom(String path){
		URL url = this.getClass().getResource(path + ".png");
		BufferedImage img = null;

		try{
			img = ImageIO.read(url);
		} catch(IOException e){
			e.printStackTrace();
		}
		
		return img;
	}
}
