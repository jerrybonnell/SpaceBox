import java.awt.Graphics2D;
import java.awt.Image;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;
import Engine.InputHandler;


public class SirBologna extends Entity {
	
	private double time; 
	private double dx;
	private double dy; 
	private double cx;
	private double cy; 
	private double speed; 

	public SirBologna(double width, double height, double x, double y) {
		super(width, height, x, y);
		ID = IDList.SIR_BOLOGNA;
		time = 0;
		cx = x;
		cy = y; 
		speed = 10;
	}
	
	public void update(InputHandler input, double tpf) {
		super.update(input, tpf);
		time += tpf * speed; 
		x = cx + dx * Math.sin(time); 
		y = cy + dy * Math.sin(2*time); 
	}
	
	public void setDisplacement(double dx, double dy) {
		this.dx = dx;
		this.dy = dy; 
	}
	
	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		Image img = assets.getImage("enemy1"); 
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
