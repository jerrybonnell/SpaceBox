import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;


public class NewScoreState extends GameState {
	
	private String[] choices;
	private int currentChoice;
	private double time; 
	private char currentChar;
	private String name;
	
	
	public NewScoreState (GameEngine game) {
		super(game);
		time = 0; 
		choices = new String[3]; 
		choices[0] = "Add Letter";
		choices[1] = "Remove Letter";
		choices[2] = "Confirm";
		currentChoice = 0;
		currentChar = 'A';
		name = "";
		
		
	}

	
	public void update(InputHandler input, double tpf) {
		time += tpf;
		
		if(input.right.updatesPressed() == 1)
			currentChar++;
		if(input.left.updatesPressed() == 1)
			currentChar--;
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
				name += currentChar;
				break;
			case (1) :
				if (name.length() > 0)
					name = name.substring(0, name.length() - 1); 
				break;
			case(2) :
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("score.dat", true)))) {
					if(name.length() == 0) 
						name = "Bob";
				    out.println(name + " " + Stage.score);
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
				game.popState();
				game.popState();
				game.popState();
				game.pushState(new ScoreState(game));
				break;
			default: 
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}	
		
	}


	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		int nameWidth = 0;
		
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", 1, 50));
		nameWidth = g.getFontMetrics().stringWidth(name); 
		g.drawString(name, (cam.getResX() - nameWidth) /2 , (int) (cam.getResY() * 0.2));
		int charWidth = g.getFontMetrics().stringWidth(currentChar + ""); 
		
		g.drawString("" + currentChar, (cam.getResX() - nameWidth) /2 + nameWidth + charWidth, (int) (cam.getResY() * 0.2));
		
		for(int i = 0; i < choices.length; i++) {
			int titleWidth; 
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
