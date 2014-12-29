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

	public Glider(double width, double height, double x, double y) {
		super(width, height, x, y);
		ID = IDList.GLIDER; 
		speed = 0;
		acceleration = 20; 
	}

	public void update(InputHandler input, double tpf) {
		super.update(input, tpf);
		
		if (input.a.isPressed())
			direction += tpf; 
		if (input.d.isPressed())
			direction -= tpf;
		
		if (input.w.isPressed()) {
			speed += acceleration * tpf;
		}
		if (input.s.isPressed()) {
			speed -= 2 * acceleration * tpf; 
		}
		
		if (!input.w.isPressed() && !input.s.isPressed())
			speed -= acceleration * tpf * Math.signum(speed);
		
		vx = speed * Math.cos(direction);
		vy = speed * Math.sin(direction);
	}
	
	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		Image img = assets.getImage("glider"); 
		int sw = (int)(width * cam.getZoom());
		int sh = (int) (height * cam.getZoom());
		
		if (img == null) {
			g.drawRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner	
			return;
		} 
		
		g.rotate(-direction,cam.screenX(x), cam.screenY(y));
		g.drawImage(img, cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, cam.screenX(x) + sw/2,
				cam.screenY(y) + sh/2, 0, 0, img.getWidth(null), img.getHeight(null), null);
		g.rotate(direction, cam.screenX(x), cam.screenY(y));
	}
	
	
}
