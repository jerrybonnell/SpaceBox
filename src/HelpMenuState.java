import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class HelpMenuState extends GameState {
	
	private String[] choices;
	private int currentChoice;
	private double timer; 

	public HelpMenuState(GameEngine game) {
		super(game);
		choices = new String[1];
		choices[0] = "Back"; 
		timer = 0;
		
	}

	
	public void update(InputHandler input, double tpf) {
		
		if(input.down.updatesPressed() == 1) {
			currentChoice++; 
			currentChoice %= choices.length; 
		}
		if(input.up.updatesPressed() == 1) {
			currentChoice--; 
			if(currentChoice < 0)
				currentChoice = choices.length - 1;
		}
		
		if (input.enter.updatesPressed() == 1) {
			switch(currentChoice) {
			case (0):
				game.popState();
				break;
			default: 
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}
		
		timer += tpf;
					
		
	}

	Color box = new Color(255, 255, 255);
	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", 1, 70));
		int titleWidth = g.getFontMetrics().stringWidth("How to Play");
		g.drawString("How to Play", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * 0.2));
		
		
		for(int i = 0; i < choices.length; i++) {
			if(currentChoice == i) {
				g.setFont(new Font("Courier New", 1, 40));
				titleWidth = g.getFontMetrics().stringWidth("> " + choices[i] + " <");
				g.drawString("> " + choices[i] + " <", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.45 * (i + 2))));
			
			} else {
				g.setFont(new Font("Courier New", 0, 40));
				titleWidth = g.getFontMetrics().stringWidth(choices[i]);
				g.drawString(choices[i], (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * (0.45 * (i + 2))));
		
			}
		}
		g.setFont(new Font("Consolas", 0, 20));
		g.drawImage(assets.getImage("enemy1"), 200 , 170 , 50, 50, null);
		g.drawString("Sir Bologna", 260, 200);
		g.drawImage(assets.getImage("enemy3"), 200 , 230 , 50, 50, null);
		g.drawString("Nonchalance", 260, 260);
		g.drawImage(assets.getImage("enemy2"), 200 , 290 , 50, 50, null);
		g.drawString("Mr. Freeze", 260, 315);
		g.drawRect(200, 350, 50, 50);
		g.drawString("Danger", 260, 380);
		if (timer > 1) {
			box = new Color((int) (Math.random() * Integer.MAX_VALUE));
			timer  = 0;
		}
		g.setColor(box);
		g.fillRect(200, 410, 50, 50);
		g.drawString("Bouncy", 260, 440);
		g.setColor(Color.WHITE);
		g.drawLine(400,170,400,400);
		g.setFont(new Font("Impact", 0, 25));
		g.drawString("DONT TOUCH!", 420, 290);
	}

}
