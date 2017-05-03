package sebdem.nouvis.world;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.MetaKey;
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
		if (this.connectsTo != null && this.connectsTo.size() > 0)
		{
			return getMeta(this,world,inWorldPosition);
		} else {
			return this.baseTexture;
		}
	
	}
	
	
	
	
	public static HashMap<MetaKey, MetaSprite> metaspritemap = new HashMap<MetaKey, MetaSprite>();
	
	public static MetaSprite getMeta(Tile tile, WorldSpace world, Vec2 inWorldPosition){
		
		int tul = world.terrain.getTile(inWorldPosition.addNew(-1, -1));
		int tum = world.terrain.getTile(inWorldPosition.addNew(0, -1));
		int tur = world.terrain.getTile(inWorldPosition.addNew(1, -1));

		int tml = world.terrain.getTile(inWorldPosition.addNew(-1, 0));
		int tmr = world.terrain.getTile(inWorldPosition.addNew(1, 0));

		int tbl = world.terrain.getTile(inWorldPosition.addNew(-1, 1));
		int tbm = world.terrain.getTile(inWorldPosition.addNew(0, 1));
		int tbr = world.terrain.getTile(inWorldPosition.addNew(1, 1));

		MetaKey surroundedBy = new MetaKey(tile, tul,tum,tur,tml,tmr,tbl,tbm,tbr);
		
		
		MetaSprite s  = metaspritemap.get(surroundedBy);
		if (s == null) {
			System.out.println("Generates New");
			s = new MetaSprite(tile.baseTexture);
			
			int ul = 4, 
				ur = 3,
				bl = 1, 
				br = 0;
			
			MetaSheet sheet = null;//(MetaSheet) tile.connectsTo.values().toArray()[0];
			boolean b1,b2,b3;
			
			// Top Left - Default: 4
			{
				b1 = !tile.connectsTo.containsKey(tul);//isSelf(tul);
				b2 = !tile.connectsTo.containsKey(tum);//isSelf(tum);
				b3 = !tile.connectsTo.containsKey(tml);//isSelf(tml);
				
				if (b2 && b3){
					if (b1)
						ul = 4;
					else {
						ul = 2;
						if (tile.connectsTo.containsKey(tul))
							sheet = tile.connectsTo.get(tul);
					}
				} else if (!b2 && b3){
					ul = 1;
					if (tile.connectsTo.containsKey(tum))
						sheet = tile.connectsTo.get(tum);
				} else if (b2 && !b3){
					ul = 3;
					if (tile.connectsTo.containsKey(tml))
						sheet = tile.connectsTo.get(tml);
				} else if (!b2 && !b3){
					ul = 0;
					if (tile.connectsTo.containsKey(tml))
						sheet = tile.connectsTo.get(tml);
				} else {
					ul = 4;
					if (tile.connectsTo.containsKey(tul))
						sheet = tile.connectsTo.get(tul);
				}
			}
			
			if (sheet != null) {
				((MetaSprite)s).setTopLeft(sheet.topLeft[ul]);
			} 
			sheet = null;
			
			// Top Right - Default: 3
			{
				b1 = !tile.connectsTo.containsKey(tur);//isSelf(tur);
				b2 = !tile.connectsTo.containsKey(tum);//isSelf(tum);
				b3 = !tile.connectsTo.containsKey(tmr);//isSelf(tmr);
				
				if (b2 && b3){
					if (b1)
						ur = 3;
					else {
						ur = 2;
						if (tile.connectsTo.containsKey(tur))
							sheet = tile.connectsTo.get(tur);
					}
				} else if (!b2 && b3){
					ur = 0;
					if (tile.connectsTo.containsKey(tum))
						sheet = tile.connectsTo.get(tum);
				} else if (b2 && !b3){
					ur = 4;
					if (tile.connectsTo.containsKey(tmr))
						sheet = tile.connectsTo.get(tmr);
				} else if (!b2 && !b3){
					ur = 1;
					if (tile.connectsTo.containsKey(tmr))
						sheet = tile.connectsTo.get(tmr);
				} else {
					ur = 3;
					if (tile.connectsTo.containsKey(tur))
						sheet = tile.connectsTo.get(tur);
				}
			}
	
			if (sheet != null) {
				((MetaSprite)s).setTopRight(sheet.topRight[ur]);
			} 
			sheet = null;
			
			// Bottom Left - Default: 1
			{
				b1 = !tile.connectsTo.containsKey(tbl);//isSelf(tbl);
				b2 = !tile.connectsTo.containsKey(tbm);//isSelf(tbm);
				b3 = !tile.connectsTo.containsKey(tml);//isSelf(tml);
				
				if (b2 && b3){
					if (b1)
						bl = 1;
					else {
						bl = 2;
						if (tile.connectsTo.containsKey(tbl))
							sheet = tile.connectsTo.get(tbl);
					}
				} else if (!b2 && b3){
					bl = 4;
					if (tile.connectsTo.containsKey(tbm))
						sheet = tile.connectsTo.get(tbm);
				} else if (b2 && !b3){
					bl = 0;
					if (tile.connectsTo.containsKey(tml))
						sheet = tile.connectsTo.get(tml);
				} else if (!b2 && !b3){
					bl = 3;
					if (tile.connectsTo.containsKey(tml))
						sheet = tile.connectsTo.get(tml);
				} else {
					bl = 1;
					if (tile.connectsTo.containsKey(tbl))
						sheet = tile.connectsTo.get(tbl);
				}
			}
	
			if (sheet != null) {
				((MetaSprite)s).setBottomLeft(sheet.bottomLeft[bl]);
			} 
			sheet = null;
			
			// Bottom Right - Default: 0
			{
				b1 = !tile.connectsTo.containsKey(tbr);//isSelf(tbr);
				b2 = !tile.connectsTo.containsKey(tbm);//isSelf(tbm);
				b3 = !tile.connectsTo.containsKey(tmr);//isSelf(tmr);
				
				if (b2 && b3){
					if (b1)
						br = 0;
					else {
						br = 2;
						if (tile.connectsTo.containsKey(tbr))
							sheet = tile.connectsTo.get(tbr);
					}
				} else if (!b2 && b3){
					br = 3;
					if (tile.connectsTo.containsKey(tbm))
						sheet = tile.connectsTo.get(tbm);
				} else if (b2 && !b3){
					br = 1;
					if (tile.connectsTo.containsKey(tmr))
					sheet = tile.connectsTo.get(tmr);
				} else if (!b2 && !b3){
					br = 4;
					if (tile.connectsTo.containsKey(tmr))
						sheet = tile.connectsTo.get(tmr);
				} else {
					br = 0;
					if (tile.connectsTo.containsKey(tbr))
						sheet = tile.connectsTo.get(tbr);
				}
			}
	
			if (sheet != null) {
				((MetaSprite)s).setBottomRight(sheet.bottomRight[br]);
			} 
			sheet = null;
			metaspritemap.put(surroundedBy, s);
		}
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
			protected boolean all;
			protected String texture;
		}
		
		public Tile createTile(){
			
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
