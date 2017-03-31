package sebdem.nouvis.states;

import java.awt.Rectangle;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.ISprite;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.utils.RandomUtils;
import sebdem.nouvis.world.Camera;
import sebdem.nouvis.world.Tile;
import sebdem.nouvis.world.TileRegistry;
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
		camera = new Camera(new Vec2(5,5), new Vec2(48,27));
		world = new WorldGenerator(233423).generate();
	}

	public int updateFrequency() {
		return IGState.MODERATE_UPDATE_FREQUENCY;
	}

	public void update(long elapsedTime)
    {
		this.camera.position.addTo(((float)Math.random() - 0.5f) * 0.25f, ((float)Math.random() - 0.5f) * 0.25f);
    }
	
	public void draw(NouvGraphics g) {
		int[][] tileids = world.terrain.getTile(camera.position, camera.bottomRight());
	
		Vec2 offset = camera.position.intOffset();
		//System.out.println("Position: " +camera.position.toString() + "; Int: "+intPos+"; Offset: " + offset.toString());
		Vec2 drawPos = offset.copy();
		for(int ty = 0; ty < tileids.length; ty++){
			drawPos.y = ((ty * 32) - offset.y);
			for(int tx = 0; tx < tileids[ty].length; tx++){
				Tile t = tiles.get(tileids[ty][tx]);
				drawPos.x = ((tx * 32) - offset.x); 
				if (t != null){

					ISprite s = t.getSprite(world, camera.position.addNew(tx, ty));
					System.out.println(drawPos);
					g.draw(s, drawPos, new Vec2(32,32));
					
				}
			}	
		}
		
//		g.drawString("EmptyState " + etime, 20 + positionx, g.getFont().getSize() / 2 + screen.renderedImage.getHeight() / 2 );
//		g.drawString("If you see this, an Error may have occurred", 20 + positionx, g.getFont().getSize() * 2 + screen.renderedImage.getHeight() / 2 );
//		g.dispose();
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
