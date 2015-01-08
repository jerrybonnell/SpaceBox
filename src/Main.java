import kuusisto.tinysound.TinySound;
import Engine.GameEngine;


public class Main {
	
	public static void main(String[] args) {
		TinySound.init();
		GameEngine game = new GameEngine("Space Box", 800, 600);
		game.getAssets().clearImages();
		game.getAssets().loadImage("res/glider.png", "glider");
		game.getAssets().loadImage("res/enemy1.png", "enemy1");
		game.getAssets().loadImage("res/enemy2.png", "enemy2");
		game.getAssets().loadImage("res/enemy3.png", "enemy3");
		game.getAssets().loadImage("res/fire.png", "fire");
		game.getAssets().loadImage("res/boom.png", "boom");
		game.getAssets().loadMusic("Pamgaea.wav", "general");
		game.getAssets().loadMusic("Ouroboros.wav", "gameplay");
		game.getAssets().loadMusic("Show Your Moves.wav", "gameover");
		game.pushState(new MainMenuState(game));
		game.start();
		TinySound.shutdown();
	}

}
