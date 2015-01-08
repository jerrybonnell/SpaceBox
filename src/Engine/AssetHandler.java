package Engine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class AssetHandler {
	
	private HashMap<String,Image> images; 
	private HashMap<String,Music> music; 
	private HashMap<String,Sound> sounds; 
	
	public AssetHandler() {
		images = new HashMap<String, Image>();
		music = new HashMap<String, Music>();
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
	public void loadMusic(String loc, String name) {
		Music m;
		m = TinySound.loadMusic(loc);
		music.put(name, m);
	}
	public Music getMusic(String name) {
		return music.get(name);
	}
	
	public void stopAllMusic() {
		Set<String> keys = music.keySet();
		for(String s : keys) {
			music.get(s).stop();
		}
	}
	
	public void clearImages() {
		images.clear();
	}
	
	
}
