package quicksortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - Sequential Quicksort
 * Sorts an array of objects using the Quicksort method [sequentially].
 * */

public class Quicksort implements SortAlgorithm {
	private static final String title = "\n\nSEQUENTIAL RECURSION RESULTS\n";
	private static final String hDiv = "\n--------------------------------------------------------\n";
	private boolean test = false;
	private TimerThreadQuicksort t = new TimerThreadQuicksort();
	private int recursions = 0;
	private int swaps = 0;
	private int comparisons = 0;
	protected static boolean lowerComplete = false;
	protected static boolean upperComplete = false;
	protected static boolean completed = false;

	public Quicksort() {
	}

	@SuppressWarnings("rawtypes")
	/**
	 * Sorts an array of object from smallest -> largest using the Quicksort method.
	 * @param values - an array of objects that require sorting.
	 * */
	public void sort(Comparable[] values){
		System.out.println(title);
		if(test)
			System.out.println("Sequence at beginning: " + toString(values) + "Called with lower = " + 0 + " and upper = " + (values.length-1) + ".");
		
		
		quicksort(values, 0, values.length-1);
		System.out.println("Statistic comparisons are: " + (Math.log(values.length-1)*values.length-1));
		while(comparisons < (Math.log10(values.length-1)*values.length-1)){}
		t.interrupt();
		
		System.out.println("Total amount of comparisons: " + comparisons + ".");
		System.out.println("Total amount of swaps: " + swaps + ".");
		System.out.println("Total amount of calls: " + recursions + ".");
		
		System.out.println("Time Count is: " + t.getTimeCount());
		
		System.out.println(hDiv);
	}

	/**
	 * Sorts a sequence from smallest to largest.
	 * @param values - the array which requires sorting.
	 * @param lowerLimit - the beginning of the partition which requires sorting.
	 * @param upperLimit - the end of the partition which requires sorting. 
	 * */
	private void quicksort(Object[] values, int lowerLimit, int upperLimit){
		boolean entered = false;
		
		if(lowerLimit >= values.length-1)
			lowerComplete = true;
		if(upperLimit <= 0)
			upperComplete = true;
		if (upperLimit > lowerLimit){
			entered = true;
			
			int i = partition(values, lowerLimit, upperLimit);
			if(recursions == 0 && test)
				System.out.println("BEFORE recursion: " + toString(values) + "Called with lower = " + lowerLimit + " and upper = " + upperLimit + " => INDEX RETURNED: " + i);
				
			
			recursions++;
			quicksort(values, lowerLimit, i-1);
			
			recursions++;
			quicksort(values, i+1, upperLimit);
		}
		
		if(!entered && test)
			System.out.println("Result after recursion(" + (recursions) + ") : " + toString(values) + "Called with lower = " + lowerLimit + " and upper = " + upperLimit + "-> DID NOT ENTER");
	}

	/**
	 * @return - the index-position after completing a partial sort.
	 * @param values - the array which requires sorting.
	 * @param lowerLimit - the beginning of the partition which requires sorting.
	 * @param upperLimit - the end of the partition which requires sorting. 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int partition(Object[] values, int lowerLimit, int upperLimit){

		int pivot = upperLimit;
		int index = lowerLimit;
		int pointer = lowerLimit;

		while (pointer <= upperLimit-1){

			if (((Comparable) values[pointer]).compareTo(values[pivot]) >= 0){
				swap(values, index, pointer);
				index++;
			}
			comparisons++;
			pointer++;
		}
		swap(values, index, pivot);
		if(recursions != 0 && test)
			System.out.println("Result after recursion(" + (recursions) + ") : " + toString(values) + "Called with lower = " + lowerLimit + " and upper = " + upperLimit + " => INDEX RETURNED: " + index);
		
		return index;	
	}
	
	/**
	 * @return an Integer Object as a strong
	 * */
	public static String toString(Object o) {
		Integer i = (Integer) o;
		return  i.getValue() + "";
	}
	
	/**
	 * @return an array of Integer-Objects as a String
	 * */
	public static String toString(Object[] o) {
		String result = "| ";
		for(int i = 0; i < o.length; i++) {
			result += toString(o[i]) + " | ";
			}
		return result;
		}
	
	/**
	 * Sets the object into test condition which prints the sequence onto the console after each recursive call if true.
	 * Object's test condition is set as false by default.
	 * */
	public void setTest(boolean test) {
		this.test = test;
	}
	
	/**
	 * Swaps the positions of two objects in an array [AB -> BA].
	 * @param values - the array in which the positions are swapped.
	 * @param x - the position of object A.
	 * @param y - the position of object B.
	 * */
	private void swap(Object[] values, int x, int y){
		Object cache = values[x];
		values[x] = values[y];
		values[y] = cache;
		swaps++;
	}
}
