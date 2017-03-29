package sebdem.nouvis.states;

import sebdem.nouvis.graphics.NouvGraphics;

public interface IGState
{
	public static final int MODERATE_UPDATE_FREQUENCY = 50;
	public static final int FASTEST_UPDATE_FREQUENCY = 5;
	public static final int FAST_UPDATE_FREQUENCY = 10;
	
	public int updateFrequency();
    public void update(long elapsedTime);

    public void draw(NouvGraphics graph);
    public void onEnter();
    public void onExit();
}
