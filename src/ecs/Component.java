package ecs;

import java.awt.Graphics2D;

import subject.Subject;

public abstract class Component extends Subject {

	// to toggle it's activity
	private boolean active = true;
	
	// to access entity in components
	// we use this to access other
	// components within components
    protected Entity entity;

    public boolean isActive() {
    	return active;
    }
    
    public void setEntity(Entity e) {
        entity = e;
        init();
    }
    
    public void removeEntity(boolean dispose) {
        entity = null;
        
        if(dispose) {
            dispose();
        }
    }
    
    public Entity getEntity() {
        return entity;
    }
    
    // must have methods
    // each components initializes (needed when willing to access/store
    // other components
    public abstract void init();
    
    // each component calls update
    public abstract void update(double deltaTime);
    
    // each component calls render
    public abstract void render(Graphics2D g);
    
    // no abstract because it's not mandatory. Only for those who assigned as listeners
    public void dispose() {
        
    }
}