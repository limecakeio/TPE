package ringPuffer;

import java.util.concurrent.TimeUnit;

public class TimerThread extends Thread {
	boolean running = true;
	long mins;
	
	TimerThread(int mins) {
		this.mins = mins;
	}
	
	/**Goes to sleep for the required time and awaked to set itself as not running*/
	public void run() {
			try {
				TimeUnit.SECONDS.sleep(mins);
			} catch (InterruptedException e) {
				System.out.print("Timer has been interrupted.");
			}
			System.out.println("Timer is finished");
			running = false;
	}	
}