package missile;

public class MissileFactory {

	private int normalMissilesCount;
	private int fastMissilesCount;
	private int slowMissilesCount;
	
	public MissileFactory(int normalCount, int fastCount, int slowCount) {
		normalMissilesCount = normalCount;
		fastMissilesCount = fastCount;
		slowMissilesCount = slowCount;
	}
	
	public Missile getMissile(float x, float y, int index) {
		MissileType type = null;
		
		if(index < normalMissilesCount) {
			type = MissileType.Normal;
		} else if(index < normalMissilesCount + fastMissilesCount) {
			type = MissileType.Fast;
		} else if(index < normalMissilesCount + fastMissilesCount + slowMissilesCount) {
			type = MissileType.Slow;
		}
		
		return getMissile(x, y, type);
	}
	
	public Missile getMissile(float x, float y, MissileType type) {
		Missile result = null;
		
		if(type == null) {
			return  getMissileRandom(x, y);
		}
		
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
		}

		result.setCreationType(type);
		return result;
	}
	
	public Missile getMissileRandom(float x, float y) {
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
