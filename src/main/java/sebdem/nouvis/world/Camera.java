package sebdem.nouvis.world;

import java.awt.geom.Rectangle2D;

import sebdem.nouvis.datastructs.Vec2;

public class Camera {

	public Vec2 position;
	public Vec2 size;
	
	
	public Camera(Vec2 position, Vec2 size){
		this.position = position;
		this.size = size;
	}
	
	
	public void relateTo(Vec2 target){
		
		
		float tx = target.x - (size.x / 2);
		float ty = target.y - (size.y / 2);
		if (tx < 0)
			tx = 0;
		this.position.x = tx;
		if (ty < 0)
			ty = 0;
		this.position.y = ty;
	}

	public Vec2 topLeft(){
		return position;
	}
	public Vec2 topRight(){
		return position.addNew(size.x, 0);
	}
	public Vec2 bottomLeft(){
		return position.addNew(0, size.y);
	}
	public Vec2 bottomRight(){
		return position.addNew(size);
	}
	
	public Rectangle2D.Float viewArea(){
		return this.position.createRectSize(this.size);
	}
	
	public Vec2 getFocusPoint(){
		return this.position.addNew(this.size.multiplyNew(0.5f));
	}
	
}
