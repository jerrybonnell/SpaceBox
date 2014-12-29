package Engine;
import java.awt.Graphics2D;


public class Entity {

	protected double width;
	protected double height;
	protected int ID = 0; 
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
	
	public void update(InputHandler input, double tpf) {
		x += vx * tpf; 
		y += vy * tpf; 
	}
	
	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		int sw = (int)(width * cam.getZoom());
		int sh = (int) (height * cam.getZoom());
		g.drawRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner		
	}
	
	
	public Collision collides (Entity e) {
		
		double pentX; 
		double pentY; 
		//find intersection region of two rectangles 
		pentX = (width + e.width) / 2 - Math.abs(x - e.x); //take average of widths and subtract by delta x 
		pentY = (height + e.height) / 2 - Math.abs(y - e.y); //take average of heights and subtract by delta y
		
		//if the result is negative, then there was no intersection between the rectangles 
		
		if (pentX < 0 || pentY < 0) 
			return null;
		double penetration; 
		Direction normal; 
		//normal is the direction from which the object came from (not to be confused with the direction it's headed) 
		//normal is either left or right 
		if(pentY > pentX) {
			if (e.x - x > 0) 
				normal = Direction.RIGHT; 
			else
				normal = Direction.LEFT; 
			penetration = pentX;
		//normal is either up or down 
		} else {
			if (e.y - y > 0) 
				normal = Direction.UP;
			else 
				normal = Direction.DOWN; 
			penetration = pentY;
		}
		return new Collision(this, e, normal, penetration); 
		
	}
	
	public int getID() {
		return ID; 
	}
	
}