package Engine;
public class Camera {

	private int resX;
	private int resY;
	private double x;
	private double y;
	private double zoom;

	public Camera(int resX, int resY, double x, double y, double zoom) {
		this.resX = resX;
		this.resY = resY;
		this.x = x;
		this.y = y;
		this.zoom = zoom;
	}

	public Camera(int resX, int resY, double x, double y) {
		this(resX, resY, x, y, 1);
	}

	public Camera(int resX, int resY) {
		this(resX, resY, 0, 0);
	}

	public int screenX(double gameX) {
		return resX/2 + (int) ((gameX - x) * zoom);
	}
	public int screenY(double gameY) {
		return resY/2 - (int) ((gameY - y) * zoom);
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

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public int getResX() {
		return resX;
	}

	public int getResY() {
		return resY;
	}
	
	public void setRes(int resX, int resY) {
		this.resX = resX;
		this.resY = resY; 
	}
	
}
