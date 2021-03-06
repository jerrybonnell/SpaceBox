import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import Engine.AssetHandler;
import Engine.Camera;
import Engine.Collision;
import Engine.Direction;
import Engine.Entity;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;

//notes: add a title 

public class MainMenuState extends GameState {

	private ArrayList<Entity> entities;
	private String choices[];
	private int currentChoice; 
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
		choices = new String[4];
		currentChoice = 0; 
		choices[0] = "Play";
		choices[1] = "Scores";
		choices[2] = "Help";
		choices[3] = "Quit"; 
		
		game.getAssets().getMusic("general").play(true);
	}

	public void update(InputHandler input, double tpf) {
		for(Entity e : entities)
			e.update(input, tpf);
		time += tpf;
		if(input.down.updatesPressed() == 1) {
			currentChoice++; 
			currentChoice %= choices.length; 
		}
		if(input.up.updatesPressed() == 1) {
			currentChoice--; 
			if(currentChoice < 0)
				currentChoice = choices.length - 1;
		}
		for (int i = 0; i < entities.size(); i++)
			for (int j=i + 1; j < entities.size(); j++){
				Collision c = entities.get(i).collides(entities.get(j)); 
				if(c == null)
					continue;
				//if the colliding object is on top, then move the y's coordinate by the intersecting amount 
				if(c.getNormal() == Direction.UP) 
					c.getB().y += c.getPenetration(); 
				//if the colliding object is on bottom, then negate the y's coordinate by intersecting amount
				if(c.getNormal() == Direction.DOWN) 
					c.getB().y -= c.getPenetration(); 
				//same for x coordinates 
				if(c.getNormal() == Direction.LEFT) 
					c.getB().x -= c.getPenetration(); 
				if(c.getNormal() == Direction.RIGHT) 
					c.getB().x += c.getPenetration(); 
				//move the colliding objects in the opposite velocity	
				if(c.getNormal() == Direction.UP || c.getNormal() == Direction.DOWN) {
					c.getA().vy *= -1; 
					c.getB().vy *= -1;
				}
				if(c.getNormal() == Direction.LEFT || c.getNormal() == Direction.RIGHT) {
					c.getA().vx *= -1; 
					c.getB().vx *= -1;
				}
				
								
			}
		if (input.enter.updatesPressed() == 1) {
			switch(currentChoice) {
			case (0):
				game.pushState(new SpaceBoxState(game));
				break;
			case (1):
				game.pushState(new ScoreState(game));
				break;
			case(2): 
				game.pushState(new HelpMenuState(game));
				break;
			case(3):
				game.stop();
				JOptionPane.showMessageDialog(null, "Thanks for playing!"); 
				break;
			default: 
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}
		
				
	}

	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		game.getCamera().reset();
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		for(int i = 0; i < entities.size(); i++) {
			g.setColor(new Color((int)(i * time) % 255, (int) (time * time) % 255, (i + 100) % 255));
			entities.get(i).render(g, cam, assets);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", 1, 70));
		int titleWidth = g.getFontMetrics().stringWidth("Space Box!");
		g.drawString("Space Box!", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * 0.2));
		
		
		for(int i = 0; i < choices.length; i++) {
			if(currentChoice == i) {
				g.setFont(new Font("Courier New", 1, 40));
				titleWidth = g.getFontMetrics().stringWidth("> " + choices[i] + " <");
				g.drawString("> " + choices[i] + " <", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.17 * (i + 2))));
			
			} else {
				g.setFont(new Font("Courier New", 0, 40));
				titleWidth = g.getFontMetrics().stringWidth(choices[i]);
				g.drawString(choices[i], (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.17 * (i + 2))));
		
			}
		}

		g.setFont(new Font("Courier New", 0, 10));
		g.drawString("Beta Version 1", 0, 10);
		
	}

}
