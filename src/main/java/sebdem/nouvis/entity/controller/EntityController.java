package sebdem.nouvis.entity.controller;

import java.util.HashMap;
import java.util.Stack;

import sebdem.nouvis.entity.EntityLiving;

public abstract class EntityController {
	
	protected static HashMap<EntityLiving, Stack<EntityController> >registry = new HashMap<EntityLiving, Stack<EntityController>>();
	
	protected EntityLiving entity;
	
	public EntityController(EntityLiving entity){
		this.entity = entity;
		registerFor(this.entity);
	}

	public abstract void update(long elapsedTime);
	
	
	public static EntityController getCurrent(EntityLiving e){
		Stack<EntityController> stck = registry.get(e);
		return stck.lastElement();
	}
	
	public EntityController unregister(){
		return registry.get(this.entity).pop();
	}
	
	public void registerFor(EntityLiving e){
		Stack<EntityController> stck = registry.get(e);
		if (stck == null)
		{
			stck = new Stack<EntityController>();
			stck.push(this);
			registry.put(e, stck);
		} else {
			stck.push(this);
		}
	}
	
	
}
