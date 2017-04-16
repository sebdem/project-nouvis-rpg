package sebdem.nouvis.states;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.ISprite;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;
import sebdem.nouvis.world.Tile;
import sebdem.nouvis.world.TileRegistry;
import sebdem.nouvis.world.TileTerrainData;
import sebdem.nouvis.world.WorldSpace;
import sebdem.nouvis.world.generation.WorldGenerator;

public class GameState implements IGState {
	
	
	long etime;
	WorldSpace world;
	Camera camera;

	TileRegistry tiles;
	
	public GameState()
	{ 
		tiles = NouvisApp.tiles;
		//camera = new Camera(new Vec2(5,5), new Vec2(48,27));
		world = new WorldGenerator(233423).generate();
		//world = new WorldSpace();
		//int[][] map = {{1,2,2},{1,1,2},{3,1,1}};
		//world.terrain = TileTerrainData.fromArray(map);
		//camera = new Camera(new Vec2(3,3), new Vec2(48,27));
		camera = new Camera(new Vec2(3,3), new Vec2(48,27));
	}

	public int updateFrequency() {
		return IGState.MODERATE_UPDATE_FREQUENCY;
	}

	Vec2 cameraDest;
	
	public void update(long elapsedTime)
    {
		if (cameraDest == null || cameraDest.equals(camera.position)){
			cameraDest = world.randomPntInRange(camera.position, 20);
		}
		else {
			float movex = cameraDest.x - camera.position.x;
			if (Math.abs(movex) > 0.125){
				movex = (movex > 0) ? 0.125f : (movex < 0) ? -0.125f : 0;
			}
			float movey = cameraDest.y - camera.position.y;
			if (Math.abs(movey) > 0.125){
				movey = (movey > 0) ? 0.125f : (movey < 0) ? -0.125f : 0;
			}


			//System.out.println("Camera moves by: " + movex + ", " + movey);
			this.camera.position.addTo(movex, movey);
		}
		//this.camera.position.addTo(((float)Math.random() - 0.5f), ((float)Math.random() - 0.5f));
		//this.camera.position.addTo(0.125f,0);
    }

	Vec2 tilescale = new Vec2(40,40);
	//Vec2 tilescale = new Vec2(32,32);
	public void draw(NouvGraphics g) {
		int[][] tileids = world.terrain.getTile(camera.position, camera.bottomRight());
		
		Vec2 offset = camera.position.intOffset();

		Vec2 drawPos = offset.copy();
		Tile tile;
		ISprite s = null;
		for(int ty = 0; ty < tileids.length; ty++){
			drawPos.y = ((ty- offset.y)* tilescale.y);

			for(int tx = 0; tx < tileids[ty].length; tx++){
				tile = tiles.get(tileids[ty][tx]);
				drawPos.x = ((tx- offset.x)* tilescale.x); 

				if (tile != null){
					s = tile.getSprite(world, camera.position.addNew(tx, ty));
					g.draw(s, drawPos, tilescale);
				}
			}	
		}
		tile = null;
		s = null;
		drawPos = null;
		offset = null;
		tileids = null;
//		g.drawString("EmptyState " + etime, 20 + positionx, g.getFont().getSize() / 2 + screen.renderedImage.getHeight() / 2 );
//		g.drawString("If you see this, an Error may have occurred", 20 + positionx, g.getFont().getSize() * 2 + screen.renderedImage.getHeight() / 2 );
		g.dispose();
	}
 
    public void onEnter()
    {
        // No action to take when the state is entered
    }
 
    public void onExit()
    {
        // No action to take when the state is exited
    }

	
}
