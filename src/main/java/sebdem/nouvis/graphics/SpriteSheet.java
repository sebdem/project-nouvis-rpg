package sebdem.nouvis.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.Resource;

public class SpriteSheet {

	protected BufferedImage sheet;
	protected Vec2 spriteDim;
	protected Vec2 sheetDim;

	protected Sprite[] elements;

	public SpriteSheet(Resource sheet, Vec2 spriteDim) {
		this.spriteDim = spriteDim;
		try {
			
			this.sheet = ImageIO.read(sheet.load());
		} catch (IOException e) {
			System.out.println("Failed to load " + sheet.fullPath());

			e.printStackTrace();
		}
	}

	public Sprite[] construct() {
		int sprWidth = (int) spriteDim.x;
		int sprHeight = (int) spriteDim.y;
		
		int row = (int) (this.sheet.getHeight()  / spriteDim.y);
		int col = (int) (this.sheet.getWidth()  / spriteDim.x);
		this.sheetDim = new Vec2(col, row);
		
		this.elements = new Sprite[col * row ];
		
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				elements[(int) (x + y * col)] = new Sprite(
						sheet.getSubimage(x * sprWidth, y * sprHeight, sprWidth, sprHeight));
			}
		}
		return elements;
	}

	public Sprite[] getSprites() {
		return this.elements;
	}

	public Sprite getSpriteAt(Vec2 pos) {
		return getSpriteAt((int)pos.x, (int)pos.y);
	}
	public Sprite getSpriteAt(int x, int y) {
		if (elements == null)
			this.construct();
		return this.elements[(int) (x + y * sheetDim.x)];
	}
}
