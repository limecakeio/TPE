package crapPack;

@SuppressWarnings("rawtypes")
public class Integer implements Comparable {
	private int value;
	
	Integer(int value) {
		this.value = value; 
	}
	
	public int compareTo(Object val) {
		
		if(val == null) {
			return 1; //Regard a null as the largest values.
		}
		else {
		int a = transformInteger((Integer) this);
		int b = transformInteger((Integer) val);
		
		if(a > b)
			return 1;
		else if (b > a)
			return -1;	
		return 0;
		}
	}
	
	public static int transformInteger(Object value) {
		if(value != null) {
			int newValue = ((Integer) value).value;
			return newValue;
		}
			return 0;
		}
	
	public static void insertionSort (Object[] toSort) {
		for(int i = 1; i < establishRange((Integer[]) toSort); i++){
			Object m = toSort[i];
			int j = i;
			
			
			while(j > 0 && ((Integer) toSort[j-1]).compareTo(m) == 1) {
				if(((Integer) toSort[j-1]).compareTo(m) == 1) {
					toSort[j] = toSort[j-1];
					j--;
				}
			}
			toSort[j] = m;
		}
	}
	
	//Integer to String
	public String toString(Object o) {
		String output = "" + transformInteger(o);
		return output;
	}
	
	//Integer Array to String (no space at end)
	public String toString(Object[] a) {
		String output = "";
		for(int i = 0; i < a.length; i++) {
			output += transformInteger(a[i]);
			if(i < a.length-1)
				output+= " ";
		}
		return output;
	}
	
	//Ensure that no null-values are included when working with arrays
	public static int establishRange(Object[] array) {
		int i = 0;
		while(i < array.length) {
			if(array[i] == null)
				return i;
			i++;
		}
		return i;
	}
}
