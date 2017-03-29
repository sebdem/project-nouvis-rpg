package sebdem.nouvis.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Set;

import javax.swing.JFrame;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvFont;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.graphics.SpriteSheet;
import sebdem.nouvis.resource.FontResource;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.states.StateStack;
import sebdem.nouvis.utils.RandomUtil;
import sebdem.nouvis.world.TileRegistry;

public class NouvisApp extends JFrame implements Runnable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public boolean running;
	public NouvGraphics graphic;
	
	public Dimension screenResolution;
	
	public TileRegistry tiles;

	NouvFont crafted;
//	SpriteSheet grassDirt;
//	SpriteSheet grassWater;
	Sprite logo;
	

	public StateStack gameStates = new StateStack();
	
	public static void main(String[] args){
		NouvisApp nouv = new NouvisApp();
		
	}
	
	public NouvisApp(){
		super();
		initRes();
		screenResolution = new Dimension(640,480);
		setResizable(false);
		setTitle("Nouvis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		
		// Fullscreen implementation ....
		this.setUndecorated(true);
		setBounds(50,50,screenResolution.width, screenResolution.height);
		setVisible(true);
		

		thread = new Thread(this);
		start();
	}
	
	private synchronized void start() {
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * All Resource Loading should be done here!
	 */
	public void initRes(){
		// Fonts
		crafted = new NouvFont(new FontResource("font.png"));
		
		
		try
		{
			tiles = new TileRegistry();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sprite Sheets - Experimental
		/*grassDirt = new SpriteSheet(new SpriteResource("tiles", "dirt_grass.png"), new Vec2(8,8));
		grassDirt.construct();
		grassWater = new SpriteSheet(new SpriteResource("tiles", "water_grass.png"), new Vec2(8,8));
		grassWater.construct();
		*/
		
		// Other sprites
		logo = new Sprite(new SpriteResource("logo.png"));
	}


	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		graphic = new NouvGraphics(bs); 
		
		graphic.fill(new Color(0,0,0), new Rectangle(0,0, 640, 480) );
		graphic.draw(logo, new Rectangle(0,0, 640, 128));
		
		Vec2 size = new Vec2(24, 24);
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 6; x++){
				Set<Integer> tileids = tiles.getIDs();
				//int tid = (int) RandomUtil.arrayRandomItem(tileids.toArray());
				int tid = 2;
				graphic.draw(tiles.get(tid).getBaseTexture(), new Vec2(x*size.x,y*size.y).createRectSize(size).getBounds());
			}
		}
		graphic.drawString(crafted, "HalLo Welt!", new Vec2(16,16), Color.white);
		graphic.drawString(crafted, "Hier könnte Ihre Werbung stehen!", new Vec2(16,32), new Color(224,224,0));
		graphic.drawString(crafted, "Hier könnte ebenfalls Ihre Werbung stehen!", new Vec2(16,40), new Color(224,224,0), new Color(64,64,64));
		
		graphic.dispose();
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;// 60 times per second
		double delta = 0;
		requestFocus();
		long now = 0, passed = 0; 
		while (running) {
			now = System.nanoTime();
			passed = (now - lastTime);
			delta = delta + (passed / ns);
			lastTime = now;
			while (delta >= 1)// Make sure update is only happening 60 times a
								// second
			{
				// handles all of the logic restricted time
				//camera.update(map);

				//this.gameStates.update(passed / 1000000);
				//screen.update();
				delta--;
			}
			render();// displays to the screen unrestricted time
		}
	}
}
