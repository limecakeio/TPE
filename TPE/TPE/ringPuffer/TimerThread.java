package ringPuffer;

import java.util.concurrent.TimeUnit;

public class TimerThread extends Thread {
	long mins;
	
	TimerThread(int mins) {
		this.mins = mins;
		startTimer();
	}
	
	private void startTimer() {
			try {
				TimeUnit.MINUTES.sleep(mins);
			} catch (InterruptedException e) {
				System.out.print("Timer has been interrupted. ");
			}
	}	
}