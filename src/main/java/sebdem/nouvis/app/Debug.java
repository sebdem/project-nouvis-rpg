package sebdem.nouvis.app;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.JFrame;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.graphics.NouvFont;
import sebdem.nouvis.graphics.NouvGraphics;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.graphics.SpriteSheet;
import sebdem.nouvis.resource.FontResource;
import sebdem.nouvis.resource.SpriteResource;

public class Debug extends JFrame{
	
	NouvGraphics graphic;
	NouvFont crafted;
	SpriteSheet grassDirt;
	SpriteSheet grassWater;
	Sprite logo;
	
	public Debug(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50,50,640, 480);
		setUndecorated(true);
		
		setVisible(true);
		graphic = new NouvGraphics(this.getGraphics());
		
		crafted = new NouvFont(new FontResource("font.png"));
		
		grassDirt = new SpriteSheet(new SpriteResource("tiles", "grass_to_dirt.png"), new Vec2(8,8));
		grassDirt.construct();
		grassWater = new SpriteSheet(new SpriteResource("tiles", "grass_to_water.png"), new Vec2(8,8));
		grassWater.construct();
		logo = new Sprite(new SpriteResource("logo.png"));
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{

		//BufferedImage output = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
		final Debug  d = new Debug();
		Thread t = new Thread(new Runnable(){

			public void run() {

				while(true){
					d.render();
					
					try {
						Thread.sleep(1000 / 60);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}});
		t.start();
	}
	
	public void render(){
		
		//graphic.fill(grassDirt.getSpriteAt(0, 0), new Rectangle(0,0, 640, 480), Color.blue);
		graphic.fill(new Color(0,0,0), new Rectangle(0,0, 640, 480) );
		graphic.draw(logo, new Rectangle(0,0, 640, 128), new Color(255,0,0));
		
		Vec2 size = new Vec2(24, 24);
		for(int y = 0; y < 2; y++){
			for(int x = 0; x < 2; x++){
				graphic.draw(grassDirt.getSpriteAt(x, y), new Vec2(x*size.x,y*size.y).createRectSize(size).getBounds());
			}
		}
		//graphic.fill(crafted.getFor('H'), new Rectangle(0,0, 640, 480), new Color(127,0,0));
		graphic.drawString(crafted, "HalLo Welt!", new Vec2(16,16), Color.white);
		graphic.drawString(crafted, "Hier könnte Ihre Werbung stehen!", new Vec2(16,32), new Color(224,224,0));
		graphic.drawString(crafted, "Hier könnte ebenfalls Ihre Werbung stehen!", new Vec2(16,40), new Color(224,224,0), new Color(64,64,64));
		
	}
	
}
