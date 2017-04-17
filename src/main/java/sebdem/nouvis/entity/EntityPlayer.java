package sebdem.nouvis.entity;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.world.Camera;

public class EntityPlayer extends EntityLiving {

	public EntityPlayer(){
		this(new Vec2(1,1), new Vec2(1,1));
	}

	public EntityPlayer(Vec2 position, Vec2 size) {
		super(position, size);
		this.sprite = new Sprite(new SpriteResource("player", "debug.png")).getSubSprite(0, 0, 16, 16);
	}
	
	
}
