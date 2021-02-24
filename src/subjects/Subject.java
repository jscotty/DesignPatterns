package subjects;

import java.util.ArrayList;
import java.util.List;
import observers.IObserver;

public class Subject implements ISubject {
	
	protected List<IObserver> observers = new ArrayList<>();

	@Override
	public void register(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregister(IObserver observer) {
		if(!observers.contains(observer)) {
            throw new IllegalArgumentException("This subject does not contain this observer");
		}
		
		observers.remove(observer);
	}
}
