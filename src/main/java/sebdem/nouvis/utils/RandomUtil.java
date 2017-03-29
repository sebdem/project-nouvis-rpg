package sebdem.nouvis.utils;

import java.util.Random;

public class RandomUtil {
	private static Random _rng = new Random();

	public static <E> E arrayRandomItem(E[] array) {
		return array[_rng.nextInt(array.length)];
	}
	public static <E> int arrayRandomIndex(E[] array) {
		return _rng.nextInt(array.length);
	}
	public static <E> E arrayRandomItem(E[] items, float[] weights){
		float totalWeight = 0.0f;
		for (float weight : weights)
		{
		    totalWeight += weight;
		}
		// Now choose a random item
		int randomIndex = -1;
		float random = (float) (Math.random() * totalWeight);
		for (int i = 0; i < items.length; ++i)
		{
		    random -= weights[i];
		    if (random <= 0.0d)
		    {
		        randomIndex = i;
		        break;
		    }
		}
		return items[randomIndex];
	}
	
	public static boolean randomBool(){
		return randomBool(50);
	}
	public static boolean randomBool(float percantage){
		float value = (float)(_rng.nextDouble() * 100);
		return  value <= percantage;
	}
	
	public static Random getRandom() {
		return _rng;
	}
	public static Random setRandom(Random random) {
		_rng = random;
		return _rng;
	}
	public static Random setRandom(long seed) {
		_rng.setSeed(seed);
		return _rng;
	}

	
	public static int randomIntAround(Integer baseValue, Integer upperVariation, Integer lowerVariation){
		return (int)(_rng.nextDouble() * upperVariation.doubleValue() + baseValue - _rng.nextDouble() * lowerVariation.doubleValue() );
	}
	public static int randomIntAround(Integer baseValue, Integer upperVariation){
		return randomIntAround(baseValue, upperVariation, 0);
	}
	
	
	public static int[][] random2DIntArray(int width, int height, Integer[] values){
		int[][] data = new int[height][width];
		
		for(int i1 = 0; i1 < height; i1++){
			for(int i2 = 0; i2 < width; i2++){
				data[i1][i2] = arrayRandomItem(values);
			}
		}
		return data;
	}
	
	public static int[][] random2DIntArray(int width, int height, Integer[] values, float[] weights){
		int[][] data = new int[height][width];
		
		for(int i1 = 0; i1 < height; i1++){
			for(int i2 = 0; i2 < width; i2++){
				data[i1][i2] = arrayRandomItem(values, weights);
			}
		}
		return data;
	}
	
	
	public static class RandomArray<E>{
		
		private E[] arrayData;
		
		public RandomArray(E... array){
			arrayData = array;
		}
		
		public E get(){
			return RandomUtil.arrayRandomItem(arrayData);
		}
		
		public E[] getArray(){
			return this.arrayData;
		}
		
	}
	
	public static class RandomArrayWrapper<E>{
		
		public RandomArray<E>[] data;
		public RandomArrayWrapper(RandomArray<E>... data){
			this.data = data;
		}
		
		@SuppressWarnings("unchecked")
		public E[] get(){
			
			E[] array = (E[])new Object[data.length];
			for(int i = 0; i < data.length; i++){
				array[i] = data[i].get();
			}
			
			return array;
		}
	}
	
}
