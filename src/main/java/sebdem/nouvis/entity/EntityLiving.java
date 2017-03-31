package sebdem.nouvis.entity;

import sebdem.nouvis.graphics.NouvGraphics;

public class EntityLiving extends EntityBase {

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

}
