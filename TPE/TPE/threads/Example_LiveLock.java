package threads;

/* THREADS - LIVELOCK
 * 
 * Scenario
 * ---------
 * In medieval times, in case the challnge of the gauntlet would backfire, noble knights would 
 * not give up hope and perform heroic deeds to conquer the heart of the local princess in question.
 * Usually those were heroic deeds of great danger, such as slaying a fierce dragon. 
 * 
 * Since the trial of the gauntlet backfired a lot in the old days of glory, poor dragons would often 
 * find themselves surrounded by a group of highly motivated challengers. Noble (and perhaps a little coward)
 * at heart, knights would then pass the right of slaying the beast on to the rival. These awkward situations
 * often resulted in a livelock of endless courtsey while both, the fierce dragon and the beautiful princess in
 * question, died of boredom and old age...  
 */

class Dragon {

	private Knight challenger;	

	// constructor: dragon
	public Dragon(Knight knight){ 
		challenger = knight; 
	}

	public synchronized void attack(){
		challenger.isEager(false);
		System.out.println(challenger.getName() + ": Thy days of glory are over, foul beast!\nFor its is I,"
				+ challenger.getName() + " who will face thee and... [runs away as the dragon is about to smack him]]"); 
	}
	// getter & setter methods
	public Knight getChallenger(){ 
		return challenger; 
	}
	public void setChallenger(Knight knight){ 
		challenger = knight; 
	}
}

class Knight {

	private String name;
	private boolean eager;

	// constructor: knight
	public Knight(String name){
		eager = true; 
		this.name = name; 
	}
	
	public void challenge(Dragon dragon, Knight knight){
		while (eager){
			
			// the noble knight is not the challenger
			if (dragon.getChallenger() != this) {
				try { 
					Thread.sleep (2500); 
				} 
				catch (InterruptedException e){
					continue; 
				}
				continue;
			}                       
			// another noble knight should always face the dragon first.
			if (knight.isEager()){
				if (name.equals("Sir Edmund")){
				System.out.println(name + ": " + knight.getName() + "!"
						+ "\nThy is the honor to slay the beast most foul and mischievous!");
				}
				if (name.equals("Sir Farlic")){
					System.out.println(name + ": Nay, honored rival! "
							+ "'Tis the honor of thee!");	
				}
				dragon.setChallenger(knight);
				continue;
			}
			// face the dragon already!
			dragon.attack();               
			dragon.setChallenger(knight);
		}
	}
	// getter & setter
	public void setName(String name){
		this.name = name;
	}
	public String getName(){ 
		return name; 
	}
	public void isEager(boolean b){
		eager = b;
	}
	private boolean isEager(){
		return eager;
	}
}

public class Example_LiveLock {

	public static void main(String[] args) {

		// contestants: noble knights & a bored dragon
		final Knight knight1 = new Knight("Sir Edmund");
		final Knight knight2 = new Knight("Sir Farlic");
		final Dragon dragon = new Dragon(knight1);

		new Thread(new Runnable(){ 
			public void run(){ 
				knight1.challenge(dragon, knight2); }}).start();
		new Thread(new Runnable(){ 
			public void run(){ 
				knight2.challenge(dragon, knight1); }}).start();
	}
}
