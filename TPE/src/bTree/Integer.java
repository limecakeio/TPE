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
	
	private int transformInteger(Integer value) {
		int newValue = value.value;
		return newValue;
	}
	
	

}
