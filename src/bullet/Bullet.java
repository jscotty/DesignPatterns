package bullet;


import components.Image;
import components.ImageScaler;
import components.MoveToPosition;
import components.Transform;
import ecs.Entity;
import math.Vector2;
import observers.IDieObserver;
import observers.IMoveToPositionObserver;
import observers.IScaleObserver;
import sprite.Sprites;

public class Bullet extends Entity implements IMoveToPositionObserver {

	private static final float speed = 2f;
	
	// adding scaling data
	private static final float maxScale = 7.5f;
	private static final float scaleStep = .5f;
	private static final float delay = 10f;
	private static final boolean invertScale = true;
	
	private IScaleObserver scaleObserver;
	private IDieObserver dieObserver;

	public Bullet(float x, float y, Vector2 target) {
		addComponent(new Transform(x, y));
		addComponent(new Image(Sprites.instance.getBullet()));
		
		MoveToPosition mtp = new MoveToPosition(target, speed);
		// registering this object because we are listening to when we moved
		// our target position!
		mtp.register(this);
		addComponent(mtp);
	}
	
	// add scale observers to our scaler
	// can not do it directly because we scale
	// after we hit our target position
	public void addScaleObserver(IScaleObserver observer) {
		this.scaleObserver = observer;
	}
	
	// add die observer which will be added once we start scaling
	public void addDieObserver(IDieObserver observer) {
		this.dieObserver = observer;
	}

	@Override
	public void onPosition() {
		// removing component because it's not needed anymore.
		removeComponent(MoveToPosition.class, false);
		
		ImageScaler scaler = new ImageScaler(maxScale, scaleStep, delay, invertScale);
		// register cached observers
		scaler.register(scaleObserver);
		scaler.register(dieObserver);
		addComponent(scaler);
	}
}
