package components;

import java.awt.Graphics2D;

import ecs.Component;
import observers.IDieObserver;
import observers.IScaleObserver;

public class ImageScaler extends Component {

	private Image image;
	private float maxScale;
	private float scaleStep;
	private float delay;
	private boolean invertScale;
	
	private float time = 0;
	private float currentScale;
	
	private boolean died = false;
	
	public ImageScaler(float maxScale, float scaleStep, float delay, boolean invertScale) {
		// maximum scale until we invert or call die
		this.maxScale = maxScale;
		// amount of scale we add each time.
		this.scaleStep = scaleStep;
		// delay in seconds to add scale
		// this will create a faltering effect
		this.delay = delay;
		// boolean to scale back to 0 when maximum scale is reached
		this.invertScale = invertScale;
	}
	
	@Override
	public void init() {
		image = entity.getComponent(Image.class);
		currentScale = image.getScale();
	}

	@Override
	public void update(double deltaTime) {
		if(died) {
			return;
		}
		
		time += deltaTime;
		
		// checking time to make faltering effect
		// if delay = 0, we have continues scaling.
		if(time < delay) {
			return;
		}
		time = 0;
		
		currentScale += scaleStep;
		
		if(currentScale > maxScale) {
			currentScale = maxScale;
			if(invertScale) {
				// inverting our scaling.
				scaleStep *= -1;
			} else {
				onDie();
			}
		} else if(currentScale < 0) {
			currentScale = 0;

			// notifying all die observers that we are scaled to an invisible state.
			onDie();
		}
		
		image.setScale(currentScale);
		// notifying all scale observers each time we scaled
		onScaling();
		
	}

	@Override
	public void render(Graphics2D g) {
	}
	
	private void onDie() {
		// notifying all die observers
		died = true;
		for (IDieObserver observer : getObservers(IDieObserver.class)) {
			observer.onDie(entity.getClass());
		}
	}
	
	private void onScaling() {
		// notifying all scale observers
		for (IScaleObserver observer : getObservers(IScaleObserver.class)) {
			observer.onScale(image);
		}
	}
}
