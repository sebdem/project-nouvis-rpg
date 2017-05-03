package sebdem.nouvis.entity.controller;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.EntityLiving;

public class DestinationWalkerController extends EntityController{

	public Vec2 destination;
	
	
	public DestinationWalkerController(EntityLiving entity) {
		super(entity);
		 
		// TODO Auto-generated constructor stub
	}
	
	public void update(long elapsedTime){
		Vec2 epos = this.entity.position;
		if (destination == null){
			destination = this.entity.world.randomPntInRange(epos, 40);
		}
		else if ( destination.equals(epos)){
			this.unregister();
		}
		else {
			float speed = 0.125f;
			float movex = destination.x - epos.x;
			if (Math.abs(movex) > speed){
				movex = (movex > 0) ? speed : (movex < 0) ? -speed : 0;
			}
			float movey = destination.y - epos.y;
			if (Math.abs(movey) > speed){
				movey = (movey> 0) ? speed : (movey < 0) ? -speed : 0;
			}
			this.entity.movevec.x = movex;
			this.entity.movevec.y = movey;
		}
	}
	
}
