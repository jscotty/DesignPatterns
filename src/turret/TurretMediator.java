package turret;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import bullet.BulletMediator;
import components.Transform;
import main.GameWindow;
import math.Vector2;

// listening to mouse to shoot our turrets!
public class TurretMediator implements MouseListener{
	
	private static final int turretCount = 10;
	
	private List<TurretBase> turrets = new ArrayList<>();
	
	private BulletMediator bulletMediator;
	
	public TurretMediator(BulletMediator bulletMediator) {
		// caching bullet mediator which will be used to fire our bullet!
		this.bulletMediator = bulletMediator;
		
		// moved turret creation from gameloop here!
		TurretFactory turretFactory = new TurretFactory();
		for (int i = 0; i < turretCount; i++) {
			float x = ((800 / turretCount) / 2) + i * (800 / turretCount);
			turrets.add(turretFactory.getTurret(x, 500, i, turretCount));
		}
		
		GameWindow.instance.addMouseListener(this);
	}
	
	// shooting turret towards mouse position!
	public void shootTurret(int x, int y) {
		// shooting position, used to get distance
		Vector2 shootPosition = new Vector2(x, y);
		// caching closest turret
		TurretBase closestTurret = null;
		// having a really high number so it will loop through all turrets.
		// when lower number is found it will cache that distance and found turret until
		// a new closer distance towards position is found.
		double closestDistance = 99999999;
		for (TurretBase turretBase : turrets) {
			double distance = Vector2.getDistanceD(turretBase.getComponent(Transform.class).position, shootPosition);
			if(distance < closestDistance) {
				closestDistance = distance;
				closestTurret = turretBase;
			}
		}
		
		// Shoot from turret closest to our mouse position!
		bulletMediator.addBullet(closestTurret.shoot(shootPosition));
	}
	
	public void update(double deltaTime) {
		for (TurretBase turret : turrets) {
			turret.update(deltaTime);
		}
	}
	
	public void render(Graphics2D g) {
		for (TurretBase turret : turrets) {
			turret.render(g);
		}
	}
	
	public void dispose() {
		GameWindow.instance.removeMouseListener(this);

		for (TurretBase turret : turrets) {
			turret.dispose();
		}
		
		turrets.clear();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// - 32 because of top bar size
		shootTurret(e.getX(), e.getY() - 32);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
