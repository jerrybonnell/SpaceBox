import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class GameOverState extends GameState {
	
	private String[] choices;
	private int currentChoice;
	private double time;
	private MrFreeze mrFreeze;
	private Nonchalance nonchalance;
	private SirBologna sirBologna; 
	
	public GameOverState(GameEngine game) {
		super(game);
		time = 0; 
		choices = new String[3]; 
		choices[0] = "Retry?";
		choices[1] = "Save Score";
		choices[2] = "Return to Main Menu";
		currentChoice = 0;
		mrFreeze = new MrFreeze(10, 10, -18, 35);
		mrFreeze.setDisplacement(5, 0);
		nonchalance = new Nonchalance(10, 10, 0, 35);
		nonchalance.setRadius(3);
		sirBologna = new SirBologna(10, 10, 18, 35);
		sirBologna.setDisplacement(5, 0);
		game.getAssets().stopAllMusic();
		game.getAssets().getMusic("gameover").play(true);
	}

	
	public void update(InputHandler input, double tpf) {
		time += tpf; 
		if(input.down.updatesPressed() == 1) {
			currentChoice++; 
			currentChoice %= choices.length; 
		}
		if(input.up.updatesPressed() == 1) {
			currentChoice--; 
			if(currentChoice < 0)
				currentChoice = choices.length -1;
		}
		
		if (input.enter.updatesPressed() == 1) {
			switch(currentChoice) {
			case (0):
				game.popState();
				game.popState();
				game.pushState(new SpaceBoxState(game));
				break;
			case(1): 
				game.pushState(new NewScoreState(game));
				break;
			case(2):
				game.getAssets().stopAllMusic();
				game.getAssets().getMusic("general").play(true);
				game.popState();
				game.popState();
				break;
			default: 
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}
		
		mrFreeze.update(input, tpf);
		nonchalance.update(input, tpf);
		sirBologna.update(input, tpf);
	}

	@Override
	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		cam.reset();
		cam.setWidth(200);
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", 1, 70));
		int titleWidth = g.getFontMetrics().stringWidth("Game Over!!");
		g.drawString("Game Over!!", (cam.getResX() - titleWidth) /2 , (int) (cam.getResY() * 0.2));
		
		
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
			g.fillOval(i * 30, (int)((i % 5 + 2) * time * 0.6*resY) % resY, 5 , 5);
		   
		}
		
		mrFreeze.render(g, cam, assets);
		nonchalance.render(g, cam, assets);
		sirBologna.render(g, cam, assets);
		
	}

}
