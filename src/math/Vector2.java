package math;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(){
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Vector2(float xPos,float yPos){
		this.x = xPos;
		this.y = yPos;
	}
	
	public static Vector2 zero(){
		return new Vector2(0,0);
	}
	
	public Vector2 normalize(){
		double length = Math.sqrt((x * x)+(y * y));
		
		if(length != 0.0){
			float s = 1.0f / (float) length;
			x = x * s;
			y = y * s;
		}
		
		return this;
	}
	
	public boolean equals(Vector2 vector){
		return(x == vector.x && y == vector.y);
	}
	
	public Vector2 add(Vector2 vector){
		return new Vector2(x + vector.x, y + vector.y);
	}
	
	public Vector2 substract(Vector2 vector){
		return new Vector2(x - vector.x, y - vector.y);
	}
	
	public static double getDistanceD(Vector2 v1, Vector2 v2){ 
		float vector1 = v1.x - v2.x;
		float vector2 = v1.y - v2.y;
		
		return Math.sqrt((vector1 * vector1) + (vector2 * vector2));
	}
	
	public static float getDistanceF(Vector2 v1, Vector2 v2){ 
		float vector1 = v1.x - v2.x;
		float vector2 = v1.y - v2.y;
		
		return (float) Math.sqrt((vector1 * vector1) + (vector2 * vector2));
	}
	
	public Vector2 direction(Vector2 target) {
		return target.substract(this).normalize();
	}
	
	public void addVector(Vector2 v){
		this.x = v.x;
		this.y = v.y;
	}
	
	public float length(){
		return (float)(Math.sqrt(x * x + y * y));
	}
	
	public void setLength(float value){
		if(value < 0){
			System.err.println("length can not be smaller than 0");
		} else {
			x = value;
			y = value;
		}
	}
}
