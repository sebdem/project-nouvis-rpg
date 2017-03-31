package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.ISprite;
import sebdem.nouvis.graphics.NouvGraphics;

public abstract class EntityBase {
	
	public Vec2 position;
	
	public Vec2 size;
	
	public ISprite sprite;
	
	public EntityBase(){
		
	}
	
	public abstract void update(long elapsedTime);
	

	public abstract void draw(NouvGraphics g);
	
}
