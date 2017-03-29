package sebdem.nouvis.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.Resource;

public interface ISprite {


	public Resource getResource();
	public BufferedImage get();
	public BufferedImage tint(Color c);
	public BufferedImage tint(int c);
	
	public Vec2 getDim();
	public Vec2 getEffDim();
}
