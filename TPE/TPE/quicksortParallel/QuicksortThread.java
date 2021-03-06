package quicksortParallel;

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
		if((upperLimit - lowerLimit) > 0) {
		qs.doQuicksort(lowerLimit, upperLimit);
		//System.out.println(this.getName() + " Result: " + qs.toString(qs.getArray()));
		}
		else {
			qs.setCompleted(true);
		}
	}
}
