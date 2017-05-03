package sebdem.nouvis.entity.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sebdem.nouvis.app.NouvisApp;
import sebdem.nouvis.entity.EntityLiving;
import sebdem.nouvis.world.Tile;

public class PlayerInputController extends EntityController implements KeyListener{

	public Tile selectedTile;
	
	public PlayerInputController(EntityLiving entity) {
		super(entity);
		selectedTile = NouvisApp.tiles.getNextLargest(0);
	}

	@Override
	public void update(long elapsedTime) {
		float speed = (this.movekey[4]) ? 0.125f : 0.085f;
		this.entity.movevec.x = this.movekey[2] ? speed : (this.movekey[3] ? -speed : 0);
		this.entity.movevec.y = this.movekey[0] ? -speed : (this.movekey[1] ? speed : 0);
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	boolean[] movekey = new boolean[5];
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_W: movekey[0] = true; break;
			case KeyEvent.VK_S: movekey[1] = true; break;
			case KeyEvent.VK_D: movekey[2] = true; break;
			case KeyEvent.VK_A: movekey[3] = true; break;
			case KeyEvent.VK_SHIFT: movekey[4] = true; break;
			case KeyEvent.VK_F1: new DestinationWalkerController(this.entity); break;
			case KeyEvent.VK_SPACE : 
				this.entity.world.place(selectedTile.getId(), this.entity.position.addNew(0, -1));
				
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch(e.getKeyCode()){
			case KeyEvent.VK_W: movekey[0] = false; break;
			case KeyEvent.VK_S: movekey[1] = false; break;
			case KeyEvent.VK_D: movekey[2] = false; break;
			case KeyEvent.VK_A: movekey[3] = false; break;
			case KeyEvent.VK_SHIFT: movekey[4] = false; break;
			case KeyEvent.VK_LEFT: selectedTile = NouvisApp.tiles.getNextSmallest((selectedTile != null) ? selectedTile.getId() : Integer.MAX_VALUE); break;
			case KeyEvent.VK_RIGHT: selectedTile =NouvisApp.tiles.getNextLargest((selectedTile != null) ? selectedTile.getId() : 0); break;
		}
	}

}
