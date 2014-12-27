package Engine;
import java.util.ArrayList;


public abstract class EntitySystem {
	
	protected ArrayList<Entity> entities;
	
	public EntitySystem(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public abstract void update(double tpf);

}
