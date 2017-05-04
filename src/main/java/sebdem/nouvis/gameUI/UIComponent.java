package sebdem.nouvis.gameUI;

import java.util.ArrayList;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.world.Camera;

public abstract class UIComponent {


	public ArrayList<UIComponent> children;
	public UIComponent parent;
	
	public Vec2 position;

	public Vec2 size;
	
	public boolean visible;
	
	public UIComponent(){
		this(null);
	}
	public UIComponent(UIComponent parent){
		this(parent, new Vec2(0,0), new Vec2(1,1));
	}
	
	public UIComponent(UIComponent parent, Vec2 position, Vec2 size){
		this.parent = parent;
		this.children = new ArrayList<UIComponent>();
		this.position = position;
		this.size = size;
		this.visible = true;
	}

	public void add(UIComponent comp){
		if(this.children.add(comp));
			comp.parent = this;
	}
	
	public abstract void update(long elapsedTime);

	public abstract void draw(NouvGraphics g);

	public abstract void draw(NouvGraphics g, Camera camera, Vec2 upscale);
	
}
