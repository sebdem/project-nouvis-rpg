package sebdem.nouvis.utils;

public class ArrayUtils {

	public static <E> E arrayRandomItem(E[] array) {
		return RandomUtils.arrayRandomItem(array);
	}
	public static <E> int arrayRandomIndex(E[] array) {
		return RandomUtils.arrayRandomIndex(array);
	}

	
	public static <E> E[] mergeArrays1D(E[] parent, E[] child, int index1){
		
		if (child.length + index1 <= parent.length ){
			for(int i = index1; i < child.length + index1 && i < parent.length; i++){
				parent[i] = child[i - index1];
			}
		} else {
			System.out.println("[ARRAYUTILS]: Could not insert child array into parent, not enough space!");
		}
		
		return parent;
	}

	public static int[][]  fillInteger2D(int[][] array, Integer... values){
		for(int i1 = 0; i1 < array.length; i1++){
			for(int i2 = 0; i2 < array[i1].length; i2++){
				array[i1][i2] = arrayRandomItem(values);
			}
		}
		return array;
	}
	public static <E> E[][]  fillArray2D(E[][] array, E... values){
		for(int i1 = 0; i1 < array.length; i1++){
			for(int i2 = 0; i2 < array[i1].length; i2++){
				array[i1][i2] = arrayRandomItem(values);
			}
		}
		return array;
	}
	
	
	public static <E> E[][] mergeArrays2D(E[][] parent, E[][] child, int index1, int index2){
		int cind1;
		for(int i = index1; i < child.length + index1 && i < parent.length; i++){
			cind1 = i - index1;
			for(int i2 = index2; i2 < child[cind1].length + index2 && i2 < parent[i].length; i2++){
				parent[i][i2] = child[cind1][i2-index2];
			}
		}
		
		return parent;
	}

	public static int[][] mergeIntArray2D(int[][] parent, int[][] child, int index1, int index2){
		int cind1;
		for(int i = index1; i < child.length + index1 && i < parent.length; i++){
			cind1 = i - index1;
			for(int i2 = index2; i2 < child[cind1].length + index2 && i2 < parent[i].length; i2++){
				parent[i][i2] = child[cind1][i2-index2];
			}
		}
		return parent;
	}
	
	
	public static class ArrayWrapper1D<E>{
		public E[] array;
		
		public ArrayWrapper1D(E[] data){
			this.array = data;
		}
		
		public String toString(){
			StringBuffer out = new StringBuffer("{");
			for(int i = 0; i < array.length; i++){
				
			}
			out.append("}");
			return out.toString();
		}
	}
	public static class IntArWrap2D{
		public int[][] array;
		
		public IntArWrap2D(int[][] data){
			this.array = data;
		}
		
		public String toString(){
			StringBuffer out = new StringBuffer("Dimensions: " + array.length + "x" + array[0].length);
			out.append("\n{\n");
			for(int i = 0; i < array.length; i++){
				out.append("\t{ ");
				for(int i2 = 0; i2 < array[i].length; i2++){
					out.append(String.format("%4d", array[i][i2]));
					if (i2 <array[i].length - 1)
						out.append(", ");
					
				}
				out.append("}" + ((i < array.length - 1) ? ", " : "")+"\n");
			}
			out.append("}");
			return out.toString();
		}
		
	}
	
	public static void main(String[] args){
		
		int[][] testarray = {{6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
			  {6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6},
			  {6,1,7,15,15,15,8,1,1,1,1,1,1,7,15,15,15,8,1,6},
			  {6,1,18,5,5,5,17,1,1,1,1,1,1,18,5,5,5,17,1,6},
			  {6,1,18,5,11,16,10,1,1,2,1,1,1,9,12,5,5,17,1,6},
			  {6,1,18,5,17,1,1,1,4,1,1,1,1,1,9,12,5,17,1,6},
			  {6,1,9,16,10,1,1,3,1,1,2,1,1,1,1,9,16,10,1,6},
			  {6,1,1,1,7,15,15,8,1,1,2,3,1,1,1,1,1,1,1,6}};
		int[][] childray = {{99,99,99},{99,70,99},{99,99,99}};
		IntArWrap2D wrap = new IntArWrap2D(testarray);
		IntArWrap2D wrap2 = new IntArWrap2D(childray);
		System.out.println(wrap.toString());
		System.out.println(wrap2.toString());
		
		ArrayUtils.mergeIntArray2D(testarray, childray, 7, 7);
		

		System.out.println(wrap.toString());
	}
}
