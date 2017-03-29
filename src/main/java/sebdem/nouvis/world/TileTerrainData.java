package sebdem.nouvis.world;

import java.awt.Color;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.utils.IntegerUtils;


public class TileTerrainData {

	private int[][] _tiles;
	public int width;
	public int height;

	public TileTerrainData(int width, int height) {
		_tiles = new int[height][width];
		this.width = width;
		this.height = height;
	}

	public TileTerrainData(Vec2 mapdim) {
		this((int) Math.floor(mapdim.x), (int) Math.floor(mapdim.y));
	}

	public static TileTerrainData fromArray(int[][] map) {
		TileTerrainData t = new TileTerrainData(map[0].length, map.length);
		t._tiles = map;
		return t;
	}

	public int getTile(int x, int y) {
		if ((x >= 0 && y >= 0) && (x < width && y < height) ) {
			return _tiles[y][x];
		} else {
			return 0;
		}
	}
	public int getTile(Vec2 at) {
		return this.getTile( (int) Math.floor(at.x), (int) Math.floor(at.y));
	}

	public int[][] getTile(int x1, int y1, int x2, int y2) {
		int[][] retarr = new int[y2 - y1][x2 - x1];
		for (int y = 0; y < y2 - y1; y++)
			for (int x = 0; x < x2 - x1; x++)
				retarr[y][x] = _tiles[y1 + y][x1 + x];
		return retarr;
	}

	public int[][] getTiles() {
		return _tiles;
	}

	public int[][] getTile(Vec2 from, Vec2 to) {
		int minx = (int) Math.floor(from.x), maxx = (int) Math.ceil(to.x);
		int miny = (int) Math.floor(from.y), maxy = (int) Math.ceil(to.y);

		int[][] retarr = new int[maxy - miny][maxx - minx];
		for (int y = miny; y < maxy; y++)
			for (int x = minx; x < maxx; x++) {
				int value = Color.black.getRGB();
				if ((height > y && y >= 0) && (this.width > x && x >= 0))
					value = _tiles[y][x];
				retarr[y - miny][x - minx] = value;
			}
		return retarr;
	}

	public boolean setTile(int x, int y, int tile) {
		if (	IntegerUtils.inRange(x, 0, this.width - 1) 
			&& 	IntegerUtils.inRange(y, 0, this.height - 1)){
			_tiles[y][x] = tile;
			return true;
		}
		return false;
	}

	public void insert(int[][] tiles, int sx, int sy) {
		System.out.println("Inserting Tiles. . .");
		for (int y = sy; y < this.height && y - sy < tiles.length; y++)
			for (int x = sx; x < this.width && x - sx < tiles[0].length; x++) {
				this._tiles[y][x] = tiles[y - sy][x - sx];
			}
	}

	public Vec2 getCenter() {
		return new Vec2(this.width / 2, this.height / 2);
	}
}
