package bTree;

public class BTreeRichard {

	public static int compareTo(Integer a, Integer b) {
		int valA = (int)a;
		int valB = (int)b;
		
		if(a > b)
			return 1;
		else if (b > a)
			return -1;
		
		return 0;
		
	}
}
