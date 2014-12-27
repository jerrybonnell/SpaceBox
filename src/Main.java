import Engine.GameEngine;


public class Main {
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine("Space Glider", 800, 600);
		game.pushState(new MainMenuState(game));
		game.start();
	}

}
