import java.awt.Graphics2D;
import java.awt.Image;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;
import Engine.InputHandler;


public class Nonchalance extends Entity {
	
	private double time; 
	private double r;
	private double cx;
	private double cy; 
	private double speed; 

	public Nonchalance (double width, double height, double x, double y) {
		super(width, height, x, y);
		ID = IDList.NONCHALANCE;
		time = 0;
		cx = x;
		cy = y; 
		speed = 1.8;
	}
	
	public void update(InputHandler input, double tpf) {
		super.update(input, tpf);
		time += tpf * speed; 
		x = cx + r * Math.sin(time); 
		y = cy + r * Math.cos(time); 
	}
	
	public void setRadius(double r) {
		this.r = r;
	}
	
	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		Image img = assets.getImage("enemy3"); 
		int sw = (int)(width * cam.getScale());
		int sh = (int) (height * cam.getScale());
		
		if (img == null) {
			g.drawRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner	
			return;
		} 
		g.drawImage(img, cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, cam.screenX(x) + sw/2,
				cam.screenY(y) + sh/2, 0, 0, img.getWidth(null), img.getHeight(null), null);

	}

}
