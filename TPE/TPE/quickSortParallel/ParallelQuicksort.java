package quickSortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - Parallel Quicksort
 * Sorts an array of objects using the Quicksort method [parallel, using Threads].
 * */

public class ParallelQuicksort extends Thread implements SortAlgorithm {
	private static final String title = "\n\nPARALLEL RECURSION RESULTS\n";
	private static final String hDiv = "\n--------------------------------------------------------\n";
	private TimerThread t;
	private static Object[] values;
	private boolean test = false;
	private static double timeCount;
	private static int recursions;
	private static int threads;
	private static int swaps;
	private static int comparisons;
	private static boolean lowerComplete = false;
	private static boolean upperComplete = false;
	private static boolean completed = false;

	public ParallelQuicksort() {
		t = new TimerThread();
	}
	
	public void run() {
		while(!completed){
			try {
				while(!lowerComplete || !upperComplete)
					sleep(1);
				t.interrupt();
			} catch (InterruptedException e) {
				
				completed = true;
			}
		}
	}


	@SuppressWarnings("rawtypes")
	/**
	 * Sorts an array of object from smallest -> largest using the Quicksort method.
	 * @param values - an array of objects that require sorting.
	 * */
	public void sort(Comparable[] array){
		values = array;
		System.out.println(title);
		System.out.println("Initial sequence: " + toString(values));
		
		quicksort(0, values.length-1);
		
		System.out.println("Total amount of comparisons: " + comparisons + ".");
		System.out.println("Total amount of swaps: " + swaps + ".");
		System.out.println("Total amount of calls: " + recursions + ".");
		System.out.println("Total amount of threads: " + threads + ".");
		System.out.println("Parallel Quicksort completed in " + (timeCount/1000) + " seconds.");
		System.out.println(hDiv);
		
	}

	/**
	 * Sorts a sequence from smallest to largest.
	 * @param values - the array which requires sorting.
	 * @param lowerLimit - the beginning of the partition which requires sorting.
	 * @param upperLimit - the end of the partition which requires sorting. 
	 * */
	private void quicksort(int lowerLimit, int upperLimit){
		boolean entered = false;
		
		if (upperLimit > lowerLimit){
			entered = true;
			int i = partition(lowerLimit, upperLimit);
			new Joiner(new ThreadFactory(this, lowerLimit, upperLimit, i));
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
	private int partition(int lowerLimit, int upperLimit){

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
	public String toString(Object o) {
		Integer i = (Integer) o;
		return  i.getValue() + "";
	}
	
	/**
	 * @return an array of Integer-Objects as a String
	 * */
	public String toString(Object[] o) {
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
	
	public void doQuicksort(int lowerLimit, int upperLimit){
		quicksort(lowerLimit, upperLimit);
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
	
	public Object[] getArray() {
		return values;
	}
	
	public int getArrayLength(){
		return values.length;
	}
	
	public void increaseRecursionCount() {
		recursions++;
	}
	
	public int getRecursionCount() {
		return recursions;
	}
	
	public void increaseThreadCount() {
		threads++;
	}
	
	public void setLowerComplete(boolean val) {
		lowerComplete = val;
	}
	
	public boolean getLowerComplete() {
		return lowerComplete;
	}
	
	public void setUpperComplete(boolean val) {
		upperComplete = val;
	}
	
	public boolean getUpperComplete() {
		return upperComplete;
	}
	
	public boolean getTest() {
		return test;
	}
	
	/**
	 * A timer to record the time it takes from the beginning of a sort until the end in milliseconds. 
	 * */
	private class TimerThread extends Thread {
		private boolean cease = false;
		TimerThread() {
			start();
		}
		
		/**Sleeps a millisecond at a time and records a count*/
		public void run() {
			while(!cease) {
				try {
					sleep(1);
					ParallelQuicksort.this.timeCount++;
				} catch (InterruptedException e) {
					cease = true;
					ParallelQuicksort.this.interrupt();
				}
			}
		}
	}

}
