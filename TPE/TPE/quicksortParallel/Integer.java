package quicksortParallel;

@SuppressWarnings("rawtypes")
public class Integer implements Comparable {
	private int value;
	
	Integer(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		int val1 = this.value;
		
		Integer object = (Integer) o;
		int val2 = object.value;

		if(val1 < val2)
			return 1;
		else if (val1 > val2)
			return -1;
		
		return 0;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
