package sebdem.nouvis.utils;

import java.awt.Color;

public class ColorUtils {

	public static final Color TRANSPARENT = new Color(0,0,0,0);
	public static final int TRANSPARENT_INT = TRANSPARENT.getRGB();
	
	
	
	
	public static boolean isChannel(int color)
	{
		return  (color == -65281 || color == 3566399);
	}

	public static int multiplyColor(int originalColor, int selectedColor)
    {
        int ro = (originalColor & 16711680) >> 16;
        int go = (originalColor & 65280) >> 8;
        int bo = (originalColor & 255) >> 0;
        int rs = (selectedColor & 16711680) >> 16;
        int gs = (selectedColor & 65280) >> 8;
        int bs = (selectedColor & 255) >> 0;
        int rn = (int)((float)ro * (float)rs * 1.25 / 255.0F);
        int gn = (int)((float)go * (float)gs * 1.25 / 255.0F);
        int bn = (int)((float)bo * (float)bs * 1.25 / 255.0F);
        rn = (rn > 255) ? 255 : rn;
        gn = (gn > 255) ? 255 : gn;
        bn = (bn > 255) ? 255 : bn;
        return originalColor & -16777216 | rn << 16 | gn << 8 | bn;
    }
	
	public static Color[] createColorArray(int amount){
		Color[] colors = new Color[amount];
		for(int i = 0; i < amount; i++){
			colors[i] = new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255) );
		}
		return colors;
	}
	public static Color[] createColorArrayStep(int rgbStep){
		Color[] colors = new Color[(256/rgbStep)*(256/rgbStep)*(256/rgbStep)];
		for(int r = 0; r < 256; r += rgbStep){
			for(int g = 0; g < 256; g += rgbStep){
				for(int b = 0; b < 256; b += rgbStep){
					colors[(int)(Math.floor(r + g + b)/rgbStep)] = new Color(r,g,b);
				}
			}
		}
		return colors;
	}
	public static int[] createColorIntArrayStep(int rgbStep){
		int[] colors = new int[(255/rgbStep)*(255/rgbStep)*(255/rgbStep)];
		for(int r = 0; r < 255; r += rgbStep){
			for(int g = 0; g < 255; g += rgbStep){
				for(int b = 0; b < 255; b += rgbStep){
					colors[(r/rgbStep)*(g/rgbStep)*(b/rgbStep)] = new Color(r,g,b).getRGB();
				}
			}
		}
		return colors;
	}

	public static int darkenColor(int originalColor, float factor)
    {
        int ro = (originalColor & 16711680) >> 16;
        int go = (originalColor & 65280) >> 8;
        int bo = (originalColor & 255) >> 0;

        int rn = (int)(((float)ro *  1.25f / 255.0F) * factor);
        int gn = (int)(((float)go * 1.25f / 255.0F) * factor);
        int bn = (int)(((float)bo * 1.25f / 255.0F) * factor);
        rn = (int)((rn > 255) ? 255 : rn);
        gn = (int)((gn > 255) ? 255 : gn);
        bn = (int)((bn > 255) ? 255 : bn);
        return originalColor & -16777216 | rn << 16 | gn << 8 | bn;
    	
    }
	
	public static Color shade(Color base, float factor){
		
		int r = (int) ((base.getRed() * factor) % 256);
		int g = (int) ((base.getGreen() * factor) % 256);
		int b = (int) ((base.getBlue() * factor) % 256);

		try {

			Color shaded = new Color(r,g,b);
			return shaded;
		} catch  (Exception e) {
			System.out.println(r + ", " + g + ", " +b );
			return new Color(255,255,255);
		}
	}
	
	
	@Deprecated
	public static int addColor(int oRGB, int nRGB, float opacity) {
		float opac = opacity / 100;
		if (opac > 1)
			opac = 1;
		Color oColor = new Color(oRGB);
		Color nColor = new Color(nRGB);
//		int ro = (oRGB & 16711680) >> 16;
//        int rs = (nRGB & 16711680) >> 16;
//        int go = (oRGB & 65280) >> 8;
//        int gs = (nRGB & 65280) >> 8;
//        int bo = (oRGB & 255) >> 0;
//        int bs = (nRGB & 255) >> 0;
//        int rn = (int)(opac * (float)rs + (1-opac) * ro);
//        int gn = (int)((float)go * (float)gs * 1.25 / 255.0F);
//        int bn = (int)((float)bo * (float)bs * 1.25 / 255.0F);
		return new Color(
					(int)(opac * nColor.getRed() + ( 1 - opac) * oColor.getRed()),
					(int)(opac * nColor.getGreen() + ( 1 - opac) * oColor.getGreen()),
					(int)(opac * nColor.getBlue() + ( 1 - opac) * oColor.getBlue())
				).getRGB();
		
	}
	
	public static void main(String[] args){
		
		Color c = new Color(-9671572);
		
		System.out.printf("Color: %d ( %d %d %d - %d)", c.getRGB(), c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
}
