package ecs;

import java.util.List;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Entity {
	// storing all our components
	private List<Component> components = new ArrayList<>();
	
	// Accessing a component by getting it from our stored components
	// list
	public <T> T getComponent(Class<T> c) throws IllegalArgumentException {
		for (Component component : components) {
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
	

	// Removing component from our list
    public <T> T removeComponent(Class<T> c) throws IllegalArgumentException {
    	Component result = null;
		for (Component component : components) {
			if(c.isInstance(component)) {
				result = component;
			}
		}
		
		if(result != null) {
			components.remove(result);
			return c.cast(result);
		}
		
		
        throw new IllegalArgumentException("Component not found " + c.getName());
	}
    
    // asking if this entity contains a certain component
    public boolean hasComponent(Class<?> clazz) {    
        for (Component c : components) {
            if (clazz.isInstance(c)) {
                return true;
            }
        }
        return false;
    }
    
    // update all components
    public void update(double deltaTime) {
    	for (Component component : components) {
			if(component.isActive()) {
				component.update(deltaTime);
			}
		}
    }
    
    //render all components
    public void render(Graphics2D g) {
    	for (Component component : components) {
			if(component.isActive()) {
				component.render(g);
			}
		}
    }
    
    // disposing all components
    public void dispose() {
        for (Component component : components) {
            if(component.isActive()) {
                component.dispose();
            }
        }
    }
}