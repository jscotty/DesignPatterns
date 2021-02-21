package turret;

public class TurretFactory {

	public TurretBase getTurret(float x, float y, int index, int size) {
		TurretBase result = null;
		
		if(size > 2) {
			float middle = (size - 1) / 2f;
			
			if(index == Math.floor(middle) || index == Math.ceil(middle)) {
				result = new TurretCircle(x, y);
			} else {
				result = new TurretSquare(x, y);
			}
		} else {
			result = new TurretSquare(x, y);
		}
		
		return result;
	}
}
