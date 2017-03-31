package sebdem.nouvis.states;

import java.awt.Color;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;

public class EmptyState implements IGState {
	
	
	long etime;
	
	private float value = 5;
	private boolean toggle;
	
	private long elapsedTime;
	
	public EmptyState()
	{ }

	public int updateFrequency() {
		return IGState.MODERATE_UPDATE_FREQUENCY;
	}

	public void update(long elapsedTime)
    {
		this.elapsedTime = elapsedTime;
		value += (toggle) ? 1f : -1f;
		if (value < 5)
			toggle = true;
		else if (value > 300)
			toggle = false;
    }
	
	public void draw(NouvGraphics g) {
		g.fill(Color.BLACK, g.getDisplaySize());
		g.drawString(NouvisApp.crafted, "EMPTY - " + elapsedTime / 1000000, g.topLeft().addTo(value, 32), Color.WHITE, 4);
		
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
