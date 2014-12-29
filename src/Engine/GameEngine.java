package Engine;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Stack;

import javax.swing.JFrame;


public class GameEngine {
	
	private JFrame frame;
	private Canvas canvas; 
	private Camera cam;
	private AssetHandler assets; 
	private InputHandler input;
	private boolean running = false;
	private String title;
	
	private Stack<GameState> states = new Stack<GameState>();
	
	public GameEngine(String title, int resX, int resY) {
		cam = new Camera(resX, resY);
		this.title = title;
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make it so the the exit button ends the programm
		frame.setFocusable(true); //Allows the frame to get focus
		frame.setResizable(false); //does not allow user to resize window
		
		canvas = new Canvas(); //Where we ill draw
		canvas.setPreferredSize(new Dimension(resX, resY));
		canvas.setMaximumSize(new Dimension(resX, resY));
		canvas.setMinimumSize(new Dimension(resX, resY));
		
		frame.add(canvas); //Add the canvas to frame
		frame.pack(); //Make the frame the same size of the canvas
		frame.setLocationRelativeTo(null); //Put the window in the center of the screen
		frame.setVisible(true); //Make the window visible
		canvas.createBufferStrategy(2); //Use a buffer strategy in the canvas to prevent screen tearing
		
		input = new InputHandler(frame);
		assets = new AssetHandler(); 
	}

	public void start() {
		running = true;
		run();
	}
	
	public void run() {
		double tpf = 1/60.0f;
		double secondCounter = 0;
		long before , after;
		
		while(running) {
			before = System.currentTimeMillis();
			update(tpf); //date game logic
			if(!running) {
				break; 
			}
			render(); //Render game to screen
			after= System.currentTimeMillis();
			
			tpf = (after - before) / 1000.0; //Time per game loop
			secondCounter += tpf; //Adds to a counter that resets every second
			
			if(secondCounter >= 1) { //If the second counter passes, or is, a second
				secondCounter = 0; //reset it to zero
				frame.setTitle(title + " FPS: " + (int)(1/tpf)); //update the games title 
			}
		}
	}
	
	public void update(double tpf) {
		frame.requestFocus();
		input.update(tpf);
		
		if(!states.empty())
			states.peek().update(input, tpf);
	}
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy(); //get the buffer strategy
		Graphics2D g = (Graphics2D) bs.getDrawGraphics(); //Cast the graphics to 2D to get more methods
		
		if(!states.isEmpty())
			states.peek().render(g, cam, assets);
		
		g.dispose(); //Free up the graphics memory
		bs.show(); //show what you drew
	}
	
	public void stop() {
		running = false;
		frame.dispose();
	}
	
	public AssetHandler getAssets() {
		return assets; 
	}
	
	public void pushState(GameState state) {
		states.push(state);
	}
	
	public GameState popState() {
		return states.pop();
	}
	
	public Camera getCamera() {
		return cam; 
	}

}
