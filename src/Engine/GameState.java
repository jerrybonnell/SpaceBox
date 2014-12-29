package Engine;
import java.awt.Graphics2D;


public abstract class GameState {
	
	protected GameEngine game;
	
	public GameState(GameEngine game) {
		this.game = game;
	}
	
	public abstract void update(InputHandler input, double tpf);
	public abstract void render(Graphics2D g, Camera cam, AssetHandler assets);

}
