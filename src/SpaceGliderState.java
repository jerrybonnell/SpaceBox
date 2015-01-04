import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class SpaceGliderState extends GameState {
	
	Stage stage;

	public SpaceGliderState(GameEngine game) {
		super(game);
		
		game.getAssets().clearImages();
		game.getAssets().loadImage("res/glider.png", "glider");
		game.getAssets().loadImage("res/enemy1.png", "enemy1");
		game.getAssets().loadImage("res/enemy2.png", "enemy2");
		game.getAssets().loadImage("res/enemy3.png", "enemy3");
		game.getAssets().loadImage("res/background.png", "background");
		game.getAssets().loadImage("res/fire.png", "fire");
		stage = new Stage(1,1,1);
		stage.loadStage(new File("level.dat"));;
	}


	public void update(InputHandler input, double tpf) {
		
		if(input.p.isPressed() || input.esc.isPressed()) {
			game.pushState(new PauseState(game));
		}
		stage.update(input, tpf);
		if (stage.wasLost()) {
			game.pushState(new GameOverState(game));
		}
		if (stage.wasWon()) {
			game.pushState(new GameWinState(game));
		}
	
	}

	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		stage.render(g, cam, assets);
	}
	

}
