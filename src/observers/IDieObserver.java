package observers;

public interface IDieObserver extends IObserver {
	// when subject dies, it fires onDie method to
	// all observers
	public <T> void onDie(Class<T> c);
}
