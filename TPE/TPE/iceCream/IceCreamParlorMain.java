package iceCream;

import static gdi.MakeItSimple.*;

public class IceCreamParlorMain {
	
	/* TPE EXCERSICE-SHEET #2
	 * EXCERSICE #2
	 * 
	 * Richard Vladimirskij (1527079)
	 * Konstantin Pelevin   (1525950)
	 */
	
	public static void main(String[] args){

		// set up: different ice-cream parlor locations
		IceCreamParlor[] localParlor = new IceCreamParlor[3];
		localParlor[0] = new ICP_Mannheim();
		localParlor[1] = new ICP_English();
		localParlor[2] = new ICP_Hochdeutsch();

		// set up: menu
		int command = 0;
		int active = 0;
		String input = "";
		boolean shutdown = false;
		boolean activeSet = false;

		/* Order your ice-cream in different locations!
		 * Local Parlors: [0] = Mannheim
		 * Local Parlors: [1] = English
		 * Local Parlors: [2] = Hochdeutsch
		 * 
		 * Supported ice-cream types are:
		 * "Spaghettieis", "Bananasplit", "Nussbecher".
		 */

		while (!shutdown){

			// pick your active ice-cream parlor
			if (!activeSet){
				println("Please pick your favourite ice-cream parlor!");
				println("Enter: [1] to choose the 'Mannheim Parlor'.");
				println("Enter: [2] to choose the English Parlor.");
				println("Enter: [3] to choose the Hochdeutsch Parlor.");

				active = readInt();

				// check input
				if (active < 1 | active > 3){
					println("Invalid input line. Please try again.");
				}
				else {
					// -1 to match object array index
					activeSet = true; 
					active = active -1;
				}	
			}

			// active ice-cream parlor is set, provide commands
			if (activeSet && !shutdown && command == 0){
				if (active == 0){
					println("\nCurrent ice-cream parlor: #" + (active+1) + " | Parlor Mannheim");
				}
				else if (active == 1){
					println("\nCurrent ice-cream parlor: #" + (active+1) + " | Parlor English");
				}
				else if (active == 2){
					println("\nCurrent ice-cream parlor: #" + (active+1) + " | Parlor Hochdeutsch");
				}
				println("Enter: [1] to order an ice-cream.");
				println("Enter: [2] to visit to another ice-cream parlor.");
				println("Enter: [3] to shutdown the program.\n");

				// get input and execute command
				command = readInt();

				// Option 1: order an ice-cream
				if (command == 1){
					command = 0;
					readLine();
					println("Please enter the name of the ice-cream you would like to order.");
					println("Supported variants are: Spaghettieis, Bananasplit, Nussbecher.");
					input = readLine();

					// check the destination ice-cream parlor
					if (active == 0){
						localParlor[active].order(translateMannheim(input));
					}
					else if (active == 1){
						localParlor[active].order(translateEnglish(input));
					}
					else {
						localParlor[active].order(input);
					}
				}

				// Option 2: pick another ice-cream parlor
				if (command == 2){
					command = 0;
					activeSet = false;
				}

				// Option 3: shutdown execution
				if (command == 3){
					command = 0;
					shutdown = true;
					println("Shutting down... Have a nice day!");
				}

				// validate command input
				if (command != 0 && (command > 3 | command < 0)){
					command = 0;
					println("Error: Wrong command line. Please try again.");
				}	
			}	
		}
	}

	// AUXILIARY METHODS
	public static String translateMannheim(String text){

		// translate order: to mannheim
		if (text.equals("Spaghettieis")){
			text = "Spageddiais";
		}
		else if (text.equals("Bananasplit")){
			text = "Bananesplitt";
		}
		else if (text.equals("Nussbecher")){
			text = "Nussbescha";
		}
		return text;
	}

	public static String translateEnglish(String text){

		// translate order: to english
		if (text.equals("Spaghettieis")){
			text = "Spaghetti-formed ice-cream";
		}
		else if (text.equals("Nussbecher")){
			text = "Nut-cup";
		}
		return text;
	}

}
