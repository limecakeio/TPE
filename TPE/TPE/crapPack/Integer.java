package crapPack;

public class Integer {
	private int value;
	
	Integer(int value) {
		this.value = value; 
	}
	
	public int compareTo(Integer val) {
		
		if(val == null) {
			return 1; //Regard a null as the largest values.
		}
		else {
		int a = transformInteger(this);
		int b = transformInteger(val);
		
		if(a > b)
			return 1;
		else if (b > a)
			return -1;	
		return 0;
		}
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
	
	//Integer to String
	public static String toString(Integer o) {
		String output = "" + transformInteger(o);
		return output;
	}
	
	//Integer Array to String (no space at end)
	public static String toString(Integer[] a) {
		String output = "";
		for(int i = 0; i < a.length; i++) {
			output += transformInteger(a[i]);
			if(i < a.length-1)
				output+= " ";
		}
		return output;
	}
	
	//Ensure that no null-values are included when working with arrays
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
