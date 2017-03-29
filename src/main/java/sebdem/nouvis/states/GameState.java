package sebdem.nouvis.states;

import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;
import sebdem.nouvis.world.WorldSpace;

public class GameState implements IGState {
	
	
	long etime;
	WorldSpace world;
	Camera camera;
	
	
	public GameState()
	{ 
		
		
	}

	public int updateFrequency() {
		return IGState.MODERATE_UPDATE_FREQUENCY;
	}

	public void update(long elapsedTime)
    {
    }
	
	public void draw(NouvGraphics g) {

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
