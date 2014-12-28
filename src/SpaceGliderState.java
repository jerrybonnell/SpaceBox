import java.awt.Color;
import java.awt.Graphics2D;

import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class SpaceGliderState extends GameState {

	public SpaceGliderState(GameEngine game) {
		super(game);
		
	}


	public void update(InputHandler input, double tpf) {
		
		if(input.esc.updatesPressed() == 1 || input.p.updatesPressed() == 1) {
			game.pushState(new PauseState(game));
			return; 
		}
		
	}


	public void render(Graphics2D g, Camera cam) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		
		
	}
	

}
