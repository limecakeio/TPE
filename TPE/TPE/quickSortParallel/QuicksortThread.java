package quickSortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - Quicksort Thread
 * Parallely executes the partition method of the quicksort.
 * */

public class QuicksortThread extends Thread {
	private int lowerLimit, upperLimit;
	private ParallelQuicksort qs;
	
	QuicksortThread(){}
	
	QuicksortThread(ParallelQuicksort qs, int lowerLimit, int upperLimit, String name) {
		super(name);
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.qs = qs;
		start();
	}
	
	public void run() {
		if(upperLimit <= 0)
			qs.setLowerComplete(true);
		if(lowerLimit >= qs.getArrayLength())
			qs.setUpperComplete(true);
		qs.doQuicksort(lowerLimit, upperLimit);
		System.out.println(this.getName() + ": " + qs.toString(qs.getArray()));
	}
}
