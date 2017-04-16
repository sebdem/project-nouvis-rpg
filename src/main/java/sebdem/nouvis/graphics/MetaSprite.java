package sebdem.nouvis.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.Resource;
import sebdem.nouvis.utils.ImageUtils;

public class MetaSprite implements ISprite {

	protected Sprite[] corners;

	public MetaSprite() {
		corners = new Sprite[4];

	}

	public MetaSprite(ISprite source) {
		corners = new Sprite[4];
		
		BufferedImage src = source.get();
		Vec2 dim = source.getDim();
		int width = (int)dim.x;
		int height = (int)dim.y;
		int halfw = width / 2;
		int halfh = height / 2;
		setTopLeft(new Sprite(src.getSubimage(			0, 		0, halfw, halfh)));
		setTopRight(new Sprite(src.getSubimage(		halfw, 		0, halfw, halfh)));
		setBottomLeft(new Sprite(src.getSubimage(		0, 	halfh, halfw, halfh)));
		setBottomRight(new Sprite(src.getSubimage(	halfw, 	halfh, halfw, halfh)));
		src = null;

	}

	public void setTopLeft(Sprite sprite) {
		this.corners[0] = sprite;
		this.updatePending = true;
	}

	public Sprite getTopLeft() {
		return corners[0];
	}

	public void setTopRight(Sprite sprite) {
		this.corners[1] = sprite;
		this.updatePending = true;
	}

	public Sprite getTopRight() {
		return corners[1];
	}

	public void setBottomLeft(Sprite sprite) {
		this.corners[2] = sprite;
		this.updatePending = true;
	}

	public Sprite getBottomLeft() {
		return corners[2];
	}

	public void setBottomRight(Sprite sprite) {
		this.corners[3] = sprite;
		this.updatePending = true;
	}

	public Sprite getBottomRight() {
		return corners[3];
	}

	
	private BufferedImage combined = null;
	private boolean updatePending = true;
	
	public BufferedImage get() {
		if (combined == null || updatePending){
			combined =  ImageUtils.fuse(
					new BufferedImage[][] { 
						{ this.getTopLeft().texture, getTopRight().texture }, 
						{ this.getBottomLeft().texture, getBottomRight().texture } 
					});
			updatePending = false;
		}
		return combined;
	}

	public BufferedImage tint(Color c) {
		return this.tint(c.getRGB());
	}

	public BufferedImage tint(int c) {
		for(Sprite s : corners){
			s.tint(c);
		}
		this.updatePending = true;
		return get();
	}

	public Vec2 getDim() {
		// TODO Auto-generated method stub
		return this.corners[0].getDim().multiplyNew(2);
	}

	public Vec2 getEffDim() {
		// TODO Auto-generated method stub
		return null;
	}

	public Resource getResource()
	{
		return null;
	}

}
