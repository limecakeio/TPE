package quickSortParallel;

public class ThreadFactory extends Thread {
	private int lowerLimit;
	private int upperLimit;
	private int index;
	private ParallelQuicksort qs;
	
	ThreadFactory(){}
	
	ThreadFactory(ParallelQuicksort qs, int lowerLimit, int upperLimit, int index){
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.index = index;
		this.qs = qs;
		start();
	}
	
	public void run() {
		qs.increaseRecursionCount();
		qs.increaseThreadCount();
		new QuicksortThread(qs, lowerLimit, index-1, "RecursionThread" + qs.getRecursionCount());
		
		qs.increaseRecursionCount();
		qs.increaseThreadCount();
		new QuicksortThread(qs, index+1, upperLimit, "RecursionThread" + qs.getRecursionCount());
	}
}
