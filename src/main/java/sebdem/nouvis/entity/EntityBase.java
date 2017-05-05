package sebdem.nouvis.entity;

import java.awt.geom.Rectangle2D;

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
	
	public Vec2 renderCenter;
	
	public EntityBase(){
		this(Vec2.one(), Vec2.one());
	}
	public EntityBase(Vec2 position, Vec2 size){
		this.position = position;
		this.size = size;
		this.renderCenter =Vec2.zero();
	}
	
	public abstract void update(long elapsedTime);
	

	public abstract void draw(NouvGraphics g);

	public abstract void draw(NouvGraphics g, Camera camera, Vec2 upscalse);
	
	
	public ISprite getSprite(){
		return this.sprite;
	}
	public Rectangle2D.Float getBounds() {
		return this.position.createRectSize(this.size);
	}

}
