package sebdem.nouvis.world.generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import sebdem.nouvis.datastructs.Vec2;
import sebdem.nouvis.utils.ArrayUtils;
import sebdem.nouvis.utils.ColorUtils;
import sebdem.nouvis.utils.RandomUtils;
import sebdem.nouvis.world.TileTerrainData;
import sebdem.nouvis.world.WorldSpace;

public class WorldGenerator {

	int width;
	int height;
	
	int landmasses;
	long seed;
	
	int xgenoff;
	int ygenoff;
	
	public WorldGenerator(long seed) {
//		width = 640;
//		height = 480;
		width = 64;
		height = 64;
		this.seed = seed;
		
		landmasses = RandomUtils.randomIntAround(15, (int) (Math.random() * 6), (int) (Math.random() * 3));

		Random r = new Random(this.seed);
		xgenoff = r.nextInt(width*8);
		ygenoff = r.nextInt(height*8);
	}

	public WorldSpace generate()
	{
		WorldSpace world = new WorldSpace();

		TileTerrainData terrain = TileTerrainData
				.fromArray(ArrayUtils.fillInteger2D(new int[height][width], new Integer[] { 1 }));
		world.terrain = terrain;

		generateSimplexTerrain(world);

		return world;

	}
	
	private void generateSimplexTerrain(WorldSpace world)
	{
		TileTerrainData terrain = world.terrain;
		
		//float[][] values = generateOctavedSimplexNoise(width, height, r.nextInt(8) + 3, 0.25f, 0.00928125f); 
		//float[][] values = generateOctavedSimplexNoise(width, height, r.nextInt(8) + 3, 0.55f, 0.003140625f); 
		float[][] values = generateOctavedSimplexNoise(width, height, 20, 0.425f, 0.005f); //r.nextInt(8) + 1
		//float[][] values = generateOctavedSimplexNoise(width, height, 20, 0.02f, 0.005f); //r.nextInt(8) + 1
		
		
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		
		float value;

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				value = Math.abs(values[x][y]);

				setTile(x,y,terrain,value);
				
				output.setRGB(x, y, ColorUtils.shade(Color.white, value).getRGB());
			}
		}
		
		try
		{
			ImageIO.write(output, "png", new java.io.File("noise.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setTile(int x, int y, TileTerrainData terrain, float value)
	{
		if (value > 0.3f)
		{
			if (value > 0.525f){
				if (value > 0.575f)
				{
					if (value > 0.85f)
					{
						terrain.setTile(x, y, 4);
					} 
					else 
					{
						terrain.setTile(x, y, 3);
					}
				}
				else
				{
					terrain.setTile(x, y, 2);
				}
			}
			else 
			{
				terrain.setTile(x, y, 1); 
			}
		}
		else
		{
			terrain.setTile(x, y, 5);
		}

	}
	
	public float[][] generateOctavedSimplexNoise(int width, int height, int octaves, float roughness, float scale)
	{
		float[][] totalNoise = new float[width][height];
		float layerFrequency = scale;
		float layerWeight = 1;
		@SuppressWarnings("unused")
		float weightSum = 0;
		Random r = new Random(seed);
		
		for (int octave = 0; octave < octaves; octave++)
		{
			// Calculate single layer/octave of simplex noise, then add it to
			// total noise
			for (int x = 0; x < width; x++)
			{
				for (int y = 0; y < height; y++)
				{
					totalNoise[x][y] +=(float) SimplexNoise.noise(x * layerFrequency+xgenoff, y * layerFrequency+ygenoff) * layerWeight;
				}
			}

			// Increase variables with each incrementing octave
			layerFrequency *= 2;
			weightSum += layerWeight;
			layerWeight *= roughness;

		}
		return totalNoise;
	}

	@Deprecated
	public float octavedNoise(float x, float y, int octaves, float roughness, float scale)
	{
		float noiseSum = 0;
		float layerFrequency = scale;
		float layerWeight = 1;
		float weightSum = 0;

		for (int octave = 0; octave < octaves; octave++)
		{
			noiseSum += SimplexNoise.noise(x * layerFrequency, y * layerFrequency) * layerWeight;
			layerFrequency *= 2;
			weightSum += layerWeight;
			layerWeight *= roughness;
		}
		return noiseSum / weightSum;
	}

	public static float[][] generateSimplexNoise(int width, int height)
	{
		float[][] simplexnoise = new float[width][height];
		float frequency = 5.0f / (float) width;

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				simplexnoise[x][y] = (float) SimplexNoise.noise(x * frequency, y * frequency);
				simplexnoise[x][y] = (simplexnoise[x][y] + 1) / 2; // generate
																	// values
																	// between 0
																	// and 1
			}
		}

		return simplexnoise;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void generateLandmassCircle(WorldSpace world, int genCenterX, int genCenterY)
	{
		TileTerrainData terrain = world.terrain;
		float radx = RandomUtils.randomIntAround(50, 40, 20);
		float rady = RandomUtils.randomIntAround(50, 40, 20);

		for (float y = -rady; y < rady; y++)
		{
			float yd = (y * y) / (rady * rady);
			for (float x = -radx; x < radx; x++)
			{
				float xd = (x * x) / (radx * radx);
				if (yd + xd <= 1)
				{
					terrain.setTile((int) (genCenterX + x + RandomUtils.randomIntAround(7, 4, 2)),
							(int) (genCenterY + y + RandomUtils.randomIntAround(7, 4, 2)), 2);
				}
			}
		}
		terrain.setTile(genCenterX, genCenterY, 3);

	}

	@SuppressWarnings("unused")
	@Deprecated
	private void generateLandmassPolyg(WorldSpace world, int genCenterX, int genCenterY)
	{
		TileTerrainData terrain = world.terrain;
		float radx = RandomUtils.randomIntAround(50, 40, 20);
		float rady = RandomUtils.randomIntAround(50, 40, 20);

		Vec2[] coasts = new Vec2[RandomUtils.randomIntAround(8, 10)];
		for (int i = 0; i < coasts.length; i++)
		{
			coasts[i] = new Vec2((int) (Math.random() * (radx * 2) - radx), (int) (Math.random() * (rady * 2) - rady));
		}

		for (Vec2 c : coasts)
		{
			int sx = (int) ((c.x < 0) ? c.x : 0);
			int sy = (int) ((c.y < 0) ? c.y : 0);
			int ex = (int) ((c.x > 0) ? c.x : 0);
			int ey = (int) ((c.y > 0) ? c.y : 0);

			for (int y = sy; y < ey; y++)
			{
				for (int x = sx; x < ex; x++)
				{

					terrain.setTile(genCenterX + x, genCenterY + y, 2);
				}
			}

		}
		terrain.setTile(genCenterX, genCenterY, 3);

	}
}
