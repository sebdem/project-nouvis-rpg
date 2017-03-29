package sebdem.nouvis.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import sebdem.nouvis.datastructs.Vec2;

public class NouvGraphics {

	private Graphics2D graph;
	
	public NouvGraphics(BufferStrategy bs){
		this(bs.getDrawGraphics());
	}
	public NouvGraphics(Graphics g){
		this((Graphics2D)g);
	}
	public NouvGraphics(Graphics2D g){
		this.graph = g;
		this.graph.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	}

	
	public void draw(ISprite sprite, Vec2 position) {
		draw(sprite, position.createRectSize(sprite.getDim()).getBounds());
	}
	public void draw(ISprite sprite, Vec2 position, Color tint) {
		draw(sprite, position.createRectSize(sprite.getDim()).getBounds(), tint);
	}
	
	public void draw(ISprite sprite, Rectangle r){
		graph.drawImage(sprite.get(), r.x, r.y, r.width, r.height, null);
	}
	public void draw(ISprite sprite, Rectangle r, Color tint){
		graph.drawImage(sprite.tint(tint), r.x, r.y, r.width, r.height, null);
	}
	
	public void dispose(){
		graph.dispose();
	}

	public void fill(Color color, Rectangle r){
		graph.setColor(color);
		graph.fillRect( r.x, r.y, r.width, r.height);
	}
	public void fill(ISprite sprite, Rectangle r){
		Vec2 dim = sprite.getDim();
		for(int y = r.y; y < r.getMaxY(); y += dim.y){
			for(int x = r.x; x < r.getMaxX(); x += dim.x){
				graph.drawImage(sprite.get(), x, y, (int)dim.x, (int)dim.y, null);
			}
		}
		dim = null;
	}
	public void fill(ISprite sprite, Rectangle r, Color tint){
		Vec2 dim = sprite.getDim();
		for(int y = r.y; y < r.getMaxY(); y += dim.y){
			for(int x = r.x; x < r.getMaxX(); x += dim.x){
				graph.drawImage(sprite.tint(tint), x, y, (int)dim.x, (int)dim.y, null);
			}
		}
		dim = null;
	}
	
	public void drawString(NouvFont font, String text, Vec2 position, Color tint){
		// TODO : Draw String fitting to clip bounds?
		char[] chars = text.toCharArray();
		ISprite s = null;
		Vec2 sdim;
		for(char c : chars){
			s = font.getFor(c);
			sdim = s.getDim(); 
			
			draw(s, position.createRectSize(sdim).getBounds(), tint);

			position.addTo(sdim.x, 0);
		}
	}
	public void drawString(NouvFont font, String text, Vec2 position, Color tint, Color shadow){
		// TODO : Draw String fitting to clip bounds?
		char[] chars = text.toCharArray();
		ISprite s = null;
		Vec2 sdim;
		for(char c : chars){
			s = font.getFor(c);
			sdim = s.getDim(); 

			draw(s, position.addNew(1, 1).createRectSize(sdim).getBounds(), shadow);
			draw(s, position.createRectSize(sdim).getBounds(), tint);

			position.addTo(sdim.x, 0);
		}
	}
	
	
}
