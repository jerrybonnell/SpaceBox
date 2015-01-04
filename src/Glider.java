import java.awt.Graphics2D;
import java.awt.Image;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;
import Engine.InputHandler;


public class Glider extends Entity {
	
	private double direction; 
	private double speed; 
	private double acceleration;
	boolean fire = false;

	public Glider(double width, double height, double x, double y) {
		super(width, height, x, y);
		ID = IDList.GLIDER; 
		speed = 0;
		acceleration = 4.5; 
		direction = Math.PI / 2;
	}

	public void update(InputHandler input, double tpf) {
		super.update(input, tpf);
		
		if (input.a.isPressed() || input.left.isPressed())
			direction += 7 * tpf; 
		if (input.d.isPressed() || input.right.isPressed())
			direction -= 7 * tpf;
		
		if (input.w.isPressed() || input.up.isPressed()) {
			acceleration = 10;
			fire = true;
		}		
		if (!(input.w.isPressed() || input.up.isPressed())) {
			acceleration = 0;
			fire = false;
		}
		
		vx += acceleration * Math.cos(direction) * tpf;
		vy += acceleration * Math.sin(direction) * tpf;
		//vy += acceleration * Math.sin(direction) * tpf - 5 * tpf;
	}
	
	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		Image img = assets.getImage("glider"); 
		int sw = (int)(width * cam.getScale());
		int sh = (int) (height * cam.getScale());
		
		if (img == null) {
			g.drawRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner	
			return;
		} 
		g.rotate(-direction,cam.screenX(x), cam.screenY(y));
		if(fire)
			g.drawImage(assets.getImage("fire"), cam.screenX(x) - 15, cam.screenY(y) - 7, sw ,
					sh, null);
		g.drawImage(img, cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, cam.screenX(x) + sw/2,
				cam.screenY(y) + sh/2, 0, 0, img.getWidth(null), img.getHeight(null), null);
		g.rotate(direction, cam.screenX(x), cam.screenY(y));
	}
	
	
}
