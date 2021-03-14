package subject;

import java.util.ArrayList;
import java.util.List;
import observers.IObserver;

public class Subject {
	
	protected List<IObserver> observers = new ArrayList<>();

	// adding observers.
	public void register(IObserver observer) {
		observers.add(observer);
	}

	// remove observer
	public void unregister(IObserver observer) {
		if(!observers.contains(observer)) {
            throw new IllegalArgumentException("This subject does not contain this observer");
		}
		
		observers.remove(observer);
	}
	
	// each subject can decide what observer it wants
	// to notify. Therefor they can call this method
	// to request all observers of a certain type.
	public<T> List<T> getObservers(Class<T> c){
		List<T> result = new ArrayList<>();
		
		for (int i = 0; i < observers.size(); i++) {
			IObserver observer = observers.get(i);
			if(c.isInstance(observer)) {
				result.add(c.cast(observer));
			}
		}
		
		return result;
	}
}
