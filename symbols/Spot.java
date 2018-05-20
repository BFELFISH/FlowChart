package symbols;

public class Spot {
	double x;
	double y;
	
	public Spot(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Spot() {
		this(0, 0);
	}
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
}
