package observers;

public interface IMainObserver extends IObserver {
	public void gameOver();
	public void scoreUpdated(int score);
	public void livesUpdated(int lives);
}
