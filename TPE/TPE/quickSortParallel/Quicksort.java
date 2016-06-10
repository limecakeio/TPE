package quickSortParallel;

import java.util.concurrent.TimeUnit;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016
 * */

public class Quicksort implements SortAlgorithm {
	private static final String title = "SEQUENTIAL RECURSION RESULTS";
	//private TimerThread t = new TimerThread();
	private int recursions = -1;
	private int swaps = 0;
	private int comparisons = 0;
	private int threads = 0;
	private boolean complete = false;

	Quicksort() {	
	}
	
	private void swap(Object[] values, int x, int y){
		Object cache = values[x];
		values[x] = values[y];
		values[y] = cache;
		swaps++;
	}


	@SuppressWarnings("rawtypes")
	public void sort(Comparable[] values){
		quicksort(values, 0, values.length-1);
		System.out.println("\n\n\n" + title + "\n\n\n");
		System.out.println("Total amount of comparisons: " + comparisons + ".");
		System.out.println("Total amount of swaps: " + swaps + ".");
		System.out.println("Total amount of recursions: " + recursions + ".");
		complete = true;
	}

	private void quicksort(Object[] values, int lowerLimit, int upperLimit){
		recursions++;
		if (upperLimit > lowerLimit){
			
			int i = partition(values, lowerLimit, upperLimit);

			quicksort(values, lowerLimit, i-1);
			quicksort(values, i+1, upperLimit);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	int partition(Object[] values, int lowerLimit, int upperLimit){

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
		System.out.println("Result after recursion(" + recursions + ") : " + toString(values));
		return index;	
	}
	
	public static String toString(Object o) {
		Integer i = (Integer) o;
		return  i.getValue() + "";
	}
	
	public static String toString(Object[] o) {
		String result = "| ";
		for(int i = 0; i < o.length; i++) {
			result += toString(o[i]) + " | ";
			}
		return result;
		}
	
	/**Counts the seconds until Quicksort finishes its procedure.*/
	
	private class TimerThread extends Thread {
		private double timeCount = 0;
		
		TimerThread() {
			start();
		}
		
		/**Sleeps a second at a time and records a count*/
		public void run() {
			while(!Quicksort.this.complete) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
					System.out.println("Timer woke up");
					timeCount++;
				} catch (InterruptedException e) {
					System.out.print("Timer has been interrupted.");
				}
			}
			System.out.println("Sequential Quicksort completed in " + timeCount/1000 + "s.");
		}
	}
}
