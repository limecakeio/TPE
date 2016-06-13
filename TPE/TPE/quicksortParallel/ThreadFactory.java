package quicksortParallel;

/**
 * @author Konstantin Pelevin
 * @author Richard Vladimirskij
 * 
 * TPE SS2016 - ThreadFactory
 * Generates two threads to work in the partitioned arrays.
 * */


public class ThreadFactory extends Thread {
	private ParallelQuicksort qs;
	private int lowerLimit;
	private int upperLimit;
	private int index;
	
	ThreadFactory(ParallelQuicksort qs, int lowerLimit, int upperLimit, int index){
		this.qs = qs;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.index = index;
		start();
	}
	
	public void run() {
		qs.increaseRecursionCount();
		qs.increaseThreadCount();
		new QuicksortThread(qs, lowerLimit, index-1, "RecursionThread" + "-" + qs.getRecursionCount());
		
		qs.increaseRecursionCount();
		qs.increaseThreadCount();
		new QuicksortThread(qs, index+1, upperLimit, "RecursionThread" + "-" + qs.getRecursionCount());
	}
}
