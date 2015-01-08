import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.GameEngine;
import Engine.GameState;
import Engine.InputHandler;

public class ScoreState extends GameState {

	public class Player {
		public String name;
		public int score;
	}

	private String[] choices;
	private int currentChoice;
	private ArrayList<Player> scoreBoard = new ArrayList<Player>();
	private Scanner scan;
	private int currentPlayer;
	private double time;

	private double pos, vel;

	public ScoreState(GameEngine game) {
		super(game);
		currentPlayer = 0;
		choices = new String[1];
		choices[0] = "Back";
		vel = 50; 
		File file = new File("score.dat");
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			Player p = new Player();
			p.name = scan.next();
			p.score = scan.nextInt();
			scoreBoard.add(p);
			scan.nextLine();
		}

		Collections.sort(scoreBoard, new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				return o2.score - o1.score;
			}
		});
		time = 0;
	}

	public void update(InputHandler input, double tpf) {
		pos += vel * tpf;
		time += tpf;
		if (input.down.updatesPressed() == 1) {
			currentChoice++;
			currentChoice %= choices.length;
		}
		if (input.up.updatesPressed() == 1) {
			currentChoice--;
			if (currentChoice < 0)
				currentChoice = choices.length - 1;
		}
		
		vel += 42 * tpf;
		if (vel > 100)
			vel = 100; 

		if (input.enter.updatesPressed() == 1) {
			switch (currentChoice) {
			case (0):
				game.getAssets().stopAllMusic();
				game.getAssets().getMusic("general").play(true);
				game.popState();
				break;
			default:
				System.out.println("ERROR: Null menu option.");
				break;
			}
		}

	}

	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, cam.getResX(), cam.getResY());
		
		g.drawImage(assets.getImage("glider"), 400 + (int)(400 * Math.sin(time)), 300 + (int)(300 * Math.cos(time)), 50, 50, null);
		g.drawImage(assets.getImage("enemy1"), 400 + (int)(400 * Math.sin(time - 1)), 300 + (int)(300 * Math.cos(time - 1)),50, 50, null);
		if (time > 10) {
			g.drawImage(assets.getImage("enemy3"), 400 + (int)(400 * Math.sin(time - 2)), 300 + (int)(300 * Math.cos(time - 2)),50, 50, null);
		}
		if (time > 20) {
			g.drawImage(assets.getImage("enemy2"), 400 + (int)(400 * Math.sin(time - 3)), 300 + (int)(300 * Math.cos(time - 3)),50, 50, null);
		}
		
		int titleWidth;
		g.setFont(new Font("Courier New", 1, 70));
		for (int i = 0; i < scoreBoard.size(); i++) {
			Player p = scoreBoard.get(i);
			titleWidth = g.getFontMetrics().stringWidth(p.name + " " + p.score);

			int pos_i = (int) pos + i * 150;
			if (pos_i > 0)
				pos_i %= (150 + 20) * scoreBoard.size();// cam.getResY();
			else
				while (pos_i < 0)
					pos_i += (150 + 20) * scoreBoard.size();// cam.getResY();
			switch (i) {
			case (0) :
				g.setColor(Color.YELLOW);
				break;
			case (1) :
				g.setColor(Color.GRAY);
				break;
			case (2) :
				g.setColor(Color.ORANGE);
				break;
			default:
				g.setColor(Color.WHITE);
			}
			
			g.drawString(p.name + " " + p.score,
					(cam.getResX() - titleWidth) / 2, pos_i);
		}
		// Player player = scoreBoard.get(currentPlayer);

		for (int i = 0; i < choices.length; i++) {
			if (currentChoice == i) {
				g.setFont(new Font("Courier New", 1, 40));
				titleWidth = g.getFontMetrics().stringWidth(
						"> " + choices[i] + " <");
				g.drawString("> " + choices[i] + " <",
						(cam.getResX() - titleWidth) / 2,
						(int) (cam.getResY() * (0.2 * (i + 2))));

			} else {
				g.setFont(new Font("Courier New", 0, 40));
				titleWidth = g.getFontMetrics().stringWidth(choices[i]);
				g.drawString(choices[i], (cam.getResX() - titleWidth) / 2,
						(int) (cam.getResY() * (0.2 * (i + 2))));

			}
		}
		
		if (Stage.score == scoreBoard.get(0).score) {
			titleWidth = g.getFontMetrics().stringWidth("New High Score!");
			for(int i = 0; i < 100; i++) {
				//double temp = time;
				//temp %= 1;
				//if(temp < 0.5)
				//	continue;
				g.setColor(new Color((int) (Math.random() * Integer.MAX_VALUE)));
				g.drawString("New High Score!", (int)(cam.getResX() - titleWidth) / 2 , (int) (0.2 * cam.getResY()));
			}
		}
		
		
	}

}
