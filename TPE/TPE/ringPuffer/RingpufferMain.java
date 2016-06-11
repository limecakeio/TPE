package ringPuffer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RingpufferMain extends Thread {
	public static final String lineDiv = "\n------------------------------------------------------------------------------------------------------------------------------------\n";
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int size = 0;
		int time = 0;
		int cAmount = 0;
		int pAmount = 0;
		
		System.out.println("WELCOME TO THE SUPER QUICK RINGBUFFER, PARALLEL-COMPUTING REALISED USING THREADS!");
			boolean success = false;
			boolean getSize = false;
			boolean getConsumer = false;
			boolean getProducer = false;
			boolean getTime = false;
			
			/**Loop through the options in order to catch certain exceptions which we can fix using a dialog*/
			while(!success) {
				while (!getSize) {
					System.out.println(lineDiv + "Please enter a size for the RingBuffer! " + lineDiv + "Size: ");
					try {
						size = in.nextInt();
						if (size <= 0)
							throw new InputMismatchException();
					} catch (InputMismatchException e) {
						System.err.println("Only accepting positive integers for size these days, please try again!");
						in.nextLine();
					}
					getSize = true;
				}
				while (!getConsumer) {
					System.out.println(lineDiv + "Enter the amount of consumer-threads required! " + lineDiv + "Consumers: ");
					try {
						cAmount = in.nextInt();
						if (size <= 0)
							throw new InputMismatchException();
					} catch (InputMismatchException e) {
						System.err.println("You will need at least one consumer for this to work, please try again.");
						in.nextLine();
					}
					getConsumer = true;
				}
				while (!getProducer) {
					System.out.println(lineDiv + "Enter the amount of producer-threads required! " + lineDiv + "Producers: ");
					try {
						pAmount = in.nextInt();
						if (size <= 0)
							throw new InputMismatchException();
					} catch (InputMismatchException e) {
						System.err.println("You will need at least one producer for this to work, please try again.");
						in.nextLine();
					}
					getProducer = true;
				}
				while (!getTime) {
					System.out.println(lineDiv + "How long should this process take in minutes? " + lineDiv + "Minutes: ");
					try {
						time = in.nextInt();
						if (size <= 0)
							throw new InputMismatchException();
					} catch (InputMismatchException e) {
						System.err.println("It'll take at least one minute, please try again.");
						in.nextLine();
					}
					getTime = true;
				}
				
				/**All information collected at this point*/
				Ringpuffer rp = new Ringpuffer(size, cAmount, pAmount, time);
				
				/**Start the count-down*/
				rp.startTimer();
				
				/**Set up the consumers*/
				for(int i = 0; i < cAmount; i++)
					rp.setConsumer(i, (i+1)*2000, "CONSUMER" + (i+1));
				
				/**Set up the producers*/
				for(int i = 0; i < pAmount; i++)
					rp.setProducer(i, (i+1)*1500, "PRODUCER" + (i+1));
				
				/**Ending master-loop*/
				success = true;
			}
			System.out.println("Notice: Main is completed.");
	}

}
