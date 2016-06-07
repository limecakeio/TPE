package ringPuffer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RingpufferMain extends Thread {
	public static final String lineDiv = "\n------------------------------------------------------------------------------------------------------------------------------------\n";
	public static Scanner in = new Scanner(System.in);
	
	public void run() {
		
		
	}
	

	public static void main(String[] args) {
		int size = 0;
		
		System.out.println("WELCOME TO THE SUPER QUICK RINGBUFFER, PARALLEL COMPUTING REALISED USING THREADS!");
			boolean success = false;
			
			//Loop through the options in order to catch certain exceptions which we can fix using a dialog.
			while(!success) {
				System.out.print(lineDiv + "Please enter a size for the RingBuffer! " + lineDiv + "Size: ");
				try {
					size = in.nextInt();
					if(size <= 0)
						throw new InputMismatchException();
					
					Ringpuffer rp = new Ringpuffer(size);
					
					
					success = true;
				
				}
				catch(InputMismatchException e) {
					System.err.println("The size of the Ringbuffer has to be a positive integer!");
					in.nextLine();
				} 
			}
	}

}
