package sebdem.nouvis.entity;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;
import sebdem.nouvis.world.Tile;

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
		if (this.movevec != null){
			if (canMoveTo(this.position.addNew(movevec)))
				this.position.addTo(movevec);
		}
	}
	
	public boolean canMoveTo(Vec2 target){
		return !(	this.world.isPosOccupied(target) 
				 || this.world.isPosOccupied(target.addNew(size.x, 0))
				 || this.world.isPosOccupied(target.addNew(0, size.y))
				 || this.world.isPosOccupied(target.addNew(size)));
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
