package Engine;

public class Collision {
	
	private Entity a; 
	private Entity b; 
	private Direction normal;
	private double penetration;
	
	public Collision(Entity a, Entity b, Direction normal, double penetration) {
		this.a = a;
		this.b = b;
		this.normal = normal;
		this.penetration = penetration;
		
	}
	
	public Entity getA() {
		return a;
	}
	public Entity getB() {
		return b;
	}
	public Direction getNormal() {
		return normal;
	}
	public double getPenetration() {
		return penetration;
	} 
	
	

}
