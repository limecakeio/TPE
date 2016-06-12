package quickSortParallel;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 * A timer to record the time it takes from the beginning of a sort until the end in milliseconds. 
 * */

public class TimerThreadQuicksort extends Thread {
	private int timeCount = 0;
	private boolean completed = false;
	TimerThreadQuicksort() {
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
			System.out.println("Time Count is: " + timeCount);
		}
	}
	
	public int getTimeCount() {
		return timeCount;
	}
	
	public void setCompleted(boolean val) {
		completed = val;
	}
}
