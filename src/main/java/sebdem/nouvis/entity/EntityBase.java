package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.ISprite;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;
import sebdem.nouvis.world.WorldSpace;

public abstract class EntityBase {
	
	public Vec2 position;
	
	public Vec2 size;
	
	public ISprite sprite;
	
	public WorldSpace world;
	
	public EntityBase(){
		this(new Vec2(1,1), new Vec2(1,1));
	}
	public EntityBase(Vec2 position, Vec2 size){
		this.position = position;
		this.size = size;
	}
	
	public abstract void update(long elapsedTime);
	

	public abstract void draw(NouvGraphics g);

	public abstract void draw(NouvGraphics g, Camera camera, Vec2 upscalse);
	
	
	public ISprite getSprite(){
		return this.sprite;
	}

}
