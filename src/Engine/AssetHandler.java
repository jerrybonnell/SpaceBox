package Engine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AssetHandler {
	
	private HashMap<String,Image> images; 
	
	public AssetHandler() {
		images = new HashMap<String, Image>();
	}

	public void loadImage(String URL, String name) {
		Image img;
		try {
			img = ImageIO.read(new File(URL));
			images.put(name, img); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Image getImage(String name) {
		return images.get(name);
	}
	
	
	
}
