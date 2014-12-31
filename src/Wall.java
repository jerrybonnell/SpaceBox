import java.awt.Color;
import java.awt.Graphics2D;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;


public class Wall extends Entity{

	public Wall(double width, double height, double x, double y) {
		super(width, height, x, y);
		ID = IDList.WALL;
	}

	public void render(Graphics2D g,Camera cam, AssetHandler assets) {
		int sw = (int)(width * cam.getScale());
		int sh = (int) (height * cam.getScale());
		g.setColor(new Color( (int) y % 255, (int) (y * y % 255),(int) (y + 100) % 255));
		g.fillRect(cam.screenX(x) - sw/2, cam.screenY(y) - sh/2, sw, sh); //sw/2 and sh/2 are used to convert params to corner		
	}
	
}
