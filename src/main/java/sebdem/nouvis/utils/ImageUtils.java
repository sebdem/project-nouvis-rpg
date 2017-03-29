package sebdem.nouvis.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Timer;
import sebdem.nouvis.graphics.Sprite;
import sebdem.nouvis.resource.SpriteResource;

public class ImageUtils {

	public static BufferedImage clear(BufferedImage image, Color clearColor) {
		Graphics2D g = image.createGraphics();
		g.setColor(clearColor);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.dispose();
		return image;
	}

	public static BufferedImage clear(BufferedImage image) {
		return clear(image, ColorUtils.TRANSPARENT);
	}

	public static BufferedImage clone(BufferedImage src) {
		BufferedImage clone = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
		Graphics2D g2d = clone.createGraphics();
		g2d.drawImage(src, 0, 0, null);
		g2d.dispose();
		return clone;
	}

	public static BufferedImage fuse(BufferedImage[]... bufferedImages) {
		int mxheight = 0;
		int mxwidth = 0;

		if (bufferedImages.length > 1) {
			for (BufferedImage[] row : bufferedImages) {
				int tempmxwidth = 0;
				int tempmxheight = 0;
				for (BufferedImage col : row) {
					tempmxwidth += col.getWidth();
					if (col.getHeight() > tempmxheight) {
						tempmxheight = col.getHeight();
					}
				}
				if (tempmxwidth > mxwidth) {
					mxwidth = tempmxwidth;
				}
				mxheight += tempmxheight;
			}
		} else {
			for (BufferedImage col : bufferedImages[0]) {
				mxwidth += col.getWidth();
				if (col.getHeight() > mxheight) {
					mxheight = col.getHeight();
				}
			}
		}

		BufferedImage newImg = new BufferedImage(mxwidth, mxheight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D newGraph = newImg.createGraphics();
		int prevx = 0;
		int prevy = 0;
		
		if (bufferedImages.length > 1) {
			for (BufferedImage[] row : bufferedImages) {
				prevx = 0;
				int maxy = 0;
				for (BufferedImage col : row) {
					newGraph.drawImage(col, prevx, prevy, null);
					prevx += col.getWidth();
					if (col.getHeight() > maxy){
						maxy = col.getHeight();
					}
				}
				prevy += maxy;
			}
		} else {
			for (BufferedImage col : bufferedImages[0]) {
				newGraph.drawImage(col, prevx, prevy, null);
				prevx += col.getWidth();
			}
		}

		return newImg;
	}
	
	public static void main(String[] args){
		Timer loading = new Timer(true);
		Sprite s11 = new Sprite(new SpriteResource("tiles", "bricks_grass.png"));
		Sprite s21 = new Sprite(new SpriteResource("tiles", "dirt_grass.png"));
		Sprite s12 = new Sprite(new SpriteResource("tiles", "water_grass.png"));
		Sprite s22 = new Sprite(new SpriteResource("tiles", "water_dirt.png"));
		loading.end();
		System.out.println(loading.get() / 1000000);
		System.out.println(loading);
		
		Timer thing = new Timer(true);
		BufferedImage[][] test1 = { {s11.get(), s21.get()}, {s12.get(), s22.get()}};
		BufferedImage[] test2 = { s11.get(), s21.get(), s12.get(), s22.get()};
		
		try {
			BufferedImage img = fuse(test1);
			ImageIO.write(img, "png", new File("test_IUtil_fuse_2d.png"));
			img = fuse(test2);
			ImageIO.write(img, "png", new File("test_IUtil_fuse_1d.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thing.end();
		System.out.println(thing.get() / 1000000);
		System.out.println(thing);
		
	}
}
