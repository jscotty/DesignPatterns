package gamestate.states;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gamestate.GameState;
import gamestate.GameStateManager;
import sprite.Sprites;
import ui.Button;

public class MenuState extends GameState {
	
	private Button btnStart;

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		btnStart = new Button(800 / 2, 600 / 2, 
				Sprites.instance.getBtnStartNormal(),
				Sprites.instance.getBtnStartHover(),
				Sprites.instance.getBtnStartPressed());
		
		btnStart.setOnClickAction(new OpenPlayState());
	}
	
	private class OpenPlayState extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			gsm.setState(new PlayState(gsm));
		}
	}

	@Override
	public void update(double deltaTime) {
		btnStart.update(deltaTime);
	}

	@Override
	public void render(Graphics2D g) {
		btnStart.render(g);
	}

	@Override
	public void dispose() {
		btnStart.dispose();
	}

}
