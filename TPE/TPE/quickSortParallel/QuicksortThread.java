package quickSortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - Quicksort Thread
 * Thread which calls up the Quicksort
 * */

public class QuicksortThread extends Thread {
	private int lowerLimit, upperLimit;
	private ParallelQuicksort qs;
	
	
	QuicksortThread(ParallelQuicksort qs, int lowerLimit, int upperLimit, String name) {
		super(name);
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.qs = qs;
		start();
	}
	
	public void run() {
		qs.doQuicksort(lowerLimit, upperLimit);
		
		//Catch if the lower partitioning is complete
		if(upperLimit <= 0)
			qs.setLowerComplete(true);
		//Catch if the upper partitioning is complete
		if(lowerLimit >= qs.getArrayLength())
			qs.setUpperComplete(true);
		
		//System.out.println(this.getName() + " Result: " + qs.toString(qs.getArray()));
	}
}
