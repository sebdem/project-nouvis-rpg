package sebdem.nouvis.states;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import sebdem.nouvis.graphics.NouvGraphics;

public class StateStack
{
    public  Map<String, IGState> mStates = new HashMap<String, IGState>();
    public Stack<IGState> mStack = new Stack<IGState>();
  
    public void update(long elapsedTime)
    {
    	IGState top = mStack.lastElement();
        top.update(elapsedTime);
    }
 

	public void draw(NouvGraphics graph) {
         mStack.lastElement().draw(graph);
	}
	
    public void push(String name)
    {
    	IGState state = mStates.get(name);
        mStack.push(state);
        state.onEnter();
    }
 
    public IGState pop()
    {
    	IGState s = mStack.pop();
    	s.onExit();
        return s;
    }
    
    
    /**
     * Registers a new IGState Object with the given key
     * @param String key
     * @param IGState state 
     */
    public void register(String key, IGState state)
    {
    	mStates.put(key, state);
    }
    
    public void registerAndPush(String key, IGState state){
    	register(key, state);
    	this.push(key);
    }
    
    /**
     * @param key = the key of the IGState you want to remove
     */
    public void remove(String key)
    {
    	mStates.remove(key);
    }

    
	public int currentTickRate() {
		return mStack.lastElement().updateFrequency();
	}

}