package observers;

public interface IDieObserver extends IObserver {
	public <T> void onDie(Class<T> c);
}
