package components;

import java.awt.Graphics2D;

import ecs.Component;
import observers.IDieObserver;
import observers.IObserver;
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
		this.maxScale = maxScale;
		this.scaleStep = scaleStep;
		this.delay = delay;
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
		
		if(time < delay) {
			return;
		}
		time = 0;
		
		currentScale += scaleStep;
		
		if(currentScale > maxScale) {
			currentScale = maxScale;
			if(invertScale) {
				scaleStep *= -1;
			} else {
				onDie();
			}
		} else if(currentScale < 0) {
			currentScale = 0;
			
			onDie();
		}
		
		image.setScale(currentScale);
		onScaling();
		
	}

	@Override
	public void render(Graphics2D g) {
	}
	
	private void onDie() {
		System.out.println("died" + observers.size());
		died = true;
		for (IObserver observer : observers) {
			System.out.println(observer);
			IDieObserver o = (IDieObserver) observer;
			if(o != null) {
				o.onDie(entity.getClass());
			}
		}
	}
	
	private void onScaling() {
		for (IObserver observer : observers) {
			IScaleObserver o = (IScaleObserver) observer;
			if(o != null) {
				o.onScale(image);
			}
		}
	}
}
