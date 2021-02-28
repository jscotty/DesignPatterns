package main;

import observers.IMainObserver;
import observers.IObserver;
import subjects.Subject;

// used for scoring
public class MainManager extends Subject {
	
	private static final int totalLives = 5;
	
	public static MainManager instance; // singleton
	
	private int score = 0;
	private int lives = totalLives;
	
	public MainManager() {
		instance = this;
	}
	
	public void addScore(int amount) {
		score += amount;
		updateScore();
	}
	
	public void removeLive() {
		lives --;
		updateLives();
		if(lives == 0) {
			gameOver();
		}
	}
	
	public void reset() {
		score = 0;
		lives = totalLives;
		updateScore();
	}
	
	private void updateScore() {
		for (IObserver observer : observers) {
			((IMainObserver) observer).scoreUpdated(score);
		}
	}
	
	private void updateLives() {
		for (IObserver observer : observers) {
			((IMainObserver) observer).livesUpdated(lives);
		}
	}
	
	private void gameOver() {
		for (IObserver observer : observers) {
			((IMainObserver) observer).gameOver();
		}
	}
}
