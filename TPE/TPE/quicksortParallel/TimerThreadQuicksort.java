package quicksortParallel;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 * A timer to record the time it takes from the beginning of a sort until the end in milliseconds. 
 * */

public class TimerThreadQuicksort extends Thread {
	private double timeCount = 0;
	private boolean completed = false;
	TimerThreadQuicksort() {
		setPriority(MAX_PRIORITY);
		start();
	}
	
	/**Sleeps a millisecond at a time and records a count*/
	public void run() {
		while(!completed) {
			try {
				sleep(1);
			} catch (InterruptedException e) {
				completed = true;
			}
			timeCount++;
		}
	}
	
	public double getTimeCount() {
		return timeCount;
	}
	
	public void setCompleted(boolean val) {
		completed = val;
	}
}
