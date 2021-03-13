package observers;

public interface IMoveToPositionObserver extends IObserver{
	// when subject reaches position, it fires onDie method to
	// all observers
	public void onPosition();
}