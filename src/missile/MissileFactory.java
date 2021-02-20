package missile;

public class MissileFactory {
	
	public Missile GetMissile(float x, float y, MissileType type) {
		Missile result = null;
		switch (type) {
			case Normal:
				result = new MissileNormal(x, y);
				break;
			case Fast:
				result = new MissileFast(x, y);
				break;
			case Slow:
				result = new MissileSlow(x, y);
				break;
			default:
				result = GetMissileRandom(x, y);
				break;
		}

		result.setCreationType(type);
		return result;
	}
	
	public Missile GetMissileRandom(float x, float y) {
		double percentage = Math.random() * 100;
		
		if(percentage < 33) {
			return new MissileFast(x, y);
		} else if(percentage < 66) {
			return new MissileSlow(x, y);
		} else {
			return new MissileNormal(x, y);
		}
	}
}
