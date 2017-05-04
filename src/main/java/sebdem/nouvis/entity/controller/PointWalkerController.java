package sebdem.nouvis.entity.controller;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.EntityLiving;

public class PointWalkerController extends EntityController{

	public Vec2 destination;
	
	
	public PointWalkerController(EntityLiving entity) {
		super(entity);
		 
		// TODO Auto-generated constructor stub
	}
	
	protected void onDestinationReached(){
		this.unregister();
	}
	
	public void update(long elapsedTime){
		Vec2 epos = this.entity.position;
		if (destination == null){
			destination = getNewTarget(24);
		}
		else if ( destination.equals(epos)){
			onDestinationReached();
		}
		else {
			float speed = this.entity.maxVelocity;
			float movex = destination.x - epos.x;
			if (Math.abs(movex) > speed){
				movex = (movex > 0) ? speed : (movex < 0) ? -speed : 0;
			}
			float movey = destination.y - epos.y;
			if (Math.abs(movey) > speed){
				movey = (movey> 0) ? speed : (movey < 0) ? -speed : 0;
			}
			
			if(this.entity.canMoveTo(epos.addNew(movex, movey))){
				this.entity.movevec.x = movex;
				this.entity.movevec.y = movey;
			} else {
				destination = getNewTarget(24);
				//this.unregister();
			}
			
		}
	}
	
	protected Vec2 getNewTarget(int range){
		return this.entity.world.randomPntInRange(this.entity.position, range);
	}
	
}
