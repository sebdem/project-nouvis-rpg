package sebdem.nouvis.world;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.EntityBase;
import sebdem.nouvis.entity.EntityLiving;
import sebdem.nouvis.entity.controller.EntityController;
import sebdem.nouvis.graphics.NouvGraphics;

public class WorldSpace {

	public TileTerrainData terrain;
	
	public ArrayList<EntityBase> entities;
	
	public WorldSpace(){
		entities = new ArrayList<EntityBase>();
	}
	
	public void draw(NouvGraphics graph, Camera camera){
		
	}

	
	public boolean isPosOccupied(Vec2 position){
		int tileid = terrain.getTile(position);
		Tile tile = NouvisApp.tiles.get(tileid);
		boolean occupied = false;
		
		if (tile != null){
			occupied = !tile.isWalkable();
		}
		return occupied;
		
	}
	
	public boolean place(int tileid, Vec2 pos){
		return this.terrain.setTile((int)pos.x, (int)pos.y, tileid);
	}
	
	public void addEntity(EntityBase e){
		this.entities.add(e);
		e.world = this;
	}
	
	public EntityBase[] getInView(Camera c){
		ArrayList<EntityBase> selection = new ArrayList<EntityBase>();
		Rectangle2D.Float viewarea = c.viewArea();
		for(EntityBase e : this.entities){
			if (viewarea.intersects(e.getBounds()));
				selection.add(e);
		}
		selection.sort((par1, par2)->{return (par1.position.y > par2.position.y) ? 1 : (par1.position.y < par2.position.y) ? -1 : 0;  });
		
		return selection.toArray(new EntityBase[selection.size()]);
	}
	
	public void updateEntities(long elapsedTime){
		for(int i = 0; i < entities.size(); i++){
			EntityBase e = entities.get(i);
			if (e instanceof EntityLiving){
				EntityController c = EntityController.getCurrent((EntityLiving) e);
				if (c != null)
					c.update(elapsedTime);
			}
			e.update(elapsedTime);
		}
	}
	

	public Vec2 randomPnt(){
		float f1, f2;
		Vec2 point;
		do {
			f1 = ((float)Math.random() * terrain.width);
			f2 = ((float)Math.random() * terrain.height);
			point = new Vec2(f1,f2);
		} while(isPosOccupied(point));
		return point;
	}
	
	public Vec2 randomPntInRange(Vec2 origin, int radius){
		float f1;
		float x = origin.x, y = origin.y;
		float f2;
		Vec2 point;
		do {
				
			do{
				f1 = ((float)Math.random() * (radius * 2)) - radius;
			} while(!(x+ f1 < terrain.width && x+ f1 > 0));
			
			do{
				f2 = ((float)Math.random() * (radius * 2)) - radius;
			} while(!(y+f2 < terrain.height && y+f2 > 0));
			point = origin.addNew(f1, f2);
		} while(isPosOccupied(point));
		return point;
	}

}
