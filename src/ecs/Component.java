package ecs;

import java.awt.Graphics2D;

public abstract class Component {

	private boolean active = true;
	
    protected Entity entity;

    public boolean isActive() {
    	return active;
    }
    
    public void setEntity(Entity e) {
        entity = e;
        init();
    }
    
    public Entity getEntity() {
        return entity;
    }
    
    public abstract void init();
    
    public abstract void update(double deltaTime);
    
    public abstract void render(Graphics2D g);
}
