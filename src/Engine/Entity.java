package Engine;
import java.awt.Graphics2D;


public class Entity {

	private double width;
	private double height;
	public double x;
	public double y; 
	public double vx;
	public double vy; 
	
	public Entity (double width, double height, double x, double y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	public Entity(double width, double height) {
		this(width, height, 0, 0);
	}
	
	public void update(double tpf) {
		x += vx * tpf; 
		y += vy * tpf; 
	}
	
	public void render(Graphics2D g,Camera cam) {
		int sw = (int)(width * cam.getZoom());
		int sh = (int) (height * cam.getZoom());
		g.drawRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner		
	}
	
}