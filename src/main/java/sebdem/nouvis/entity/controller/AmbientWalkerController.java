package sebdem.nouvis.entity.controller;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.EntityLiving;

public class AmbientWalkerController extends PointWalkerController {

	public AmbientWalkerController(EntityLiving entity) {
		super(entity);
	}
	
	@Override
	protected void onDestinationReached(){
		destination = getNewTarget(24);
	}

	
	@Override
	protected Vec2 getNewTarget(int range){
		if (Math.random()*100 < 2.5){
			return this.entity.world.randomPntInRange(this.entity.position, range);
		}
		return null;
	}
}
