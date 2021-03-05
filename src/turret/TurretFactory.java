package turret;

public class TurretFactory {

	public TurretBase getTurret(float x, float y, int index, int size) {
		if(size > 2) {
			// get middle of our size
			// - 1 because we count from 0 ;)
			float middle = (size - 1) / 2f;
			
			// check ceil and floored numbers
			// ceil as in ceiling and floor as in floored
			// so example size is 6
			// middle is 2.5
			// middle indexes will be 2 and 3
			// square, square, circle, circle, square, square
			if(index == Math.floor(middle) || index == Math.ceil(middle)) {
				return new TurretCircle(x, y);
			} else {
				return new TurretSquare(x, y);
			}
		} else {
			// default
			return new TurretSquare(x, y);
		}
	}
}
