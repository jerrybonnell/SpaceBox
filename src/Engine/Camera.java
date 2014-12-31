package Engine;
public class Camera {

	private int resX;
	private int resY;
	private double x;
	private double y;
	private double width;

	public Camera(int resX, int resY, double x, double y, double width) {
		this.resX = resX;
		this.resY = resY;
		this.x = x;
		this.y = y;
		this.width = width;
	}

	public Camera(int resX, int resY, double x, double y) {
		this(resX, resY, x, y, resX);
	}

	public Camera(int resX, int resY) {
		this(resX, resY, 0, 0);
	}

	public int screenX(double gameX) {
		return resX/2 + (int) ((gameX - x) * resX/width);
	}
	public int screenY(double gameY) {
		return resY/2 - (int) ((gameY - y) * resX/width);
	}

	public void move(double x, double y) {
		this.x += x;
		this.y += y;
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getResX() {
		return resX;
	}

	public int getResY() {
		return resY;
	}
	
	public double getScale() {
		return resX / width;
	}
	
	public void setRes(int resX, int resY) {
		this.resX = resX;
		this.resY = resY; 
	}
	
	public void reset() {
		x = 0;
		y = 0;
		width = resX;
	}
}
