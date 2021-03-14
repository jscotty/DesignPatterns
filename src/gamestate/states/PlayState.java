package gamestate.states;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;

import bullet.BulletMediator;
import gamestate.GameState;
import gamestate.GameStateManager;
import main.MainManager;
import missile.Missile;
import missile.MissileFactory;
import missile.MissileMediator;
import observers.IMainObserver;
import sprite.Sprites;
import turret.TurretBase;
import turret.TurretFactory;
import turret.TurretMediator;
import ui.Button;

public class PlayState extends GameState implements IMainObserver {
	private static final String scorePrefix = "Score: ";
	private static final String livesPrefix = "Lives: ";
	
	private Button backBtn;
	private Button restartBtn;
	private BufferedImage gameOverImg = Sprites.instance.getGameOver();

	private TurretMediator turretMediator;
	private BulletMediator bulletMediator;
	private MissileMediator missileMediator;
	
	private boolean initialized = false;
	private boolean gameOver;
	
	private String scoreMsg = scorePrefix + "0";
	private String livesMsg = livesPrefix + "5";

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		gameOver = false;
		MainManager.instance.register(this);
		MainManager.instance.reset();
		
		missileMediator = new MissileMediator();
		bulletMediator = new BulletMediator(missileMediator);
		turretMediator = new TurretMediator(bulletMediator);
		
		initialized = true;
	}

	// internal class to handle button action. Java doesn't allow lambda expressions as
  	// well known in C#, so this is a work-around option ;)
	private class OpenMenuState extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			gsm.setState(new MenuState(gsm));
		}
	}

	@Override
	public void update(double deltaTime) {
		if(!initialized || gameOver) {
			return;
		}
		
		turretMediator.update(deltaTime);
		missileMediator.update(deltaTime);
		bulletMediator.update(deltaTime);
		
		if(gameOver) {
			backBtn.update(deltaTime);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(!initialized) {
			return;
		}
		
		turretMediator.render(g);
		missileMediator.render(g);
		bulletMediator.render(g);

		g.drawString(scoreMsg, 300, 100);
		g.drawString(livesMsg, 300, 120);
		
		if(gameOver) {
			backBtn.render(g);
			g.drawImage(gameOverImg, 400 - (gameOverImg.getWidth() / 2), 300 - (gameOverImg.getHeight() / 2), null);
		}
	}

	@Override
	public void dispose() {
		backBtn.dispose();
		
		turretMediator.dispose();
		missileMediator.dispose();
		bulletMediator.dispose();
		
		MainManager.instance.unregister(this);
	}

	@Override
	public void gameOver() {
		backBtn = new Button(100, 50,
				Sprites.instance.getBtnBackNormal(), Sprites.instance.getBtnBackHover(), Sprites.instance.getBtnBackPressed());
		backBtn.setScale(0.5f);
		
		backBtn.setOnClickAction(new OpenMenuState());
		
		gameOver = true;
	}

	@Override
	public void scoreUpdated(int score) {
		System.out.println("score");
		scoreMsg = scorePrefix + score;
	}
	
	@Override
	public void livesUpdated(int lives) {
		livesMsg = livesPrefix + lives;
	}

}
