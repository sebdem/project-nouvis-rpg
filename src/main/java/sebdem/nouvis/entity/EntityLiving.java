package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;

public class EntityLiving extends EntityBase {

	
	public EntityLiving(Vec2 position, Vec2 size){
		super(position, size);
	}
	
	@Override
	public void update(long elapsedTime)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(NouvGraphics g)
	{
		g.draw(this.sprite, this.position);
	}

	
	public Vec2 lastDrawPos;
	
	@Override
	public void draw(NouvGraphics g, Camera camera, Vec2 upscalse)
	{
		lastDrawPos = this.position.substractNew(camera.position).multiplyNew(upscalse.x);
		g.draw(this.sprite, lastDrawPos, upscalse);
		
	}

}
