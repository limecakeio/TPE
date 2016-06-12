package threads;

/* THREADS - PERFORMANCE HAZARDS
 * 
 * Scenario
 * --------
 * In this last episode the mischievous knight Sir Prattlelot has conquered the beautiful princess' heart.
 * Nevertheless, our noble knights - Sir Edmund and Sir Farlic - decided to stay for the wedding ceremony
 * and pick up a task of great difficulty - observing the castle gates so that no rogue would dare to disturb 
 * the feast. Sir Edmung positioned himself next to the Northern Gates while Sir Farlic has chosen the Southern Gates. 
 * 
 * The Northern Gates had a single door and only one guest at a time could enter the castle.
 * The Southern Gates, on the other hand, had ten small doors but only one door could be opened at any given time.
 * One million guests entered through each of them. Would Sir Edmund or Sir Farlic be the first to leave the gates?
 */

class RoyalCastleGates {
	private int guests;
	private long gatesShut;
	private long gatesOpened;

	// constructor: the castle
	public RoyalCastleGates(){
		guests = 0;
		gatesShut = 0;
		gatesOpened = 0;
	}

	// update: count royal guests
	public synchronized void enter(String gatekeeper){
		// timer: start
		if (getGuests() ==  0){
			gatesOpened = System.currentTimeMillis();
		}
		setGuests(getGuests()+1);

		// timer: end
		if (getGuests() == 1000000){
			gatesShut = System.currentTimeMillis();
			System.out.println("The Gates of " + gatekeeper + " remained wide open for "
					+ getElapsedTime() + " milliseconds.");
		}
	}

	// getter & setter
	public synchronized void setGuests(int guests){
		this.guests = guests;
	}
	public synchronized int getGuests(){
		return guests;
	}
	public long getElapsedTime(){
		return (gatesShut - gatesOpened);
	}
}

public class Example_PerformanceHazards {
	public static void main(String[] args){

		// actors: noble knights & castle gates
		String knight1 = "Sir Edmund";
		String knight2 = "Sir Farlic";
		RoyalCastleGates northernGates = new RoyalCastleGates();
		RoyalCastleGates southernGates = new RoyalCastleGates();

		// singlethread version
		new Thread(new Runnable(){ 
			public void run(){
				for (int n = 0; n != 1000000; n++){
					northernGates.enter(knight1); }}}).start();

		// multithread version
		for (int s = 0; s != 10; s++){
			new Thread(new Runnable(){ 
				public void run(){
					for (int g = 0; g != 100000; g++){
						southernGates.enter(knight2); }}}).start();
		}
	}
}

