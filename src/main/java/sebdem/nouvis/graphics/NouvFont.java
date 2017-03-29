package sebdem.nouvis.graphics;

import java.util.HashMap;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.resource.FontResource;

public class NouvFont {

	private HashMap<Character, Sprite> chars;
	private Sprite unknownChar;
	
	public NouvFont(FontResource font) {
		chars = new HashMap<Character, Sprite>();
		SpriteSheet sheet = new SpriteSheet(font, new Vec2(8,8));
		sheet.construct();
		putChars(' ', '~', sheet.elements, 0);
		this.unknownChar = getFor('?');
	}
	
	private void putChars(char from, char to, Sprite[] sprites, int spritesoffset){
		for(char c = from; c <= to; c++){
			chars.put(c, sprites[spritesoffset + (c - from)]);
		}
	}

	public Sprite getFor(char c) {
		Sprite s = chars.get(c);
		if (s != null)
			return s;
		return unknownChar;
	}
}
