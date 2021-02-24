package subjects;

import observers.IObserver;

public abstract interface ISubject {
	public void register(IObserver observer);
	public void unregister(IObserver observer);
}
