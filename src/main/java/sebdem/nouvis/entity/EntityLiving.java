package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;

public class EntityLiving extends EntityBase {

	public Vec2 movevec;
	
	public EntityLiving(Vec2 position, Vec2 size){
		super(position, size);
		this.movevec = new Vec2(0,0);
	}
	
	@Override
	public void update(long elapsedTime)
	{
		move();
	}

	public void move()
	{
		if (this.movevec != null)
			this.position.addTo(movevec);
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
