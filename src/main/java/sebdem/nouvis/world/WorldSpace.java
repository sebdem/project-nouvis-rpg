package sebdem.nouvis.world;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;

public class WorldSpace {

	public TileTerrainData terrain;
	
	
	public WorldSpace(){
		
	}
	
	public void draw(NouvGraphics graph, Camera camera){
		
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
