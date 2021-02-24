package ecs;

import java.util.List;

import subjects.Subject;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entity extends Subject {
	private List<Component> components = new ArrayList<>();
	
	/*
	 * If you want to access a component, you can get it through this method.
	 * If entity does not contain component, it will return an
	 * illegalArgumentException
	 */
	public <T> T getComponent(Class<T> c) throws IllegalArgumentException {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(c.isInstance(component)) {
				return c.cast(component);
			}
		}
		
        throw new IllegalArgumentException("Component not found " + c.getName());
	}
	

    public void addComponent(Component c) {
        if (c.getEntity() != null) {
            throw new IllegalArgumentException("component already attached an entity");
        }
        
        components.add(c);
        c.setEntity(this);
    }
    
    public void removeComponent(Component c, boolean dispose) {
        if (c.getEntity() == null) {
            throw new IllegalArgumentException("Component is already detached from entity");
        }
        
        components.remove(c);
        c.removeEntity(dispose);
    }
    
    public boolean hasComponent(Class<?> clazz) {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
            if (clazz.isInstance(component)) {
                return true;
            }
        }
        return false;
    }
    
    public void update(double deltaTime) {
    	// changed all foreach to for loops because when adding/removing component while
    	// foreachloop has been called, the loop will crash.
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(component.isActive()) {
				component.update(deltaTime);
			}
			
		}
    }
    
    public void render(Graphics2D g) {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(component.isActive()) {
				component.render(g);
			}
		}
    }
    
    public void dispose() {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(component.isActive()) {
				component.dispose();
			}
		}
    }
}
