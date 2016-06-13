package ringPuffer;

import java.util.concurrent.TimeUnit;

/**
 * @author Richard Vladimirskij, Konstantin Pelevin
 * 
 * TimerThread - acts as a countdown, set in minutes.
 * Upon completion, the timer thread leads to all producer/consumer threads working on a Ringbuffer
 * to finish their runs.
 * */

public class TimerThread extends Thread {
	boolean running = true;
	long mins;
	
	TimerThread(int mins) {
		this.mins = mins;
	}
	
	/**Goes to sleep for the required time and awakes only to set itself as not running*/
	public void run() {
			try {
				TimeUnit.MINUTES.sleep(mins);
			} catch (InterruptedException e) {
				System.out.print("Timer has been interrupted.");
			}
			running = false;
	}	
}