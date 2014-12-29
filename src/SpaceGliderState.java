import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class SpaceGliderState extends GameState {
	
	private Glider player;
	private ArrayList<Entity> enemies;
	private Entity bottomWall, topWall, leftWall, rightWall; 
	Random rand; 
	
	public SpaceGliderState(GameEngine game) {
		super(game);
		game.getAssets().loadImage("res/glider.png", "glider");
		game.getAssets().loadImage("res/enemy1.png", "enemy1");
		game.getAssets().loadImage("res/enemy2.png", "enemy2");
		game.getAssets().loadImage("res/enemy3.png", "enemy3");
		game.getAssets().loadImage("res/background.png", "background");
		player = new Glider(1, 1, 2, 2);
		enemies = new ArrayList<Entity>();
		bottomWall = new Entity(15, 1,  7,0);
		topWall = new Entity(15, 1, 7, 47);
		rightWall = new Entity(1, 50, 15, 25);
		leftWall = new Entity(1, 50, 0, 25); 
		
		
		/*rand = new Random();
		non.setRadius(rand.nextDouble() * 40 + 50);
		if(rand.nextInt(2) == 0) 
			mrf.setDisplacement(60, 0);
		else 
			mrf.setDisplacement(0, 60);
		*/
		game.getCamera().setZoom(30);
	}


	public void update(InputHandler input, double tpf) {
		
		player.update(input, tpf);
		if(input.esc.updatesPressed() == 1 || input.p.updatesPressed() == 1) {
			game.pushState(new PauseState(game));
			return; 
		}
		//update the movements of each entity 
		for (Entity e : enemies) {
			e.update(input, tpf);
		}
		bx += 20 * tpf;
	}

	double bx, by;
	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		bottomWall.render(g, cam, assets);
		topWall.render(g, cam, assets);
		rightWall.render(g, cam, assets);
		leftWall.render(g, cam, assets);
		player.render(g, cam, assets);
		cam.setX(player.x);
		cam.setY(player.y);
	}
	

}
