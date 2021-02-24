package bullet;


import components.Image;
import components.ImageScaler;
import components.MoveToPosition;
import components.Transform;
import ecs.Entity;
import math.Vector2;
import observers.IDieObserver;
import observers.IMoveToPositionObserver;
import observers.IObserver;
import observers.IScaleObserver;
import sprite.Sprites;

public class Bullet extends Entity implements IMoveToPositionObserver {

	private static final float speed = 2f;
	
	private static final float maxScale = 7.5f;
	private static final float scaleStep = .5f;
	private static final float delay = 10f;
	private static final boolean invertScale = true;
	
	private MoveToPosition mtp;
	private IScaleObserver scaleObserver;
	private IDieObserver dieObserver;

	public Bullet(float x, float y, Vector2 target) {
		addComponent(new Transform(x, y));
		addComponent(new Image(Sprites.instance.getBullet()));
		
		mtp = new MoveToPosition(target, speed);
		mtp.register((IObserver) this);
		addComponent(mtp);
	}
	
	public void addScaleObserver(IScaleObserver observer) {
		this.scaleObserver = observer;
	}
	
	public void addDieObserver(IDieObserver observer) {
		this.dieObserver = observer;
	}

	@Override
	public void onPosition() {
		removeComponent(mtp, false);
		
		ImageScaler scaler = new ImageScaler(maxScale, scaleStep, delay, invertScale);
		scaler.register(scaleObserver);
		scaler.register(dieObserver);
		addComponent(scaler);
	}
}
