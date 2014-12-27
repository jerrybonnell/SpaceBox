import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Engine.Camera;
import Engine.Entity;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;

//notes: add a title 

public class MainMenuState extends GameState {

	private ArrayList<Entity> entities;
	private double time = 0;
	
	public MainMenuState(GameEngine game) {
		super(game);
		entities = new ArrayList<Entity>();
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++) {
				Entity e = new Entity(20, 20, i * 30 - 150 , j * 30 - 150);
				e.vx = Math.random() * 10 - 5;
				e.vy = Math.random() * 10 - 5;
				entities.add(e);
			}
	}

	public void update(InputHandler input, double tpf) {
		for(Entity e : entities)
			e.update(tpf);
		time += tpf;
	}

	public void render(Graphics2D g, Camera cam) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		for(int i = 0; i < entities.size(); i++) {
			g.setColor(new Color((int)(i * time) % 255, (int) (time * time) % 255, (i + 100) % 255));
			entities.get(i).render(g, cam);
		}
	}

}
