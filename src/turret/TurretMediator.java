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
import missile.MissileMediator;

public class TurretMediator implements MouseListener{
	
	private List<TurretBase> turrets = new ArrayList<>();
	
	private BulletMediator bulletMediator;
	
	public TurretMediator(BulletMediator bulletMediator) {
		this.bulletMediator = bulletMediator;
		
		TurretFactory turretFactory = new TurretFactory();
		int turretCount = 6;
		for (int i = 0; i < turretCount; i++) {
			float x = ((800 / turretCount) / 2) + i * (800 / turretCount);
			turrets.add(turretFactory.getTurret(x, 500, i, turretCount));
		}
		
		GameWindow.instance.addMouseListener(this);
	}
	
	public void shootTurret(int x, int y) {
		Vector2 shootPosition = new Vector2(x, y);
		TurretBase closestTurret = null;
		double closestDistance = 99999999;
		for (TurretBase turretBase : turrets) {
			double distance = Vector2.getDistanceD(turretBase.getComponent(Transform.class).position, shootPosition);
			if(distance < closestDistance) {
				closestDistance = distance;
				closestTurret = turretBase;
			}
		}
		
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
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		shootTurret(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
