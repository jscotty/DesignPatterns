package math;

public class Vector2 {

	
	public float xPos;
	public float yPos;
	
	public Vector2(){
		this.xPos = 0.0f;
		this.yPos = 0.0f;
	}

	public Vector2(float xPos,float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public static Vector2 zero(){
		return new Vector2(0,0);
	}
	
	public void normalize(){
		double length = Math.sqrt((xPos*xPos)+(yPos*yPos));
		
		if(length != 0.0){
			float s = 1.0f / (float) length;
			xPos = xPos * s;
			yPos = yPos * s;
		}
	}
	
	public boolean equals(Vector2 vector){
		return(xPos == vector.xPos && yPos == vector.yPos);
	}
	
	public Vector2 add(Vector2 vector){
		xPos = xPos + vector.xPos;
		yPos = yPos + vector.yPos;
		return new Vector2(xPos,yPos);
	}
	
	public Vector2 substract(Vector2 vector){
		xPos = xPos - vector.xPos;
		yPos = yPos - vector.yPos;
		return new Vector2(xPos,yPos);
	}
	
	public static double getDistanceOnScreen(Vector2 v1, Vector2 v2){ 
		float vector1 = v1.xPos - v2.xPos;
		float vector2 = v1.yPos - v2.yPos;
		
		return Math.sqrt((vector1 * vector1) + (vector2 * vector2));
	}
	
	public void addVector(Vector2 v){
		this.xPos = v.xPos;
		this.yPos = v.yPos;
	}
	
	public float length(){
		return (float)(Math.sqrt(xPos * xPos + yPos * yPos));
	}
	
	public void setLength(float value){
		if(value < 0){
			System.err.println("length can not be smaller than 0");
		} else {
			xPos = value;
			yPos = value;
		}
	}
}
