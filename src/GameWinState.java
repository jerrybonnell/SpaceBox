import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class GameWinState extends GameState {
	
	private String[] choices;
	private int currentChoice;
	private double time; 
	
	public GameWinState(GameEngine game) {
		super(game);
		time = 0; 
		choices = new String[1]; 
		choices[0] = "Sweet!";
		currentChoice = 0;
	}

	
	public void update(InputHandler input, double tpf) {
		time += tpf; 
		if (input.enter.updatesPressed() == 1) {
			switch(currentChoice) {
			case (0):
				game.popState();
				game.popState();
				break;
			default: 
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}	
		
	}

	@Override
	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", 1, 70));
		int titleWidth = g.getFontMetrics().stringWidth("Congratulations! You Win!!");
		g.drawString("Congratulations! You Win!!", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * 0.2));
		
		
		for(int i = 0; i < choices.length; i++) {
			if(currentChoice == i) {
				g.setFont(new Font("Courier New", 1, 40));
				titleWidth = g.getFontMetrics().stringWidth("> " + choices[i] + " <");
				g.drawString("> " + choices[i] + " <", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.2 * (i + 2))));
			
			} else {
				g.setFont(new Font("Courier New", 0, 40));
				titleWidth = g.getFontMetrics().stringWidth(choices[i]);
				g.drawString(choices[i], (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.2 * (i + 2))));
		
			}
		}
		
		int resX = cam.getResX();
		int resY = cam.getResY();
		for(int i = 0; i < 1020; i++) {
			g.setColor(new Color((i *240) % 255, (i + 150) % 255, (i * 100) % 255));
			g.fillOval((int)((i % 5 + 2) * time * 0.6*resX) % resX, i * 30, 5 , 5);
		   
		}
	}

}
