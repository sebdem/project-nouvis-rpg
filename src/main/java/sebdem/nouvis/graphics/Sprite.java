package sebdem.nouvis.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.Resource;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.utils.ColorUtils;
import sebdem.nouvis.utils.ImageUtils;

public class Sprite implements ISprite{

	protected BufferedImage texture;
	protected Resource resource;
	
	
	public Sprite(SpriteResource resource) {

		this.resource = resource;
		try {
			setTexture(ImageIO.read(resource.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite(BufferedImage texture) {
		setTexture(texture);
	}
	
	protected void setTexture(BufferedImage texture){
		this.texture = texture;
	}

	public BufferedImage get() {
		return texture;
	}

	public Vec2 getDim() {
		return new Vec2(this.texture.getWidth(), this.texture.getHeight());
	}

	public Vec2 getEffDim() {
		Vec2 dim = getDim();

		return dim;
	}

	public BufferedImage tint(Color c) {
		return tint(c.getRGB());
	}

	public BufferedImage tint(int c) {
		int w = texture.getWidth();
		int h = texture.getHeight();
		BufferedImage tinted = ImageUtils.clone(texture);

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				tinted.setRGB(x, y, ColorUtils.multiplyColor(tinted.getRGB(x, y), c));
			}
		}
		return tinted;
	}

	public Resource getResource()
	{
		return resource;
	}
	public void setResource(Resource res)
	{
		resource =  res;
	}


}
