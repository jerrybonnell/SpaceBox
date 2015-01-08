import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;

public class SpaceBoxState extends GameState {

	Stage stage;

	public SpaceBoxState(GameEngine game) {
		super(game);

		stage = new Stage(1, 1, 1);
		stage.loadStage(new File("level.dat"));
		game.getAssets().stopAllMusic();
		game.getAssets().getMusic("gameplay").play(true);
	}

	public void update(InputHandler input, double tpf) {

		if (input.p.isPressed() || input.esc.isPressed()) {
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
