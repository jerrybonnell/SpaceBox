import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Engine.AssetHandler;
import Engine.Camera;
import Engine.Entity;
import Engine.InputHandler;

public class Stage {

	private ArrayList<Entity> enemies;
	private ArrayList<Wall> horizontalWalls;
	private Wall bottomWall, topWall, leftWall, rightWall;
	private Glider player;
	private double totalHeight;
	private double totalWidth;
	private double roomHeight;
	private double time;
	private int numLevels;
	private int numLives;
	private int currentLevel;

	public Stage(double width, double height, int numLevels) {
		setStage(width, height, numLevels);
		numLives = 3;
		currentLevel = 0;
		player = new Glider(1, 1, 2, 2);
		time = 0;
	}

	public void loadStage(File levelFile) {
		player = new Glider(1, 1, 0, 2);
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(levelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner ls = new Scanner(scanner.nextLine());
		setStage(ls.nextDouble(), ls.nextDouble(), ls.nextInt());

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			ls = new Scanner(line);
			int id = ls.nextInt();
			// adding a regular rectangle
			if (id == 0)
				enemies.add(new Entity(ls.nextDouble(), ls.nextDouble(), ls
						.nextDouble(), ls.nextDouble()));
			// adding a Mr. Freeze enemy
			else if (id == IDList.MR_FREEZE) {
				MrFreeze mr = new MrFreeze(ls.nextDouble(), ls.nextDouble(),
						ls.nextDouble(), ls.nextDouble());
				mr.setDisplacement(ls.nextDouble(), ls.nextDouble());
				mr.setPeriod(ls.nextDouble());
				enemies.add(mr);
			}
			// adding a Nonchalance enemy
			else if (id == IDList.NONCHALANCE) {
				Nonchalance non = new Nonchalance(ls.nextDouble(),
						ls.nextDouble(), ls.nextDouble(), ls.nextDouble());
				non.setRadius(ls.nextDouble());

			}
			// adding a Sir Bologna enemy
			else if (id == IDList.SIR_BOLOGNA) {
				SirBologna sir = new SirBologna(ls.nextDouble(),
						ls.nextDouble(), ls.nextDouble(), ls.nextDouble());
				sir.setDisplacement(ls.nextDouble(), ls.nextDouble());
				enemies.add(sir);
			}
		}
	}

	public void setStage(double width, double height, int numLevels) {
		totalHeight = height;
		totalWidth = width;
		this.numLevels = numLevels;
		roomHeight = totalHeight / numLevels;
		enemies = new ArrayList<Entity>();
		horizontalWalls = new ArrayList<Wall>();
		bottomWall = new Wall(totalWidth, 1, 0, 0);
		topWall = new Wall(totalWidth, 1, 0, totalHeight);
		rightWall = new Wall(1, totalHeight, totalWidth / 2, totalHeight / 2);
		leftWall = new Wall(1, totalHeight, -totalWidth / 2, totalHeight / 2);

		for (int i = 0; i < numLevels; i++) {
			double x = 0;
			if (i % 2 == 0)
				x = -2.5;
			else
				x = 2.5;
			Wall wall = new Wall(totalWidth - 5, 1, x, i * roomHeight);
			horizontalWalls.add(wall);
		}
	}

	public void update(InputHandler input, double tpf) {
		for (Entity e : enemies) {
			e.update(input, tpf);
		}
		player.update(input, tpf);
		currentLevel = (int) (player.y / roomHeight);

		boolean died = false;
		time += tpf;
		// Extreme border collision
		died = player.collides(leftWall) != null
				|| player.collides(rightWall) != null
				|| player.collides(bottomWall) != null
				|| player.collides(topWall) != null;
		for (Wall wall : horizontalWalls)
			died = player.collides(wall) != null || died;
		
		for (Entity enemy : enemies)
			died = player.collides(enemy) != null || died;

		if (died) {
			numLives--;
			player.y = roomHeight * currentLevel + 2;
			player.x = leftWall.x + 19;
			player.vx = 0;
			player.vy = 0;
		}
	}
	
	public boolean wasWon() {
		return false;
	}
	
	public boolean wasLost() {
		return (numLives <= 0);
	}

	public void render(Graphics2D g, Camera cam, AssetHandler assets) {
		cam.setWidth(55);
		cam.setX((rightWall.x + leftWall.x) / 2);
		//cam.setY(19 + roomHeight * currentLevel);
		cam.setY(19 + roomHeight * 1);
		int resX = cam.getResX();
		int resY = cam.getResY();
		for(int i = 0; i < 1020; i++) {
			g.setColor(new Color((i *240) % 255, (i + 150) % 255, (i * 100) % 255));
			g.fillOval((int)((i % 5 + 2) * time * 0.6*resX) % resX, i * 30, 5 , 5);
		   
		}
		for (Entity e : horizontalWalls)
			e.render(g, cam, assets);
		for (Entity enemy : enemies)
			enemy.render(g, cam, assets);
		for (int i = 0; i < numLives; i++)
			g.drawImage(assets.getImage("glider"), 10 + 30 * i, 10, 20, 20,
					null);
		bottomWall.render(g, cam, assets);
		topWall.render(g, cam, assets);
		rightWall.render(g, cam, assets);
		leftWall.render(g, cam, assets);
		player.render(g, cam, assets);
	}
}
