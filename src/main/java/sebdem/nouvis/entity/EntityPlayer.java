package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.utils.RandomUtils;
import sebdem.nouvis.world.Camera;

public class EntityPlayer extends EntityLiving {

	public EntityPlayer(){
		this(new Vec2(1,1), new Vec2(1,1));
	}

	public EntityPlayer(Vec2 position, Vec2 size) {
		super(position, size);
		this.sprite = new Sprite(new SpriteResource("player", "debug.png"));
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		/*if (destination == null || destination.equals(this.position)){
			destination = this.world.randomPntInRange(this.position, 40);
		}
		else {
			float speed = 0.125f;
			float movex = destination.x - this.position.x;
			if (Math.abs(movex) > speed){
				movex = (movex > 0) ? speed : (movex < 0) ? -speed : 0;
			}
			float movey = destination.y - this.position.y;
			if (Math.abs(movey) > speed){
				movey = (movey> 0) ? speed : (movey < 0) ? -speed : 0;
			}
			this.movevec.x = movex;
			this.movevec.y = movey;
		}*/
	}
	
	@Override
	public void draw(NouvGraphics g, Camera camera, Vec2 upscalse)
	{
		lastDrawPos = this.position.substractNew(camera.position).multiplyNew(upscalse.x);
		int sx = 0, sy = 0;

			if(this.movevec.x >= 0.075f){
				sy = 2;
			}
			else if(this.movevec.x <= -0.075f){
				sy = 1;
			}
			if(this.movevec.y >= 0.075f){
				sx = 1;
			}
			else if(this.movevec.y <= -0.075f){
				sx = 2;
			} 

		Sprite s = new Sprite(new SpriteResource("player", "debug.png")).getSubSprite(sx, sy, 16, 16);
		g.draw(s, lastDrawPos, upscalse);
		
	}
	
}
