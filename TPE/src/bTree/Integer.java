package bTree;

public class Integer {
	private int value;
	
	Integer(int value) {
		this.value = value; 
	}
	
	public int compareTo(Integer val) {
		int a = transformInteger(this);
		int b = transformInteger(val);
		
		if(a > b)
			return 1;
		else if (b > a)
			return -1;	
		return 0;
	}
	
	public static int transformInteger(Integer value) {
		if(value != null) {
			int newValue = value.value;
			return newValue;
		}
			return 0;
		}
	
	public static void insertionSort (Integer[] toSort) {
		for(int i = 1; i < establishRange(toSort); i++){
			Integer m = toSort[i];
			int j = i;
			while(j > 0 && toSort[j-1].compareTo(m) == 1) {
				if(toSort[j-1].compareTo(m) == 1) {
					toSort[j] = toSort[j-1];
					j--;
				}
			}
			toSort[j] = m;
		}
	}
	
	public static String toString(Integer o) {
		String output = "" + transformInteger(o);
		return output;
	}
	
	//Ensure that no nulls are included when working with array
	public static int establishRange(Integer[] array) {
		int i = 0;
		while(i < array.length) {
			if(array[i] == null)
				return i;
			i++;
		}
		return i;
	}
	

}
