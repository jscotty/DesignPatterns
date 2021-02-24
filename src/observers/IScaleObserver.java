package observers;

import components.Image;

public interface IScaleObserver extends IObserver {
	public void onScale(Image image);
}
