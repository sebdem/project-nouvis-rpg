package sebdem.nouvis.entity.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import sebdem.nouvis.entity.EntityLiving;

public class PlayerInputController extends EntityController implements KeyListener{

	public PlayerInputController(EntityLiving entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(long elapsedTime) {
		
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch(e.getKeyCode()){
			case KeyEvent.VK_W: movekey[0] = false; break;
			case KeyEvent.VK_S: movekey[1] = false; break;
			case KeyEvent.VK_D: movekey[2] = false; break;
			case KeyEvent.VK_SHIFT: movekey[4] = false; break;
		}
	}

}
