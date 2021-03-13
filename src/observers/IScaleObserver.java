package observers;

import components.Image;

public interface IScaleObserver extends IObserver {
	// when subject scaled, it fires onScale method to
	// all observers passing the image for subjects to check
	// the image data.
	public void onScale(Image image);
}
