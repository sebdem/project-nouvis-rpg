package sebdem.nouvis.states;

import java.awt.Color;

import javax.swing.JFrame;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.EntityPlayer;
import sebdem.nouvis.entity.controller.EntityController;
import sebdem.nouvis.entity.controller.PlayerInputController;
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
	EntityPlayer player;
	PlayerInputController controller;
	
	public GameState(JFrame container)
	{ 
		tiles = NouvisApp.tiles;
		//camera = new Camera(new Vec2(5,5), new Vec2(48,27));
		world = new WorldGenerator(123).generate();
//		world = new WorldSpace();
//		int[][] map = {{1,2,2},{1,1,2},{3,1,1}};
//		world.terrain = TileTerrainData.fromArray(map);
		//camera = new Camera(new Vec2(3,3), new Vec2(48,27));
		camera = new Camera(new Vec2(9,9), new Vec2(20, 15));

		player = new EntityPlayer(world.randomPnt(), new Vec2(1,1));
		player.world = world;
		controller = new PlayerInputController(player);
		container.addKeyListener(controller);
	}

	public int updateFrequency() {
		return IGState.MODERATE_UPDATE_FREQUENCY;
	}

	
	public void update(long elapsedTime)
    {
		EntityController.getCurrent(player).update(elapsedTime);
		player.update(elapsedTime);
		//this.camera.position.addTo(((float)Math.random() - 0.5f), ((float)Math.random() - 0.5f));
		//this.camera.position.addTo(0.125f,0);
		this.camera.relateTo(player.position);
    }

	
	
	Vec2 tilescale = new Vec2(64,64);
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
		 
		this.player.draw(g, camera, tilescale);

		g.fill(Color.yellow, g.bottomLeft().substractFrom(0, tilescale.y + 8) ,tilescale.addNew(8, 8));
		g.draw(this.controller.selectedTile.getBaseTexture(), g.bottomLeft().substractFrom(-6, tilescale.y+6) ,tilescale);
		
		//g.drawLine(new Vec2(0, player.lastDrawPos.y), new Vec2(g.bottomRight().x, player.lastDrawPos.y), Color.white);
		//g.drawLine(new Vec2(player.lastDrawPos.x, 0), new Vec2(player.lastDrawPos.x, g.bottomRight().y), Color.white);
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
