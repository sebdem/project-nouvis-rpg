package sebdem.nouvis.world;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;

public class WorldSpace {

	
	public TileTerrainData terrain;
	
	
	public WorldSpace(){
		
	}
	
	public void draw(NouvGraphics graph, Camera camera){
		
	}

	public Vec2 randomPnt(){
		float f1 = ((float)Math.random() * terrain.width);
		float f2 = ((float)Math.random() * terrain.height);
		return new Vec2(f1,f2);
	}
	public Vec2 randomPntInRange(Vec2 origin, int radius){
		float f1;
		float x = origin.x, y = origin.y;
		do{
			f1 = ((float)Math.random() * (radius * 2)) - radius;
		} while(!(x+ f1 < terrain.width && x+ f1 > 0));
		
		float f2;
		do{
			f2 = ((float)Math.random() * (radius * 2)) - radius;
		} while(!(y+f2 < terrain.height && y+f2 > 0));
		return origin.addNew(f1, f2);
	}

}
