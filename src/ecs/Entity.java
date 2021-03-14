package ecs;

import java.util.List;

import subject.Subject;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entity {
	private List<Component> components = new ArrayList<>();
	
	// Accessing a component by getting it from our stored components
	// list
	public <T> T getComponent(Class<T> c) throws IllegalArgumentException {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(c.isInstance(component)) {
				return c.cast(component);
			}
		}
		
        throw new IllegalArgumentException("Component not found " + c.getName());
	}
	

	// Adding a component to our list
    public void addComponent(Component c) {
        if (c.getEntity() != null) {
            throw new IllegalArgumentException("component already attached an entity");
        }
        
        components.add(c);
        c.setEntity(this);
    }
    
    public <T> T removeComponent(Class<T> c, boolean dispose) throws IllegalArgumentException {
    	Component result = null;
		for (Component component : components) {
			if(c.isInstance(component)) {
				result = component;
			}
		}
		
		if(result != null) {
			components.remove(result);
			if(dispose) {
				result.dispose();
			}
			return c.cast(result);
		}
		
		
        throw new IllegalArgumentException("Component not found " + c.getName());
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
    
    // update all components
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
    
    //render all components
    public void render(Graphics2D g) {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(component.isActive()) {
				component.render(g);
			}
		}
    }
    
    // disposing all components
    public void dispose() {
    	for (int i = 0; i < components.size(); i++) {
    		Component component = components.get(i);
			if(component.isActive()) {
				component.dispose();
			}
		}
    }
}
