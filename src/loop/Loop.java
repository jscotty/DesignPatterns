package loop;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Loop extends JPanel implements Runnable{

	private Thread thread;
	private boolean running;
	
	private int fps; //frames per second
	private int tps; //ticks per second
	
	private int width;
	private int height;
	
	public Graphics2D graphics2D;
	private BufferedImage img;
	
	public static double currentFPS = 120D;
	
	public Loop(int width, int height){
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}

	// initializing
	public void init() {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		graphics2D = (Graphics2D) img.getGraphics();
		
		running = true;
	}
	
	// starting thread
	@Override
	public void addNotify() {
		super.addNotify();
		
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}

	// runnable code
	@Override
	public void run() {
		init();
		
		// caching time variables
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / currentFPS;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double deltaTime = 0;
		
		while(running){
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while(deltaTime >= 1){
				ticks++;
				
				// tick + deltatime
				tick(deltaTime);
				
				deltaTime--;
				shouldRender = true;
			}
			
			if(shouldRender){
				frames++;
				//render
				render();
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				tps = ticks;
				fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
		
	}

	// used as virtual method
	public void tick(double deltaTime) {
	}

	// rendering graphics
	public void render() {
		graphics2D.clearRect(0, 0, width, height);
	}
	
	// clear frame
	public void clear(){
		Graphics g2 = getGraphics();
		
		if(img!=null){
			g2.drawImage(img,0,0,null);
		}
		g2.dispose();
	}
	
	// getter properties
	public int getFps() {
		return fps;
	}
	public int getTps() {
		return tps;
	}
}