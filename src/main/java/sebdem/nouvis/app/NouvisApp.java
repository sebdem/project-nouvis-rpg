package sebdem.nouvis.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.entity.controller.EntityController;
import sebdem.nouvis.graphics.NouvFont;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.graphics.SpriteSheet;
import sebdem.nouvis.resource.FontResource;
import sebdem.nouvis.resource.SpriteResource;
import sebdem.nouvis.states.EmptyState;
import sebdem.nouvis.states.GameState;
import sebdem.nouvis.states.StateStack;
import sebdem.nouvis.utils.RandomUtils;
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
	
	public static TileRegistry tiles;

	public static NouvFont crafted;

	public static Sprite logo;
	

	public StateStack gameStates;
	
	public static void main(String[] args){
		NouvisApp nouv = new NouvisApp();
		
	}
	
	public NouvisApp(){
		super();
		initRes();
		//screenResolution = new Dimension(1280,960);
		screenResolution = new Dimension(600,800);
		setResizable(false);
		setTitle("Nouvis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null); 

		// Fullscreen implementation ....
		if(false){
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			this.setUndecorated(true);
			GraphicsDevice display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			display.setFullScreenWindow(this);
			//getScreenDevices()[0].setFullScreenWindow(this);
			screenResolution = new Dimension(display.getDisplayMode().getWidth(),display.getDisplayMode().getHeight());
		}
		

		setBounds(0,0,screenResolution.width, screenResolution.height);
		setVisible(true);
		

		thread = new Thread(this);
		
		this.gameStates = new StateStack();
		this.gameStates.register("game.menu", new EmptyState());
		this.gameStates.registerAndPush("game.world", new GameState(this));
		
		
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

		Rectangle screen = new Rectangle(0, 0, (int)screenResolution.getWidth(), (int)screenResolution.getHeight());
		graphic.setDisplaySize(screen);
		
		//graphic.fill(new Color(0,0,0), screen );
		//graphic.draw(logo, new Rectangle(0,0, 640, 128));
		graphic.fill(Color.black, screen);
		this.gameStates.draw(graphic);
//		
//		Random r = new Random(1234);
//		Vec2 size = new Vec2(32, 32);
//		for(int y = 0; y < 6; y++){
//			for(int x = 0; x < 6; x++){
//				Set<Integer> tileids = tiles.getIDs();
//				//int tid = (int) RandomUtil.arrayRandomItem(tileids.toArray());
//				int tid = tileids.toArray(new Integer[tileids.size()])[r.nextInt(tileids.size())];
//				graphic.draw(tiles.get(tid).getBaseTexture(), new Vec2(x*size.x,y*size.y).createRectSize(size).getBounds());
//			}
//		}
//		graphic.drawString(crafted, "HalLo Welt!", new Vec2(16,48), Color.white);
		//graphic.drawString(crafted, "Hier könnte Ihre Werbung stehen!", new Vec2(16,64), new Color(224,224,0));
//		graphic.drawString(crafted, "Hier könnte ebenfalls Ihre Werbung stehen!", new Vec2(16,72), new Color(224,224,0), new Color(64,64,64));
//		
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
				this.gameStates.update(passed);
				//this.gameStates.update(passed / 1000000);
				//screen.update();
				delta--;
			}
			render();// displays to the screen unrestricted time
		}
	}
}
