package sebdem.nouvis.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.Resource;
import sebdem.nouvis.resource.SpriteResource;

public class MetaSheet {

	protected BufferedImage sheet;
	protected Vec2 spriteDim;

	public Sprite[] topLeft;
	public Sprite[] topRight;
	public Sprite[] bottomLeft;
	public Sprite[] bottomRight;

	public MetaSheet(Resource sheet, Vec2 spriteDim) {
		try
		{
			this.spriteDim = spriteDim;
			this.sheet = ImageIO.read(sheet.load());
		} catch (IOException e)
		{
			System.out.println("Failed to load " + sheet.fullPath());

			e.printStackTrace();
		}
	}

	public void construct()
	{
		topLeft = new Sprite[6];
		topRight = new Sprite[6];
		bottomLeft = new Sprite[6];
		bottomRight = new Sprite[6];
		Sprite temp = null;
		int index = 0;
		int irow = 0;

		for (int y = 0; y < 4; y++)
		{
			irow = (int) (3 * Math.floor(y / 2));
			for (int x = 0; x < 6; x++)
			{
				temp = new Sprite(sheet.getSubimage(
							(int) (x * spriteDim.x), 
							(int) (y * spriteDim.y), 
							(int) (spriteDim.x),
							(int) (spriteDim.y)
						));

				index = irow + (int) (Math.floor(x / 2));
				if (x % 2 == 0)
				{
					if (y % 2 == 0)
						topLeft[index] = temp;
					else
						bottomLeft[index] = temp;
				} else
				{
					if (y % 2 == 0)
						topRight[index] = temp;
					else
						bottomRight[index] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		MetaSheet dirtToGrass = new MetaSheet(new SpriteResource("tiles", "water_grass.png"), new Vec2(8,8));
		dirtToGrass.construct();
		MetaSprite ms;
		
		for(int i1 = 0; i1 < 6; i1 ++){
			for(int i2 = 0; i2 < 6; i2 ++){
				for(int i3 = 0; i3 < 6; i3 ++){
					for(int i4 = 0; i4 < 6; i4 ++){
						ms = new MetaSprite();
						ms.setTopLeft(dirtToGrass.bottomLeft[i1]);
						ms.setTopRight(dirtToGrass.bottomRight[i2]);
						ms.setBottomLeft(dirtToGrass.bottomLeft[i3]);
						ms.setBottomRight(dirtToGrass.bottomRight[i4]);
						
						ImageIO.write(ms.get(), "png", new File("metatest/output_"+i1+"_"+i2+"_"+i3+"_"+i4+".png"));
					}
				}
			}
		}
	}
}
