package sebdem.nouvis.world;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Timer;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.ISprite;
import sebdem.nouvis.graphics.MetaSheet;
import sebdem.nouvis.graphics.MetaSprite;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.utils.ImageUtils;
import sebdem.nouvis.world.generation.WorldGenerator;

public class Tile {

	protected int id;
	protected boolean walkable;

	protected Sprite baseTexture;

	protected HashMap<Integer, MetaSheet> connectsTo;

	
	
	public Tile() {
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the walkable
	 */
	public boolean isWalkable()
	{
		return walkable;
	}

	/**
	 * @param walkable the walkable to set
	 */
	public void setWalkable(boolean walkable)
	{
		this.walkable = walkable;
	}

	/**
	 * @return the baseTexture
	 */
	public Sprite getBaseTexture()
	{
		return baseTexture;
	}

	
	/**
	 * @param baseTexture the baseTexture to set
	 */
	public void setBaseTexture(Sprite baseTexture)
	{
		this.baseTexture = baseTexture;
	}
	
	/**
	 * @return the connectsTo
	 */
	public HashMap<Integer, MetaSheet> getConnectsTo()
	{
		return connectsTo;
	}

	/**
	 * @param connectsTo the connectsTo to set
	 */
	public void setConnectsTo(HashMap<Integer, MetaSheet> connectsTo)
	{
		this.connectsTo = connectsTo;
	}
	
	
	public ISprite getSprite(WorldSpace world, Vec2 inWorldPosition)
	{
		// TODO: Make MetaData implementation easier. ._.
		ISprite s = baseTexture;
		/*
		if (this.connectsTo != null && this.connectsTo.size() > 0)
		
		{
			s = new MetaSprite(baseTexture);

			int tul = world.terrain.getTile(inWorldPosition.addNew(-1, -1));
			int tum = world.terrain.getTile(inWorldPosition.addNew(0, -1));
			int tur = world.terrain.getTile(inWorldPosition.addNew(1, -1));

			int tml = world.terrain.getTile(inWorldPosition.addNew(-1, 0));
			int tmr = world.terrain.getTile(inWorldPosition.addNew(1, 0));

			int tbl = world.terrain.getTile(inWorldPosition.addNew(-1, 1));
			int tbm = world.terrain.getTile(inWorldPosition.addNew(0, 1));
			int tbr = world.terrain.getTile(inWorldPosition.addNew(1, 1));

			int ul = 4, 
				ur = 3,
				bl = 1, 
				br = 0;
			
			MetaSheet sheet = null;//(MetaSheet) this.connectsTo.values().toArray()[0];
			boolean b1,b2,b3;
			
			// Top Left - Default: 4
			{
				b1 = !this.connectsTo.containsKey(tul);//isSelf(tul);
				b2 = !this.connectsTo.containsKey(tum);//isSelf(tum);
				b3 = !this.connectsTo.containsKey(tml);//isSelf(tml);
				
				if (b2 && b3){
					if (b1)
						ul = 4;
					else {
						ul = 2;
						if (this.connectsTo.containsKey(tul))
							sheet = this.connectsTo.get(tul);
					}
				} else if (!b2 && b3){
					ul = 1;
					if (this.connectsTo.containsKey(tum))
						sheet = this.connectsTo.get(tum);
				} else if (b2 && !b3){
					ul = 3;
					if (this.connectsTo.containsKey(tml))
						sheet = this.connectsTo.get(tml);
				} else if (!b2 && !b3){
					ul = 0;
					if (this.connectsTo.containsKey(tml))
						sheet = this.connectsTo.get(tml);
				} else {
					ul = 4;
					if (this.connectsTo.containsKey(tul))
						sheet = this.connectsTo.get(tul);
				}
			}
			
			if (sheet != null) {
				((MetaSprite)s).setTopLeft(sheet.topLeft[ul]);
			} 
			sheet = null;
			
			// Top Right - Default: 3
			{
				b1 = !this.connectsTo.containsKey(tur);//isSelf(tur);
				b2 = !this.connectsTo.containsKey(tum);//isSelf(tum);
				b3 = !this.connectsTo.containsKey(tmr);//isSelf(tmr);
				
				if (b2 && b3){
					if (b1)
						ur = 3;
					else {
						ur = 2;
						if (this.connectsTo.containsKey(tur))
							sheet = this.connectsTo.get(tur);
					}
				} else if (!b2 && b3){
					ur = 0;
					if (this.connectsTo.containsKey(tum))
						sheet = this.connectsTo.get(tum);
				} else if (b2 && !b3){
					ur = 4;
					if (this.connectsTo.containsKey(tmr))
						sheet = this.connectsTo.get(tmr);
				} else if (!b2 && !b3){
					ur = 1;
					if (this.connectsTo.containsKey(tmr))
						sheet = this.connectsTo.get(tmr);
				} else {
					ur = 3;
					if (this.connectsTo.containsKey(tur))
						sheet = this.connectsTo.get(tur);
				}
			}

			if (sheet != null) {
				((MetaSprite)s).setTopRight(sheet.topRight[ur]);
			} 
			sheet = null;
			
			// Bottom Left - Default: 1
			{
				b1 = !this.connectsTo.containsKey(tbl);//isSelf(tbl);
				b2 = !this.connectsTo.containsKey(tbm);//isSelf(tbm);
				b3 = !this.connectsTo.containsKey(tml);//isSelf(tml);
				
				if (b2 && b3){
					if (b1)
						bl = 1;
					else {
						bl = 2;
						if (this.connectsTo.containsKey(tbl))
							sheet = this.connectsTo.get(tbl);
					}
				} else if (!b2 && b3){
					bl = 4;
					if (this.connectsTo.containsKey(tbm))
						sheet = this.connectsTo.get(tbm);
				} else if (b2 && !b3){
					bl = 0;
					if (this.connectsTo.containsKey(tml))
						sheet = this.connectsTo.get(tml);
				} else if (!b2 && !b3){
					bl = 3;
					if (this.connectsTo.containsKey(tml))
						sheet = this.connectsTo.get(tml);
				} else {
					bl = 1;
					if (this.connectsTo.containsKey(tbl))
						sheet = this.connectsTo.get(tbl);
				}
			}

			if (sheet != null) {
				((MetaSprite)s).setBottomLeft(sheet.bottomLeft[bl]);
			} 
			sheet = null;
			
			// Bottom Right - Default: 0
			{
				b1 = !this.connectsTo.containsKey(tbr);//isSelf(tbr);
				b2 = !this.connectsTo.containsKey(tbm);//isSelf(tbm);
				b3 = !this.connectsTo.containsKey(tmr);//isSelf(tmr);
				
				if (b2 && b3){
					if (b1)
						br = 0;
					else {
						br = 2;
						if (this.connectsTo.containsKey(tbr))
							sheet = this.connectsTo.get(tbr);
					}
				} else if (!b2 && b3){
					br = 3;
					if (this.connectsTo.containsKey(tbm))
						sheet = this.connectsTo.get(tbm);
				} else if (b2 && !b3){
					br = 1;
					if (this.connectsTo.containsKey(tmr))
					sheet = this.connectsTo.get(tmr);
				} else if (!b2 && !b3){
					br = 4;
					if (this.connectsTo.containsKey(tmr))
						sheet = this.connectsTo.get(tmr);
				} else {
					br = 0;
					if (this.connectsTo.containsKey(tbr))
						sheet = this.connectsTo.get(tbr);
				}
			}

			if (sheet != null) {
				((MetaSprite)s).setBottomRight(sheet.bottomRight[br]);
			} 
			sheet = null;
		}
		*/
		return s;
	}


	public void drawTile(NouvGraphics graph, Rectangle dest, WorldSpace world, Vec2 inWorldPosition)
	{
		graph.draw(this.getSprite(world, inWorldPosition), dest);
	}

	public void drawTile(NouvGraphics graph, Rectangle dest, WorldSpace world, Vec2 inWorldPosition, Color tint)
	{
		graph.draw(this.getSprite(world, inWorldPosition), dest, tint);
	}


	
	public static class TileJson{
		protected int id;
		protected boolean walkable;

		protected String baseTexture;

		protected MetaMapping[] connectsTo;
		
		private class MetaMapping{
			protected int id;
			protected String texture;
		}
		
		public Tile createTile(){
			// TODO: Metatile based on Tile.json configuration
			// TODO: Load Resources from a more-or-less global(singleton?) class 
			
			Tile tile = new Tile();
			tile.setId(id);
			tile.setWalkable(walkable);
			tile.setBaseTexture(new Sprite(new SpriteResource("tiles", baseTexture+".png")));
			
			if (connectsTo != null && connectsTo.length > 0) {
				tile.setConnectsTo(new HashMap<Integer, MetaSheet>());
				
				MetaSheet metatilesheet = null;
				for(MetaMapping mapping : connectsTo){
					metatilesheet = new MetaSheet(new SpriteResource("tiles", mapping.texture+".png"), new Vec2(8,8));
					metatilesheet.construct();
					tile.connectsTo.put(mapping.id, metatilesheet);
				}
			}
			return tile;
			
		}
		
	}

}
